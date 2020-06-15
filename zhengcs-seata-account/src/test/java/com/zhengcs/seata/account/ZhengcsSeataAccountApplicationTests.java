package com.zhengcs.seata.account;

import com.alibaba.fastjson.JSON;
import com.zhengcs.seata.interfaces.dubbo.domain.request.AccountRequest;
import com.zhengcs.seata.interfaces.dubbo.domain.response.Result;
import com.zhengcs.seata.interfaces.dubbo.service.DubboAccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
@Slf4j
class ZhengcsSeataAccountApplicationTests {

	@Reference(url = "dubbo://localhost:20883")
	DubboAccountService dubboAccountService;

	@Test
	void test() {
		AccountRequest request = AccountRequest.builder()
				.userId("001")
				.money(new BigDecimal(100))
				.build();
		Result result = dubboAccountService.debit(request);
		log.info("result: {}", JSON.toJSONString(result));
	}


}
