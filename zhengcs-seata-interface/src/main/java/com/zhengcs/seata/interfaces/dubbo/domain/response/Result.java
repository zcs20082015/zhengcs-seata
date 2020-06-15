package com.zhengcs.seata.interfaces.dubbo.domain.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhengcs
 * @version 1.0
 * @description
 * @date 2020/6/9 10:57 上午
 **/
@Data
public class Result<T> implements Serializable {
    private T data;

    private boolean success = Boolean.FALSE;

    private String errorMsg;

    public static <T> Result success(){

        return success(null);
    }

    public static <T> Result success(T data){
        Result r = new Result();
        r.data = data;
        r.success = Boolean.TRUE;
        return r;
    }

    public static Result error(String msg){
        Result r = new Result();
        r.errorMsg = msg;
        r.success = Boolean.FALSE;
        return r;
    }

}
