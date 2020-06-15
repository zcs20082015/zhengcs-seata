package com.zhengcs.seata.interfaces.dubbo.service;

import com.zhengcs.seata.interfaces.dubbo.domain.request.StorageRequest;
import com.zhengcs.seata.interfaces.dubbo.domain.response.Result;

/**
 * @author zhengcs
 * @version 1.0
 * @description
 * @date 2020/6/10 4:13 下午
 **/
public interface DubboStorageService {

    /**
     * 库存扣减
     * @param request
     */
    Result<Boolean> decreaseStorage(StorageRequest request);
}
