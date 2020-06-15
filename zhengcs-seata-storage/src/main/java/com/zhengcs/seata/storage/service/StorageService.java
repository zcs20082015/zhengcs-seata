package com.zhengcs.seata.storage.service;

import com.alibaba.fastjson.JSON;
import com.zhengcs.seata.interfaces.dubbo.domain.request.StorageRequest;
import com.zhengcs.seata.storage.entity.Storage;
import com.zhengcs.seata.storage.mapper.StorageMapper;
import com.zhengcs.seata.storage.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhengcs
 * @version 1.0
 * @description
 * @date 2020/6/10 2:17 下午
 **/
@Service
@Slf4j
public class StorageService {

    @Autowired
    private StorageMapper storageMapper;

    public Storage insert(StorageRequest request) throws Exception{
        log.info("库存新增接口，请求参数：{}", JSON.toJSONString(request));
        Storage storage = BeanUtil.copy(request, Storage.class);
        storageMapper.insert(storage);
        throw new RuntimeException("test error");
    }

    public void deduct(String commodityCode, int count) {
        //There is a latent isolation problem here.
        //I hope that users can solve it and deepen their understanding of seata isolation.
        //At the bottom I will put a reference solution.
        Storage storage = storageMapper.findByCommodityCode(commodityCode);
        storage.setCount(storage.getCount() - count);
        storageMapper.updateById(storage);
    }
}
