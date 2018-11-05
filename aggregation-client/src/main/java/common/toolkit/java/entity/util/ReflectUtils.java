/* --------------------------------------------------------
 * Copyright (c) Express Scripts, Inc.  All rights reserved.
 * --------------------------------------------------------
 */
package common.toolkit.java.entity.util;

import com.google.common.collect.Sets;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 反射工具类
 * @author ghlin
 */
public final class ReflectUtils {
    private static Log log               = LogFactory.getLog(ReflectUtils.class);
    public static final String VALUE_OF_MEHTOD   = "valueOf";
    public static final String JAVA_LANG_PACKAGE = "java.lang.";

    /**
     * set field of object value
     * @param object
     *            object
     * @param fieldName
     *            field name
     * @param value
     *            value
     * @throws Exception
     *             e
     */
    public static void setFieldValue(Object object, String fieldName, Object value) throws Exception {
        String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method method = object.getClass().getMethod(methodName, new Class[] { value.getClass() });
        method.invoke(object, new Object[] { value });
    }

    /**
     * set field of object value
     * @param object
     *            object
     * @param name
     *            field name
     * @return object
     * @throws Exception
     *             e
     */
    public static Object getFieldValue(Object object, String name) {
        String[] fieldNames = name.split("\\.");
        for (String fieldName : fieldNames) {
            if (object == null) {
                return null;
            }
            String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Method method;
            try {
                method = object.getClass().getMethod(methodName, new Class[] {});
                object = method.invoke(object);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }
        return object;
    }

    public static boolean hasField(Class<?> clazz, String fieldName) {
        boolean flag = false;
        Field[] declaredFs = clazz.getDeclaredFields();
        Field[] fs = clazz.getFields();
        Set<String> properties = Sets.newHashSet();
        for (Field f : fs) {
            properties.add(f.getName());
        }
        for (Field f : declaredFs) {
            properties.add(f.getName());
        }
        flag = properties.contains(fieldName.trim());
        return flag;
    }

    /**
     * 将源对象拷贝到目标对象
     * @param sourceObj
     * @param destObj
     * @param excludeProperties
     * @throws Exception
     */
    public static void copySource2Dest(Object sourceObj, Object destObj, List<String> excludeProperties)
            throws Exception {
        Class<?> sourceClazz = sourceObj.getClass();
        Class<?> destClazz = destObj.getClass();
        Field[] desitionFields = destClazz.getDeclaredFields();
        Field[] superHistFields = destClazz.getSuperclass().getDeclaredFields();
        Field[] fileds = new Field[desitionFields.length + superHistFields.length];

        System.arraycopy(desitionFields, 0, fileds, 0, desitionFields.length);
        System.arraycopy(superHistFields, 0, fileds, desitionFields.length, superHistFields.length);

        for (Field field : fileds) { // 目标对象的fields
            String tmpFieldName = field.getName();
            if (!excludeProperties.contains(tmpFieldName)) {
                Object tempObj = getFieldValue(sourceObj, tmpFieldName);
                if (tempObj != null) {
                    setFieldValue(destObj, tmpFieldName, tempObj);
                }
            }
        }
    }

    /**
     * set field of object value
     * @param clazz
     *            class which has the method
     * @param methodName
     *            method name
     * @param object
     *            parameter
     * @throws Exception
     *             e
     * @return object
     */
    public static Object invokeStaticMethod(Class clazz, String methodName, Object object) throws Exception {
        Object[] objects = new Object[] { object };
        return invokeStaticMethod(clazz.getName(), methodName, objects);
    }

    /**
     * set field of object value
     * @param className
     *            class name
     * @param methodName
     *            method name
     * @param object
     *            parameter
     * @throws Exception
     *             e
     * @return object
     */
    public static Object invokeStaticMethod(String className, String methodName, Object object) throws Exception {
        Object[] objects = new Object[] { object };
        return invokeStaticMethod(className, methodName, objects);
    }

    /**
     * set field of object value
     * @param className
     *            class name
     * @param methodName
     *            method name
     * @param objects
     *            parameters
     * @throws Exception
     *             e
     * @return object
     */
    public static Object invokeStaticMethod(String className, String methodName, Object[] objects) throws Exception {
        Class[] classes = getClasses(objects);
        return invokeStaticMethod(className, methodName, classes, objects);
    }

    /**
     * set field of object value
     * @param className
     *            class name
     * @param methodName
     *            method name
     * @param objects
     *            parameters
     * @param classes
     *            Class[]
     * @throws Exception
     *             e
     * @return object
     */
    public static Object invokeStaticMethod(String className, String methodName, Class[] classes, Object[] objects)
            throws Exception {
        Class clazz = Class.forName(className);
        Method method = clazz.getMethod(methodName, classes);
        return method.invoke(clazz, objects);
    }

    /**
     * @param objects
     *            parameters
     * @return class[]
     */
    private static Class[] getClasses(Object[] objects) {
        Class[] clazzs = new Class[objects.length];
        for (int i = 0; i < clazzs.length; i++) {
            clazzs[i] = objects[i].getClass();
        }
        return clazzs;
    }

    /**
     * set field of object value
     * @param clazz
     *            clazz
     * @param fieldName
     *            field name
     * @param object
     *            parameters
     * @throws Exception
     *             e
     * @return object
     */
    public static Object setStaticField(Class clazz, String fieldName, Object object) throws Exception {
        String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method method = clazz.getMethod(methodName, object.getClass());
        return method.invoke(clazz, object);
    }

    /**
     * get field class
     * @param fieldName
     *            field name
     * @param clazz
     *            class
     * @return Class
     * @throws Exception
     *             exception
     */
    public static Class getFieldClass(String fieldName, Class clazz) throws Exception {
        Field field = clazz.getDeclaredField(fieldName);
        return field.getType();
    }

    /**
     * invoke method of object
     * @param object
     *            object
     * @param methodName
     *            method name
     * @param parameters
     *            parameters
     * @param clazzs
     *            Class[]
     * @return object
     * @throws Exception
     *             e
     */
    public static Object invokeMethod(Object object, String methodName, Class[] clazzs, Object[] parameters)
            throws Exception {
        Method method = object.getClass().getMethod(methodName, clazzs);
        Object result = method.invoke(object, parameters);
        return result;
    }

    /**
     * Gets the super class genric type.
     * @param clazz
     *            the clazz
     * @param <T>
     *            Generic Class
     * @return the super class genric type
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getSuperClassGenricType(final Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    /**
     * @author ghlin
     * @param clazz
     * @param fieldName
     * @return
     */
    public static boolean containsField(Class<?> clazz, String fieldName) {
        try {
            clazz.getDeclaredField(fieldName);
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public static Class getSuperClassGenricType(final Class clazz, final int index) {
        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            log.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            log.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: "
                    + params.length);
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            log.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
            return Object.class;
        }

        return (Class) params[index];
    }

    /**
     * 获取所有的field. <br/>
     * @author Administrator
     * @param object
     * @return
     */
    public static Field[] getAllFields(Object object) {
        Class<?> clazz = object.getClass();
        Class<?> originClazz = clazz;
        Field[] resultField = clazz.getDeclaredFields();

        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            if (clazz == originClazz) {
                continue;
            }
            try {
                Field[] superFields = clazz.getDeclaredFields();
                resultField = (Field[]) ArrayUtils.addAll(resultField, superFields);
            } catch (Exception e) {
                // 这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                // 如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
                System.out.println("error");
            }
        }

        return resultField;
    }

    /**
     * 这里用一句话描述这个方法的作用.
     * @author ghlin
     * @param srcObj 源对象
     * @param keyPrefix 前缀
     * @param excludeProperties 不需要拷贝的属性
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static Map<String, Object> getMap(Object srcObj, String keyPrefix, List<String> excludeProperties) {
        String fieldSeparate = StringUtil.isBlank(keyPrefix) ? "" : ".";
        Map<String, Object> result = new HashMap<String, Object>();
        Field[] declaredFields = ReflectUtils.getAllFields(srcObj);
        for (Field f : declaredFields) {
            if (excludeProperties.contains(f.getName())) {
                continue;
            }
            result.put(keyPrefix + fieldSeparate + f.getName(), getFieldValue(srcObj, f.getName()));
        }
        return result;
    }

    /**
     * 这里用一句话描述这个方法的作用.
     * @author ghlin
     * @param o
     * @param excludeProperties
     * @return
     */
    public static Map<String, Object> getMap(Object o, List<String> excludeProperties) {
        return getMap(o, "", excludeProperties);
    }

    /**
     * 将反射时的checked exception转换为unchecked exception.
     */
    public static RuntimeException convertReflectionExceptionToUnchecked(Exception e) {
        if (e instanceof IllegalAccessException || e instanceof IllegalArgumentException
                || e instanceof NoSuchMethodException) {
            return new IllegalArgumentException(e);
        } else if (e instanceof InvocationTargetException) {
            return new RuntimeException(((InvocationTargetException) e).getTargetException());
        } else if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        }
        return new RuntimeException("Unexpected Checked Exception.", e);
    }
}
