package com.lyf.utils;

/**
 * @author LYF_
 * @create 2022-05-09 10:06
 */
/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lyf.constant.ExceptionCodeEnum;
import com.lyf.exception.BusinessException;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author Mark sunlightcs@gmail.com
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R setData(Object object){
        put("data", object);
        return this;
    }

    public <T> T getData(TypeReference<T> typeReference){
        Object data = get("data");
        String str = JSON.toJSONString(data);
        T t = JSON.parseObject(str, typeReference);
        return t;
    }

    public <T> T getMsg(TypeReference<T> typeReference){
        Object data = get("msg");
        String str = JSON.toJSONString(data);
        T t = JSON.parseObject(str, typeReference);
        return t;
    }

    public R() {
        put("code", 200);
        put("msg", "success");
    }

    public static R error(Integer code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R error(ExceptionCodeEnum e) {
        R r = new R();
        r.put("code", e.getCode());
        r.put("msg", e.getMsg());
        return r;
    }

    public static R error(BusinessException e) {
        R r = new R();
        r.put("code", e.getCode());
        r.put("msg", e.getMsg());
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public int getCode() {
        return (Integer)this.get("code");
    }
}

