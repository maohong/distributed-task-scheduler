package org.mh.dts.common.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.codehaus.jackson.map.ObjectMapper;
import org.mh.dts.common.http.bean.RequestMethod;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by maohong on 2016/12/16.
 */
public class JsonUtils {

    private static final ObjectMapper jsonMapper = new ObjectMapper();

    public static String toJsonStringFromMap(Map data) {
        if (data == null) {
            return "{}";
        }
        try {
            String ret = jsonMapper.writeValueAsString(data);
            return ret;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> String toJsonStringFromObject(T data) {

        try {
            String ret = jsonMapper.writeValueAsString(data);
            return ret;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Object readObject(String strJson, Class type) {
        JSONObject jsonObject = JSONObject.fromObject(strJson);
        return JSONObject.toBean(jsonObject, type);
    }

    public static Object readObject(String strJson, Class type, Map<String, Class> classMap) {
        JSONObject jsonObject = JSONObject.fromObject(strJson);
        return JSONObject.toBean(jsonObject, type, classMap);
    }

    public static List readList(String strJson, Class elementType) {
        JSONArray jsonArray = JSONArray.fromObject(strJson);
        Object arr[] = (Object[]) (Object[]) JSONArray.toArray(jsonArray,
                elementType);
        return Arrays.asList(arr);
    }

    public static void main(String[] args) {
        System.out.println(JsonUtils.toJsonStringFromObject(RequestMethod.RUN_TASK));


    }
}
