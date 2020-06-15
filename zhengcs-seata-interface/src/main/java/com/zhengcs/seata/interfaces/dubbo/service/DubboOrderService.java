package com.zhengcs.seata.interfaces.dubbo.service;

import com.zhengcs.seata.interfaces.dubbo.domain.request.OrderRequest;
import com.zhengcs.seata.interfaces.dubbo.domain.response.Result;

/**
 * @author zhengcs
 * @version 1.0
 * @description
 * @date 2020/6/12 2:04 下午
 **/
public interface DubboOrderService {

    Result<Boolean> createOrder(OrderRequest request);
}
