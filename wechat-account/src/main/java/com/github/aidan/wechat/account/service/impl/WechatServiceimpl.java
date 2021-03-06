package com.github.aidan.wechat.account.service.impl;

import com.github.aidan.wechat.account.common.RedisDo;
import com.github.aidan.wechat.account.dao.WechatAccountDoMapper;
import com.github.aidan.wechat.account.entity.WechatAccountDo;
import com.github.aidan.wechat.account.service.WechatService;
import com.github.aidan.wechat.account.util.CopyUtils;
import com.github.aidan.wechat.account.util.EmptyUtil;
import com.github.aidan.wechat.account.util.StreamUtil;
import com.github.aidan.wechat.account.util.UuidUtils;
import com.github.aidan.wechat.account.vo.AccountStock;
import com.github.aidan.wechat.account.vo.AccountStockVo;
import com.github.aidan.wechat.account.vo.AccountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

@Service
public class WechatServiceimpl implements WechatService {

    @Autowired
    private WechatAccountDoMapper wechatAccountDoMapper;

    @Autowired
    private RedisDo redisDo;

    private static boolean dbFlag = false;

    @Override
    public AccountVo getWechatAccount(String accountKey) {

        if (!dbFlag){
            AccountVo accountVo = redisDo.getAccount(accountKey);
            redisDo.initAccountPool(accountKey);
            dbFlag = true;
            return  accountVo;
        }

        Long RedisPoolAccountCount = redisDo.getRedisPoolAccountCount(accountKey);

        if (RedisPoolAccountCount >30){

            return redisDo.getAccount(accountKey);
        }

        String lockKey = "lock:account";

        if (RedisPoolAccountCount >0 && !redisDo.tryLock(lockKey,3L)){
            return redisDo.getAccount(accountKey);
        }


        if (redisDo.tryLock(lockKey,3L)){

            dbFlag = redisDo.pushNewAccount(accountKey);

            redisDo.releaseLock(lockKey);
        }else if (RedisPoolAccountCount >0 ){

            return redisDo.getAccount(accountKey);

        }else {

            if (!retryTryLock(lockKey)){
                //手动解锁失败!
                throw  new RuntimeException("Redis　死锁，手动删除");
            }

            if (redisDo.tryLock(lockKey,3L)){
                dbFlag = redisDo.pushNewAccount(accountKey);
                redisDo.releaseLock(lockKey);
            }
        }

        return redisDo.getAccount(accountKey);
    }

    @Override
    public String uploadFile(Integer status,MultipartFile file) {

        String fileName = file.getOriginalFilename();

        // 获取文件后缀
        String prefix=fileName.substring(fileName.lastIndexOf("."));
        FileInputStream fis =null;
        InputStreamReader isr=null;
        BufferedReader br=null;
        try {
            // 用uuid作为文件名，防止生成的临时文件重
            final File excelFile = File.createTempFile(UuidUtils.getUUID(), prefix);
            // MultipartFile to File
            file.transferTo(excelFile);

            fis=new FileInputStream(excelFile);
            isr=new InputStreamReader(fis, "UTF-8");
            br = new BufferedReader(isr);
            String line="";
            String[] arrs= null;
            while ((line=br.readLine())!=null) {
                arrs=line.split("----");
                System.out.println(arrs[0] + " : " + arrs[1] + " : " + arrs[2]);
                saveAccount(status,arrs);
            }

            //删除临时文件
            deleteFile(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败!";
        }finally {
            StreamUtil.closeCloseable(fis);
            StreamUtil.closeCloseable(isr);
            StreamUtil.closeCloseable(br);
        }

        return "上传成功!";
    }


    /**
     * 更新账户状态
     * @param uername
     * @param status 1 正常，2 封号，3 解封，4 待定
     * @return
     */
    @Override
    public String updateWechatAccount(String uername, Integer status) {

        WechatAccountDo wechatAccountDo = new WechatAccountDo();

        wechatAccountDo.setUsername(uername);
        wechatAccountDo.setStatus(status);

        wechatAccountDoMapper.updateByUsername(wechatAccountDo);

        return "更新成功!";
    }


    /**
     * 获取指定状态的账号信息
     * @param status 1 正常，2 封号，3 解封，4 待定
     * @return
     */
    @Override
    public List<AccountVo> getWechatAccountByStatus(Integer status) {

        return wechatAccountDoMapper.selectByStatus(status);
    }

    /**
     * 下载指定状态账号的信息
     * @param status
     * @param request
     * @param response
     * @return
     */
    @Override
    public File download(Integer status, HttpServletRequest request, HttpServletResponse response) {

        File file = new File(UuidUtils.getUUID()+".txt");
        deleteFile(file);
        BufferedWriter bw =null;
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getName(),true);

            List<AccountVo> accountVoList =  wechatAccountDoMapper.selectByStatus(status);
            bw = new BufferedWriter(fw);
            for (AccountVo accountVo :accountVoList){
                bw.write(accountVo.printVo());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
           // deleteFile(file);
            StreamUtil.closeCloseable(bw);
        }
        System.out.println(file.getAbsolutePath());
        System.out.println(file);

        return file;
    }


    @Override
    public String deleteAccount(Integer status, String username) {

         int n = wechatAccountDoMapper.deleteAccount(status,username);

        return "删除成功 :"+n+"  个账号";
    }


    @Override
    public boolean releaseRedisLock(String accountKey) {

        return redisDo.releaseLock(accountKey);
    }

    @Override
    public AccountStockVo getAccountStock() {
        AccountStockVo accountStockVo = new AccountStockVo();

        Map<Integer,AccountStock> constantMap = wechatAccountDoMapper.getAccountStock();
        System.out.println(constantMap);
        accountStockVo.setTotalCount(wechatAccountDoMapper.getAccountTotal());
        accountStockVo.setUseableCount(constantMap.get(1).getCount()==null?0:constantMap.get(1).getCount());
        accountStockVo.setForbiddenCount(constantMap.get(2).getCount()==null?0:constantMap.get(2).getCount());
        accountStockVo.setReleaseFailCount(constantMap.get(3).getCount()==null?0:constantMap.get(3).getCount());

        return accountStockVo;
    }

    /**
     * 删除临时生成的文件
     * @param files
     */
    private void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }



    private int saveAccount(Integer status,String[] arrs){

        WechatAccountDo wechatAccountDo = new WechatAccountDo();
        wechatAccountDo.setUsername(arrs[0]);
        wechatAccountDo.setPassword(arrs[1]);
        wechatAccountDo.setData(arrs[2]);
        wechatAccountDo.setToken(arrs[3]);

       if (status == 1){
           wechatAccountDo.setIsusable(true);
       }

        wechatAccountDo.setStatus(status);
        //如果存在该账户更新状态
        WechatAccountDo wechatAccountDo1 =  wechatAccountDoMapper.selectByUername(arrs[0]);

        if (EmptyUtil.isNotEmpty(wechatAccountDo1)){

            CopyUtils.copyProperties(wechatAccountDo,wechatAccountDo1);

            return  wechatAccountDoMapper.updateByPrimaryKey(wechatAccountDo1);
        }
        return wechatAccountDoMapper.insertSelective(wechatAccountDo);
    }


    public boolean  retryTryLock(String lockKey){
        boolean lock =false;
        for (int i = 0;i<8;i++){

            lock = redisDo.tryLock("lock:account",3L);
            if (lock){
                break;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //7 次尝试获取锁失败！手动解锁
        if (!lock){
            lock = redisDo.releaseLock("lock:account");
        }
        return lock;
    }

}
