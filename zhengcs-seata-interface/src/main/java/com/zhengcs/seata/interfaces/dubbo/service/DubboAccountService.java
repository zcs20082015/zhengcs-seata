package com.zhengcs.seata.interfaces.dubbo.service;


import com.zhengcs.seata.interfaces.dubbo.domain.request.AccountRequest;
import com.zhengcs.seata.interfaces.dubbo.domain.response.Result;

/**
 * @author zhengcs
 * @version 1.0
 * @description
 * @date 2020/6/11 10:52 上午
 **/
public interface DubboAccountService {

    Result debit(AccountRequest request);
}
