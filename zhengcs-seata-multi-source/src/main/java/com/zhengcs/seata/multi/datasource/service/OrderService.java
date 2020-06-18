package com.zhengcs.seata.multi.datasource.service;

import com.zhengcs.seata.interfaces.dubbo.domain.request.AccountRequest;
import com.zhengcs.seata.multi.datasource.config.DataSourceKey;
import com.zhengcs.seata.multi.datasource.config.DynamicDataSourceContextHolder;
import com.zhengcs.seata.multi.datasource.entity.order.Order;
import com.zhengcs.seata.multi.datasource.mapper.order.OrderMapper;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.math.BigDecimal;

@Service
@Slf4j
public class OrderService {


    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private AccountService accountService;

    @GlobalTransactional
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
        accountService.debit(userId, orderMoney);
        log.info("全局事务ID[{}], 账户金额扣减成功", RootContext.getXID());
        
        throw new RuntimeException("test error");
    }
}
