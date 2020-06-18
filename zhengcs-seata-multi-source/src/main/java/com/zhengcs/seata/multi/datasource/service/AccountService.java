package com.zhengcs.seata.multi.datasource.service;

import com.zhengcs.seata.multi.datasource.config.DataSourceKey;
import com.zhengcs.seata.multi.datasource.config.DynamicDataSourceContextHolder;
import com.zhengcs.seata.multi.datasource.entity.account.Account;
import com.zhengcs.seata.multi.datasource.mapper.account.AccountMapper;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class AccountService {

    private static final String ERROR_USER_ID = "1002";

    @Autowired
    private AccountMapper accountMapper;

    public void debit(String userId, BigDecimal num) {
        log.info("账户扣减接口，全局事务ID：{}", RootContext.getXID());
        Account account = accountMapper.selectByUserId(userId);
        account.setMoney(account.getMoney().subtract(num));
        if(account.getMoney().compareTo(BigDecimal.ZERO) < 0){
            throw new RuntimeException("account branch exception");
        }
        accountMapper.updateById(account);
    }

}
