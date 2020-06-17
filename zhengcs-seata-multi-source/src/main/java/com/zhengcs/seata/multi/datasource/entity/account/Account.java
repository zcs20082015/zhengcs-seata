package com.zhengcs.seata.multi.datasource.entity.account;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Account {
    private Integer id;

    private String userId;

    private BigDecimal money;

}