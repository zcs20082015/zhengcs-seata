package com.zhengcs.seata.multi.datasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ZhengcsSeataMultiSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZhengcsSeataMultiSourceApplication.class, args);
	}

}
