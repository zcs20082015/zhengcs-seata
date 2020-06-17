package com.zhengcs.seata.multi.datasource.mapper.account;

import com.zhengcs.seata.multi.datasource.entity.account.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper {

    Account selectByUserId(@Param("userId") String userId);

    int updateById(Account record);

}