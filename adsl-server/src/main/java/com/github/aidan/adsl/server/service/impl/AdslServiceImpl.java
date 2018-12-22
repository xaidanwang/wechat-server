package com.github.aidan.adsl.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.aidan.adsl.server.bean.ADSL;
import com.github.aidan.adsl.server.dao.AdslAccountDoMapper;
import com.github.aidan.adsl.server.entity.AdslAccountDo;
import com.github.aidan.adsl.server.service.AdslService;
import com.github.aidan.adsl.server.util.JsonFormatUtil;
import com.github.aidan.adsl.server.util.StreamUtil;
import com.github.aidan.adsl.server.util.UuidUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class AdslServiceImpl implements AdslService {

    @Autowired
    private AdslAccountDoMapper adslAccountDoMapper;
    @Override
    public ADSL getAdslAccount() {
        ADSL adsl = new ADSL();
        AdslAccountDo adslAccountDo =adslAccountDoMapper.getOneAdslAccountDo();
        BeanUtils.copyProperties(adslAccountDo,adsl);
        return adsl;
    }

    @Override
    public String deleteAdslAccount(String host) {

        adslAccountDoMapper.deleteAdslAccount(host);
        return "删除成功";
    }

    @Override
    public String deleteAll() {

        adslAccountDoMapper.deleteAll();
        return "删除成功";
    }

    @Override
    public String uploadFile(boolean useable, MultipartFile file) {
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
                saveAccount(useable,arrs);
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

    private int saveAccount(Boolean useable,String[] arrs){
        AdslAccountDo adslAccountDo = new AdslAccountDo();
        System.out.println(arrs[0]);
        adslAccountDo.setHost(arrs[0]);
        adslAccountDo.setPort(Integer.parseInt(arrs[1]));
        adslAccountDo.setUsername(arrs[2]);
        adslAccountDo.setPwd(arrs[3]);
        adslAccountDo.setUseable(useable);
        System.out.println(JsonFormatUtil.formatJson(JSON.toJSONString(adslAccountDo)));
        return adslAccountDoMapper.insertSelective(adslAccountDo);
    }

    @Override
    public String updateWechatAccount(String host, Integer port, boolean useable) {

        adslAccountDoMapper.updateWechatAccount(host,port,useable);
        return "更新成功!";
    }
}
