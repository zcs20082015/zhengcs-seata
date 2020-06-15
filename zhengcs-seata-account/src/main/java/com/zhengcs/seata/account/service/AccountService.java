package com.zhengcs.seata.account.service;

import com.zhengcs.seata.account.entity.Account;
import com.zhengcs.seata.account.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {

    private static final String ERROR_USER_ID = "1002";

    @Autowired
    private AccountMapper accountMapper;

    public void debit(String userId, BigDecimal num) {
        Account account = accountMapper.selectByUserId(userId);
        account.setMoney(account.getMoney().subtract(num));
        if(account.getMoney().compareTo(BigDecimal.ZERO) < 0){
            throw new RuntimeException("account branch exception");
        }
        accountMapper.updateById(account);
    }

}
