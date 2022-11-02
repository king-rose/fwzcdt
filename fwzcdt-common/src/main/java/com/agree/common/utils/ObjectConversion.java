package com.agree.common.utils;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * @author Tonny
 * @version 2021.2
 * @date 2022/9/7 17:11
 */

public class ObjectConversion {

    /**
     * 从List<A> copy到List<B>
     * @param list List<B>
     * @param clazz B
     * @return List<B>
     */
    public static <T> List<T> copy(List<?> list, Class<T> clazz){
        String oldOb = JSON.toJSONString(list);
        return JSON.parseArray(oldOb, clazz);
    }

    /**
     * 从对象A copy到 对象B
     * @param ob A
     * @param clazz B.class
     * @return B
     */
    public static <T> T copy(Object ob,Class<T> clazz){
        String oldOb = JSON.toJSONString(ob);
        return JSON.parseObject(oldOb, clazz);
    }
}
