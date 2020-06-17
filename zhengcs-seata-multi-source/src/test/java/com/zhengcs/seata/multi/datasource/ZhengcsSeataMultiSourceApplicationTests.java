package com.zhengcs.seata.multi.datasource;

import com.zhengcs.seata.multi.datasource.service.AccountService;
import com.zhengcs.seata.multi.datasource.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class ZhengcsSeataMultiSourceApplicationTests {

	@Autowired
	private OrderService orderService;

	@Autowired
	AccountService accountService;

	@Test
	void test() {

		orderService.create("001", "123", 10);

	}

	@Test
	void testA(){
		accountService.debit("001", new BigDecimal(10));
	}

}
