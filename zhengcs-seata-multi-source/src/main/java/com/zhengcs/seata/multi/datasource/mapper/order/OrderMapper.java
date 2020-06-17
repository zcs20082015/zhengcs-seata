package com.zhengcs.seata.multi.datasource.mapper.order;

import com.zhengcs.seata.multi.datasource.entity.order.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    int insert(Order record);

}