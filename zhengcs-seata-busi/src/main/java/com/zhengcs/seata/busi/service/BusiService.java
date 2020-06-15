package com.zhengcs.seata.busi.service;

import com.alibaba.fastjson.JSON;
import com.zhengcs.seata.interfaces.dubbo.domain.request.OrderRequest;
import com.zhengcs.seata.interfaces.dubbo.domain.request.StorageRequest;
import com.zhengcs.seata.interfaces.dubbo.domain.response.Result;
import com.zhengcs.seata.interfaces.dubbo.service.DubboOrderService;
import com.zhengcs.seata.interfaces.dubbo.service.DubboStorageService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @author zhengcs
 * @version 1.0
 * @description
 * @date 2020/6/15 7:42 下午
 **/
@Service
@Slf4j
public class BusiService {

    @Reference(url = "dubbo://localhost:20882", check = false)
    private DubboStorageService dubboStorageService;
    @Reference(url = "dubbo://localhost:20881", check = false)
    private DubboOrderService dubboOrderService;

    /**
     * 减库存，下订单
     *
     * @param userId
     * @param commodityCode
     * @param orderCount
     */
    @GlobalTransactional(name = "purchase")
    public void purchase(String userId, String commodityCode, int orderCount) {
        log.info("purchase begin ... xid: " + RootContext.getXID());

        StorageRequest storageRequest = StorageRequest.builder()
                .commodityCode(commodityCode)
                .count(orderCount)
                .build();
        Result<Boolean> storageResult = dubboStorageService.decreaseStorage(storageRequest);
        log.info("库存扣减结果：{}", JSON.toJSONString(storageResult));
        if(!storageResult.isSuccess()){
            throw new RuntimeException("库存扣减异常");
        }

        OrderRequest orderRequest = OrderRequest.builder()
                .userId(userId)
                .commodityCode(commodityCode)
                .count(orderCount)
                .build();
        Result orderResult = dubboOrderService.createOrder(orderRequest);
        log.info("订单创建结果：{}", JSON.toJSONString(orderResult));
        if(!orderResult.isSuccess()){
            throw new RuntimeException("订单创建异常");
        }

        log.info("事务ID[{}]，下单成功", RootContext.getXID());
    }
}
