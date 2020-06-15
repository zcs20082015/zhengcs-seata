package com.zhengcs.seata.storage.util;

import org.springframework.beans.BeanUtils;

/**
 * @author zhengcs
 * @version 1.0
 * @description
 * @date 2020/6/10 4:33 下午
 **/
public class BeanUtil {

    public static <T> T copy(Object source, Class<T> target) throws IllegalAccessException, InstantiationException {
        T t = target.newInstance();
        BeanUtils.copyProperties(source, t);
        return t;
    }
}
