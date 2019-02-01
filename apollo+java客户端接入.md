>apollo 分布式配置  [java 接入官方介绍](https://github.com/ctripcorp/apollo/wiki/Java%E5%AE%A2%E6%88%B7%E7%AB%AF%E4%BD%BF%E7%94%A8%E6%8C%87%E5%8D%97)
                    
# java  客户端接入
1.Portal地址：  
    http://apollo.bitautotech.com  
    账号信息：域账号  
2.各环境ConfigServer地址  
    Dev：http://172.20.4.59:8098  
    Pro: http://config.apollo.bitautotech.com   无端口  
## springboot接入Apollo
* 引入pom.xml
```xml
<dependency>
    <groupId>com.ctrip.framework.apollo</groupId>
    <artifactId>apollo-client</artifactId>
    <version>1.1.0</version>
</dependency>
```

* 配置文件application.properties
```application.properties
#应用名
app.id=SampleApp
#Apollo Config Service地址 可以选择环境dev or pro
apollo.meta=http://localhost:8080
#开启集成apollo ApolloAutoConfiguration类上的注解
apollo.bootstrap.enabled = true
#配置文件的命名空间 默认是application.properties,多个逗号隔开
apollo.bootstrap.namespaces = application,cheng
```  

* 获取Apollo中的配置 或者通过@Value("${redis.cache.clusterNodes}")方式获取配置信息
```java
@ConfigurationProperties(prefix = "redis.cache")    //配置文件前缀
@Component("sampleRedisConfig") //bean的name
@RefreshScope      //动态更新
public class SampleRedisConfig {

  private int expireSeconds;
  private String clusterNodes;
  private int commandTimeout;
  //set get
}
```  

* **更新配置文件**
  * 客户端和服务端保持了一个长连接，从而能第一时间获得配置更新的推送
  * 客户端还会定时从Apollo配置中心服务端拉取应用的最新配置，可以适当调长,运行时指定System Property: apollo.refreshInterval来覆盖，单位为分钟
    
注意：配置中心能保证的是配置推送到应用程序，对于一些需要初始化的场景（比如数据库连接、redis连接、zk连接等），是需要额外的逻辑来实现热切换的
如果想热切换有2种方式  
第一种是调用 post请求调用http://localhost:3005/refresh 接口  
第二种是下面这种方式
```controller
    @Autowired
    private SampleRedisConfig sampleRedisConfig;
    @Autowired
    private RefreshScope refreshScope;     //@RefreshScope 动态更新  在pom.xml中需要引入springcloud相关配置
    public void test(){
          refreshScope.refresh("sampleRedisConfig");//刷新SampleRedisConfig这个类的最新配置
    }
```