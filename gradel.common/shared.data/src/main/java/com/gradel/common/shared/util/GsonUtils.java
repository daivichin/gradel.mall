package com.gradel.common.shared.util;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;


/**
 * @author: sdeven.chen.dongwei@gmail.com
 * @contact sdeven.chen.dongwei@gmail.com
 * @date 2019/5/5
 * @description Gson工具类
 */
public class GsonUtils {
    private static Gson gson = (new GsonBuilder()).disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    private GsonUtils() {
    }
    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T getJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public static <T> List<T> getJson(String json, Type type) {
        return (List) gson.fromJson(json, type);
    }

    public static Map<String, String> getJsonToMap(String json) {
        return (Map) gson.fromJson(json, (new TypeToken<Map<String, String>>() {
        }).getType());
    }

    public static Map<String, Object> getJsonToObjMap(String json) {
        return (Map) gson.fromJson(json, (new TypeToken<Map<String, Object>>() {
        }).getType());
    }

    public static List<Map<String, String>> getJsonToListMap(String json) {
        Type type = (new TypeToken<List<Map<String, String>>>() {
        }).getType();
        return (List) gson.fromJson(json, type);
    }

    public static List<Map<String, Object>> getJsonToListObjectMap(String json) {
        Type type = (new TypeToken<List<Map<String, Object>>>() {
        }).getType();
        return (List) gson.fromJson(json, type);
    }

    public static Map<String, Object> getJsonToObjectMap(String json) {
        return  JSONObject.parseObject(json);
    }
}
