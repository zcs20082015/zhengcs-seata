package com.zhengcs.seata.interfaces.dubbo.domain.request;

import java.io.Serializable;

/**
 * @author zhengcs
 * @version 1.0
 * @description
 * @date 2020/6/10 4:16 下午
 **/
public class BaseRequest implements Serializable {

    private Integer page;

    private Integer pageSize;

    public void setPage(Integer page){
        if(page < 1){
            page = 1;
        }

        this.page = page;
    }

    public void setPageSize(Integer pageSize){
        if(pageSize < 1){
            pageSize = 1;
        }

        this.pageSize = pageSize;
    }

    public Integer getStart(){
        if(null == this.page){
            return null;
        }
        return (this.page - 1) * this.pageSize;
    }

    public Integer getOffset(){
        return this.pageSize;
    }
}
