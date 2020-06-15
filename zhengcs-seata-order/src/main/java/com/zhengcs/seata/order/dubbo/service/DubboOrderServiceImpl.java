package com.zhengcs.seata.order.dubbo.service;

import com.alibaba.fastjson.JSON;
import com.zhengcs.seata.interfaces.dubbo.domain.request.OrderRequest;
import com.zhengcs.seata.interfaces.dubbo.domain.response.Result;
import com.zhengcs.seata.interfaces.dubbo.service.DubboOrderService;
import com.zhengcs.seata.order.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhengcs
 * @version 1.0
 * @description
 * @date 2020/6/12 2:12 下午
 **/
@Service
@Slf4j
public class DubboOrderServiceImpl implements DubboOrderService {

    @Autowired
    private OrderService orderService;

    @Override
    public Result<Boolean> createOrder(OrderRequest request) {
        log.info("进入下单接口，全局事务ID[{}]，请求参数：{}", RootContext.getXID(), JSON.toJSONString(request));
        orderService.create(request.getUserId(), request.getCommodityCode(),request.getCount());
        return Result.success();
    }
}
