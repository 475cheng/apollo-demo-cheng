package com.bitauto.ep.fx.webapi.controllers;

import com.bitauto.ep.fx.webapi.entity.SampleRedisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AliveController {
    private final static Logger logger = LoggerFactory.getLogger(AliveController.class);

    //配置中心能保证的是配置推送到应用程序，对于一些需要初始化的场景（比如数据库连接、redis连接、zk连接等），是需要额外的逻辑来实现热切换的
    @Value("${redis.cache.clusterNodes}")
    private  String clusNode;
    @Autowired
    private SampleRedisConfig sampleRedisConfig;
    @Autowired
    private RefreshScope refreshScope;
    @GetMapping("/alive")
    public String getAliveStatus() {
        try {
            //refreshScope.refresh("sampleRedisConfig");//可以post请求http://localhost:8888/refresh 代替此行代码
            String s1 = sampleRedisConfig.toString();
            System.out.println(s1);
            System.out.println(clusNode);
            return s1;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
