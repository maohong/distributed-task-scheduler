package org.mh.dts.common.utils;


import org.mh.dts.common.annotation.Required;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * Created by maohong on 2016/12/19.
 */
public class DtsBeanUtils {

    public static void findPropertiesFromMapAndInject(Object obj, Map<String, Object> map) throws IllegalAccessException {

        if (obj == null)
            throw new RuntimeException("object is null!");

        Field fileds [] = obj.getClass().getDeclaredFields();
        for (Field field : fileds) {
            Object value = map.get(field.getName());
            Annotation annotation = field.getAnnotation(Required.class);
            if (value == null ) {
                if (annotation != null) {
                    throw new RuntimeException(String.format("parameter %s is missing", field.getName()));
                }
            }
            Class clazz = field.getType();
            if (clazz == Integer.class) {
                Integer realValue = (Integer) value;
                field.set(obj, realValue);
            }
            else if (clazz == Long.class) {
                Long realValue = (Long) value;
                field.set(obj, realValue);
            }
            else if (clazz == Double.class) {
                Double realValue = (Double) value;
                field.set(obj, realValue);
            }
            else if (clazz == String.class) {
                String realValue = (String) value;
                field.set(obj, realValue);
            }
            else if (clazz == Float.class) {
                Float realValue = (Float) value;
                field.set(obj, realValue);
            }
            else if (clazz == Boolean.class) {
                Boolean realValue = (Boolean) value;
                field.set(obj, realValue);
            }
            else if (clazz == Character.class) {
                Character realValue = (Character) value;
                field.set(obj, realValue);
            }
            else if (clazz == Short.class) {
                Short realValue = (Short) value;
                field.set(obj, realValue);
            }
            else if (clazz == Byte.class) {
                Byte realValue = (Byte) value;
                field.set(obj, realValue);
            }
            else if (clazz == List.class) {
                // TODO: 2016/12/20  
            }
        }


    }
}
