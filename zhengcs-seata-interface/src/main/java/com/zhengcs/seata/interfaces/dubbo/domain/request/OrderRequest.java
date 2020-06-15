package com.zhengcs.seata.interfaces.dubbo.domain.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhengcs
 * @version 1.0
 * @description
 * @date 2020/6/12 2:02 下午
 **/
@Data
@Builder
public class OrderRequest extends BaseRequest {

    private Integer id;

    private String userId;

    private String commodityCode;

    private Integer count;

    private BigDecimal money;
}
