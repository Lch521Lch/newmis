package com.ef.newmis.util;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

/**
 * 将json 请求 字符串，转换为 HashMap *
 *
 * @author dirxu
 * @date 2020-02-13
 */
public class JsonExtraTools {

    /**
     * 将 json 请求 字符串，转换为 HashMap
     *
     * @return HashMap
     * @date 2012-02-13
     * @author dirxu
     */

    public HashMap<String, String> parseJsonToMap(String questJson) {

        HashMap<String, String> questMap = new HashMap<>(10);

        JSONObject jsonObject = JSONObject.parseObject(questJson);

        //循环解析参数
        for (java.util.Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            questMap.put(entry.getKey().trim(), entry.getValue().toString());
        }

        return questMap;

    }
}
