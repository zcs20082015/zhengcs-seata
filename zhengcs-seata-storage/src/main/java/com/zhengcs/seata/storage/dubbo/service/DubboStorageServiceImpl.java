package com.zhengcs.seata.storage.dubbo.service;

import com.alibaba.fastjson.JSON;
import com.zhengcs.seata.interfaces.dubbo.domain.request.StorageRequest;
import com.zhengcs.seata.interfaces.dubbo.domain.response.Result;
import com.zhengcs.seata.interfaces.dubbo.service.DubboStorageService;
import com.zhengcs.seata.storage.service.StorageService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhengcs
 * @version 1.0
 * @description
 * @date 2020/6/10 4:27 下午
 **/
@Service
@Slf4j
public class DubboStorageServiceImpl implements DubboStorageService {

    @Autowired
    private StorageService storageService;

    @Override
    public Result<Boolean> decreaseStorage(StorageRequest request){
        log.info("进入库存扣减接口，全局事务ID[{}], 请求参数：{}", RootContext.getXID(), JSON.toJSONString(request));
        storageService.deduct(request.getCommodityCode(), request.getCount());
        return Result.success();
    }
}
