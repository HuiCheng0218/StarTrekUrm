package com.chngenesis.sartrekurm.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: LJP
 * @Date: 2019/2/15 11:17
 */
public final class MapTemplate {

    public static Map<String, Object> success(){
        Map<String, Object> base = new HashMap<>();
        base.put("error", false);
        base.put("message", Constants.OPERATION_SUCCESS);
        return base;
    }

    public static Map<String, Object> success(String successMessage){
        Map<String, Object> base = new HashMap<>();
        base.put("error", false);
        base.put("message", successMessage);
        return base;
    }

    public static  Map<String, Object> success(String key, Object value){
        Map<String, Object> base = new HashMap<>();
        base.put("error", false);
        base.put("message", Constants.OPERATION_SUCCESS);
        base.put(key, value);
        return base;
    }

    public static Map<String, Object> error(){
        Map<String, Object> base = new HashMap<>();
        base.put("error", true);
        base.put("message", Constants.OPERATION_FAIL);
        return base;
    }

    public static Map<String, Object> error(String errorMessage){
        Map<String, Object> base = new HashMap<>();
        base.put("error", true);
        base.put("message", errorMessage);
        return base;
    }

    public static Map<String, Object> error(Exception e){
        Map<String, Object> base = new HashMap<>();
        base.put("error", true);
        base.put("message", Constants.OPERATION_FAIL);
        base.put("exception", e.getMessage());
        return base;
    }
}
