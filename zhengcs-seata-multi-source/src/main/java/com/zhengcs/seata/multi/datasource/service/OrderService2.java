package com.zhengcs.seata.multi.datasource.service;

import com.zhengcs.seata.interfaces.dubbo.domain.request.AccountRequest;
import com.zhengcs.seata.multi.datasource.entity.order.Order;
import com.zhengcs.seata.multi.datasource.mapper.order.OrderMapper;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.math.BigDecimal;


/**
 * @description 本地事务模式下多数据源事务支持
 * @author zhengcs
 * @date 2020/6/18 8:03 下午
 **/
//@Service
@Slf4j
public class OrderService2 {


    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private AccountService accountService;

    @Autowired
    @Qualifier( "orderTransactionManager")
    DataSourceTransactionManager orderTransactionManager;
    @Autowired
    @Qualifier( "accountTransactionManager")
    DataSourceTransactionManager accountTransactionManager;


    public void create(String userId, String commodityCode, Integer count) {

        //开启事务
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        /*PROPAGATION_REQUIRES_NEW:  事物隔离级别，开启新事务*/
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status =  orderTransactionManager.getTransaction(def);
        TransactionStatus status2 =  accountTransactionManager.getTransaction(def);
        try {
            BigDecimal orderMoney = new BigDecimal(count).multiply(new BigDecimal(5));
            Order order = new Order();
            order.setUserId(userId);
            order.setCommodityCode(commodityCode);
            order.setCount(count);
            order.setMoney(orderMoney);
            orderMapper.insert(order);
            log.info("全局事务ID[{}], 订单记录增加成功");


            AccountRequest accountRequest = AccountRequest.builder()
                    .userId(userId)
                    .money(orderMoney)
                    .build();
            accountService.debit(userId, orderMoney);
            log.info("全局事务ID[{}], 账户金额扣减成功");
            //int a = 1/0;
        } catch (Exception e) {
            e.printStackTrace();
            accountTransactionManager.rollback(status2);
            orderTransactionManager.rollback(status);
            return;
        }

        accountTransactionManager.commit(status2);
        orderTransactionManager.commit(status);

    }
}
