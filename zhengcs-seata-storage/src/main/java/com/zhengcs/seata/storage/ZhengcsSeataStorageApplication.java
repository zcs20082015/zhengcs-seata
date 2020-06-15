package com.zhengcs.seata.storage;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.zhengcs.seata.storage")
@MapperScan(basePackages = "com.zhengcs.seata.storage.mapper")
@EnableDubbo
public class ZhengcsSeataStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZhengcsSeataStorageApplication.class, args);
	}

}
