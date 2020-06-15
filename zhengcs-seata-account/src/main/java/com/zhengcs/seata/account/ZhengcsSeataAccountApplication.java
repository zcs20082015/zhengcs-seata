package com.zhengcs.seata.account;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.zhengcs.seata.account")
@MapperScan(basePackages = "com.zhengcs.seata.account.mapper")
@EnableDubbo
public class ZhengcsSeataAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZhengcsSeataAccountApplication.class, args);
	}

}
