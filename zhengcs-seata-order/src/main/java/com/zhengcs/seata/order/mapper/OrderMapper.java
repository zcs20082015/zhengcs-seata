package com.zhengcs.seata.order.mapper;

import com.zhengcs.seata.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    int insert(Order record);

}