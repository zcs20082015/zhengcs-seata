package com.zhengcs.seata.busi;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.zhengcs.seata.busi")
@EnableDubbo
public class ZhengcsSeataBusiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZhengcsSeataBusiApplication.class, args);
	}

}
