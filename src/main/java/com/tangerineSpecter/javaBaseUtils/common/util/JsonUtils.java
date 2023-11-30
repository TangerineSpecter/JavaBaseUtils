package com.tangerinespecter.javabaseutils.common.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.base.Splitter;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * json处理工具类
 *
 * @author TangerineSpecter
 * @date 2023年11月30日10:51:35
 */
@SuppressWarnings("all")
public class JsonUtils {

    /**
     * url参数处理成Map键值对形式
     *
     * @param params url参数，示例：?userid=test&meetingId=test
     * @return map数据
     */
    public static Map<String, Object> urlParam2Map(String params) {
        Map<String, Object> result = MapUtil.newHashMap();
        if (StrUtil.isBlank(params)) {
            return result;
        }
        List<String> paramGroup = StrUtil.split(params, "&");
        for (String param : paramGroup) {
            final List<String> p = StrUtil.split(param, "=");
            Object value = CollUtil.get(p, 1);
            value = Objects.equals(value, "null") ? null : value;
            result.put(CollUtil.get(p, 0), value);
        }
        return result;
    }

    /**
     * url参数处理成JSON字符串
     *
     * @param params url参数，示例：?userid=test&meetingId=test
     * @return json字符串
     */
    public static String urlParam2Json(String params) {
        return JSONUtil.toJsonStr(urlParam2Map(params));
    }

    /**
     * @param obj    转换的对象
     * @param objStr 需要转换成成对象的字符串打印结果
     * @param <T>    泛型
     * @return 解析成json格式的对象信息
     */
    public static <T> T objStr2Json(Class<T> obj, String objStr) {
        final T result = ReflectUtil.newInstance(obj);
        //获取字段map
        final Map<String, Field> fieldMap = ReflectUtil.getFieldMap(obj);

        String content = getObjStrContent(obj, objStr);
        if (content == null) {
            return result;
        }
        final List<String> fieldGroup = Splitter.on(",").splitToList(content);
        for (String fieldStr : fieldGroup) {
            handleFieldType(result, content, fieldStr, fieldMap);
        }
        return result;
    }

    /**
     * 填充对应字段的value
     *
     * @param result     返回对象
     * @param content    解析字符串内容
     * @param fieldPairs 键值对，格式：fieldName=value
     * @param fieldMap   键值对映射关系
     * @param <T>        泛型
     */
    private static <T> void handleFieldType(T result, String content, String fieldPairs, Map<String, Field> fieldMap) {
        List<String> fieldParam = Splitter.on("=").splitToList(fieldPairs);
        String paramName = CollUtil.get(fieldParam, 0);
        String paramValue = CollUtil.get(fieldParam, 1);
        paramValue = Objects.equals(paramValue, "null") ? null : paramValue;
        Field field = fieldMap.get(StrUtil.cleanBlank(paramName));
        if (field == null) {
            return;
        }

        if (checkBaseType(field.getType())) {
            ReflectUtil.setFieldValue(result, StrUtil.cleanBlank(paramName), paramValue);
        } else if (Objects.equals(field.getType(), List.class) || Objects.equals(field.getType(), Set.class)) {
            Type listElement = ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
            T eleObj = ReflectUtil.newInstance(listElement.getTypeName());
            Class<?> listEleClazz = eleObj.getClass();
            Collection<T> list = CollUtil.newArrayList();
            if (checkBaseType(listEleClazz)) {
                //常规list\set
                String[] arrElements = StrUtil.subBetweenAll(paramValue, "[", "]");
                for (String arrElement : arrElements) {
                    list.add((T) arrElement);
                }
                ReflectUtil.setFieldValue(result, field.getName(), list);
                return;
            } else {
                String[] eleContents = StrUtil.subBetweenAll(content, listEleClazz.getSimpleName() + "(", ")");
                for (String eleContent : eleContents) {
                    T newInfo = ReflectUtil.newInstance(listElement.getTypeName());
                    final Map<String, Field> elefieldMap = ReflectUtil.getFieldMap(newInfo.getClass());
                    for (String eleFieldPairs : Splitter.on(",").splitToList(eleContent)) {
                        handleFieldType(newInfo, eleContent, eleFieldPairs, elefieldMap);
                    }
                    list.add(newInfo);
                }
            }
            ReflectUtil.setFieldValue(result, field.getName(), list);
        }
    }

    /**
     * 检测基本类型，箱体类型，字符串类型
     *
     * @param type 字段类型
     * @return true：符合
     */
    private static boolean checkBaseType(Class<?> type) {
        List<Class<?>> boxedTypes = CollUtil.newArrayList(Integer.class, Boolean.class, Float.class, Byte.class, Short.class, Long.class, Double.class, Character.class);
        return type.equals(String.class) || type.isPrimitive() || boxedTypes.contains(type);
    }

    /**
     * 获取对象的内部信息
     */
    private static <T> String getObjStrContent(Class<T> clazz, String objStr) {
        objStr = StrUtil.subAfter(objStr, clazz.getSimpleName() + "(", false);
        objStr = StrUtil.subBefore(objStr, ")", true);
        return objStr;
    }
}
