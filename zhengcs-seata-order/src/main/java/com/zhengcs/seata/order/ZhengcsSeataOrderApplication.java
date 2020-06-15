package com.zhengcs.seata.order;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.zhengcs.seata.order")
@MapperScan(basePackages = "com.zhengcs.seata.order.mapper")
@EnableDubbo
public class ZhengcsSeataOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZhengcsSeataOrderApplication.class, args);
	}

}
