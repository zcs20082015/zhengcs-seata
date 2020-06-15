package com.zhengcs.seata.order.service;

import com.zhengcs.seata.interfaces.dubbo.domain.request.AccountRequest;
import com.zhengcs.seata.interfaces.dubbo.service.DubboAccountService;
import com.zhengcs.seata.order.entity.Order;
import com.zhengcs.seata.order.mapper.OrderMapper;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class OrderService {


    @Autowired
    private OrderMapper orderMapper;

    @Reference(url = "dubbo://localhost:20883")
    private DubboAccountService dubboAccountService;

    public void create(String userId, String commodityCode, Integer count) {
        BigDecimal orderMoney = new BigDecimal(count).multiply(new BigDecimal(5));
        Order order = new Order();
        order.setUserId(userId);
        order.setCommodityCode(commodityCode);
        order.setCount(count);
        order.setMoney(orderMoney);

        orderMapper.insert(order);
        log.info("全局事务ID[{}], 订单记录增加成功", RootContext.getXID());
        AccountRequest accountRequest = AccountRequest.builder()
                .userId(userId)
                .money(orderMoney)
                .build();
        dubboAccountService.debit(accountRequest);
        log.info("全局事务ID[{}], 账户金额扣减成功", RootContext.getXID());
    }
}
