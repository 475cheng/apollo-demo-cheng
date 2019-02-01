# apollo-demo-cheng
如果想运行这个程序，需要有自己的apollo服务端，然后修改application.properties中的配置文件  
app.id=dbg-sjgg-demoapi  
apollo.meta=http://172.20.4.88:8098  
apollo.bootstrap.namespaces =application  
然后添加几个key-value，如SampleRedisConfig这三个配置即可  
apollo+java客户端接入 有详细说明和简单图解