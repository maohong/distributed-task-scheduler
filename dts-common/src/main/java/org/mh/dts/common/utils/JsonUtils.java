package org.mh.dts.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Created by maohong on 2016/12/16.
 */
public class JsonUtils {

    private static final Gson gson = new GsonBuilder()
                                    .enableComplexMapKeySerialization()
                                    .setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    public static <T> String toJsonString(T data) {

        try {
            String ret = gson.toJson(data);
            return ret;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T readObject(String jsonStr, TypeToken<T> typeToken) {
        T obj = gson.fromJson(jsonStr, typeToken.getType());
        return obj;
    }

    public static <T> T readObject(String jsonStr, Class<T> clazz) {
        T obj = gson.fromJson(jsonStr, clazz);
        return obj;
    }

    public static void main(String[] args) {



    }
}
