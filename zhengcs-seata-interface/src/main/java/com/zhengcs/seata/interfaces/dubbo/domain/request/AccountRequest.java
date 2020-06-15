package com.zhengcs.seata.interfaces.dubbo.domain.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhengcs
 * @version 1.0
 * @description
 * @date 2020/6/11 10:51 上午
 **/
@Data
@Builder
public class AccountRequest extends BaseRequest {

    private Integer id;

    private String userId;

    private BigDecimal money;

}
