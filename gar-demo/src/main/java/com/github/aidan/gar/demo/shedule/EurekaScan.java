package com.github.aidan.gar.demo.shedule;

import com.github.aidan.gar.demo.eureka.bean.ApplicationInfo;
import com.github.aidan.gar.demo.eureka.bean.InstanceInfo;
import com.github.aidan.gar.demo.eureka.bean.ServiceInstanceDTO;
import com.github.aidan.gar.demo.eureka.fegin.IEurekaService;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author wang yi fei
 * @date 2019/3/1 17:01
 */
public class EurekaScan implements Runnable {

    private ChannelHandlerContext ctx;


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IEurekaService iEurekaService;

    public EurekaScan(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    public EurekaScan() {
    }

    @Override
    public void run() {

    }


    protected ServiceInstanceDTO queryEurekaApps(){

        ServiceInstanceDTO serviceInstanceDTO = iEurekaService.queryEurekaApps();
        //redis 获取内存中的数据
        ServiceInstanceDTO serviceInstanceDTO1 = new ServiceInstanceDTO();
        if (serviceInstanceDTO != null && serviceInstanceDTO.equals(serviceInstanceDTO1)){
            //发送心跳
            return null;
        }else {
            //不同找出不同的地方，并确定是删除还是增加，或者更新
            List<ApplicationInfo> application = serviceInstanceDTO.getApplications().getApplication();
            List<ApplicationInfo> application1 = serviceInstanceDTO1.getApplications().getApplication();

            for (int i =0;i<application.size();i++){
                ApplicationInfo applicationInfo = application.get(i);


                for (int j=0;j<application1.size();j++){

                    if (application1.contains(applicationInfo)&&applicationInfo.equals(application1.get(j))){

                        //如果application1 中包含applicationInfo 切 两个值相等则数据不需要更改
                        break;
                    }

                }
            }
        }
        return null;
    }

    public void compareInstanceInfo(List<InstanceInfo> instanceInfo1,List<InstanceInfo> instanceInfo2){

        if ((instanceInfo1== null || instanceInfo1.size()<=0)&& (instanceInfo2== null || instanceInfo2.size()<=0)){
            return;
        }
        if (instanceInfo1== null || instanceInfo1.size()<=0){
            //返回instanceInfo2
            return;
        }

        if (instanceInfo2== null || instanceInfo2.size()<=0){
            //返回instanceInfo1
            return;
        }

        List<InstanceInfo> newinstanceInfos = new ArrayList<>();

        for (int i=0;i<instanceInfo1.size();i++){
            InstanceInfo instanceInfo = instanceInfo1.get(i);
            if (instanceInfo2.contains(instanceInfo)){
                //说明内存中有该值,且已经注册，发送心跳消息
                instanceInfo2.remove(instanceInfo);
            }else {
                //不包含,加入新列表注册
                newinstanceInfos.add(instanceInfo);
                instanceInfo2.remove(instanceInfo);
            }
        }
        //循环体执行结束,instanceInfo2 剩下的实例为要删除的
        List<InstanceInfo> deleteinstanceInfos = new ArrayList<>();
        deleteinstanceInfos.addAll(instanceInfo2);
    }


    /**
     * 将所有的服务实例列表，根据名称存储在redis中
     * @param redisTemplate
     * @param iEurekaService
     */
    private void redisCacheInstanceInfo(RedisTemplate<String,List<InstanceInfo>> redisTemplate,IEurekaService iEurekaService){
        ServiceInstanceDTO serviceInstanceDTO = iEurekaService.queryEurekaApps();
        if (serviceInstanceDTO != null && serviceInstanceDTO.getApplications()!=null&&serviceInstanceDTO.getApplications().getApplication().size()>0){
            List<ApplicationInfo> applicationInfoList = serviceInstanceDTO.getApplications().getApplication();
            for (int i =0;i<applicationInfoList.size();i++){
                ApplicationInfo applicationInfo = applicationInfoList.get(i);
                //使用name 作为key 值，将实例列表存储进入redis
                ValueOperations valueOperations = redisTemplate.opsForValue();
                valueOperations.set(applicationInfo.getName(),applicationInfo.getInstance());
            }
        }
    }
}
