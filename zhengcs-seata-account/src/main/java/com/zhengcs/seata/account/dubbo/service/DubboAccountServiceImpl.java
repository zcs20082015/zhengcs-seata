package com.zhengcs.seata.account.dubbo.service;

import com.alibaba.fastjson.JSON;
import com.zhengcs.seata.account.service.AccountService;
import com.zhengcs.seata.interfaces.dubbo.domain.request.AccountRequest;
import com.zhengcs.seata.interfaces.dubbo.domain.response.Result;
import com.zhengcs.seata.interfaces.dubbo.service.DubboAccountService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhengcs
 * @version 1.0
 * @description
 * @date 2020/6/15 10:53 上午
 **/
@Service
@Slf4j
public class DubboAccountServiceImpl implements DubboAccountService {

    @Autowired
    private AccountService accountService;

    @Override
    public Result debit(AccountRequest request) {
        log.info("进入资金账户扣减接口，全局事务ID[{}], 请求参数：{}", RootContext.getXID(), JSON.toJSONString(request));
        accountService.debit(request.getUserId(), request.getMoney());

        return Result.success();
    }
}
