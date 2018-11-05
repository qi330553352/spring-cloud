package common.toolkit.java.entity.util;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 转换工具类.
 * @author 尔演&Eryan eryanwcp@gmail.com
 * @date 2012-9-5 上午11:12:27
 */
public class ConvertUtils extends org.apache.commons.beanutils.ConvertUtils {

    /**
     * 提取集合中的对象的属性(通过getter函数), 组合成List.
     * @param collection 来源集合.
     * @param propertyName 要提取的属性名.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static List convertElementPropertyToList(final Collection collection, final String propertyName) {
        List list = new ArrayList();

        try {
            for (Object obj : collection) {
                list.add(PropertyUtils.getProperty(obj, propertyName));
            }
        } catch (Exception e) {
            throw ReflectUtils.convertReflectionExceptionToUnchecked(e);
        }

        return list;
    }

    /**
     * 提取集合中的对象的属性(通过getter函数), 组合成由分割符分隔的字符串.
     * @param collection 来源集合.
     * @param propertyName 要提取的属性名.
     * @param separator 分隔符.
     */
    @SuppressWarnings({ "rawtypes" })
    public static String convertElementPropertyToString(final Collection collection, final String propertyName,
            final String separator) {
        List list = convertElementPropertyToList(collection, propertyName);
        return StringUtils.join(list, separator);
    }

    /**
     * 转换字符串到相应类型.
     * @param value 待转换的字符串.
     * @param toType 转换目标类型.
     */
    public static Object convertStringToObject(String value, Class<?> toType) {
        try {
            return org.apache.commons.beanutils.ConvertUtils.convert(value, toType);
        } catch (Exception e) {
            throw ReflectUtils.convertReflectionExceptionToUnchecked(e);
        }
    }

    /**
     * 基于Apache BeanUtils转换字符串到相应类型.
     * @param value 待转换的字符串.
     * @param toType 转换目标类型.
     */
    public static Object convertToObject(String value, Class<?> toType) {
        try {
            return convert(value, toType);
        } catch (Exception e) {
            throw ReflectUtils.convertReflectionExceptionToUnchecked(e);
        }
    }

    /**
     * 转换字符串数组到相应类型.
     * @param value 待转换的字符串.
     * @param toType 转换目标类型.
     */
    public static Object convertToObject(String[] values, Class<?> toType) {
        try {
            return convert(values, toType);
        } catch (Exception e) {
            throw ReflectUtils.convertReflectionExceptionToUnchecked(e);
        }
    }
}
