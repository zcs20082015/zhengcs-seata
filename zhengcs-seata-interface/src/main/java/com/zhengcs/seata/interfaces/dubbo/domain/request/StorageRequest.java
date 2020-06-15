package com.zhengcs.seata.interfaces.dubbo.domain.request;

import lombok.Builder;
import lombok.Data;

/**
 * @author zhengcs
 * @version 1.0
 * @description
 * @date 2020/6/10 4:20 下午
 **/
@Data
@Builder
public class StorageRequest extends BaseRequest {

    private Integer id;

    private String commodityCode;

    private Integer count;
}
