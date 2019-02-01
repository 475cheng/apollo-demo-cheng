package com.bitauto.ep.fx.webapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//开启自动化配置
@EnableAutoConfiguration()
//从指定包中扫描组件
@ComponentScan("com.bitauto.ep.fx")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
