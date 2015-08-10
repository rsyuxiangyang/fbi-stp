package stp.common.assistant.bean;

import stp.common.assistant.enums.IEnum;
import stp.common.assistant.exceptions.AssistantException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Java Bean 对象转换器
 * <p/>
 * User: liyd
 * Date: 13-5-8 下午4:29
 * version $Id: BeanConverter.java, v 0.1 Exp $
 */
@SuppressWarnings("unchecked")
public class BeanConverter {

    /** 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(BeanConverter.class);

    /**
     * 列表转换
     *
     * @param clazz the clazz
     * @param list the list
     * @return the page list
     */
    public static <T> List<T> convert(Class<T> clazz, List<?> list) {
        return convert(clazz, list, null);
    }

    /**
     * 列表转换
     *
     * @param clazz the clazz
     * @param list the list
     * @param ignoreProperties the ignore properties
     * @return the page list
     */
    public static <T> List<T> convert(Class<T> clazz, List<?> list, String[] ignoreProperties) {

        //返回的list列表
        List<T> resultList = new ArrayList<T>();

        if (list == null || list.isEmpty()) {
            LOG.debug("转换的列表为空，直接返回");
            return resultList;
        }

        Iterator<?> iterator = list.iterator();

        //循环调用转换单个对象
        while (iterator.hasNext()) {

            try {

                T t = clazz.newInstance();

                Object obj = iterator.next();

                t = convert(t, obj, ignoreProperties);

                resultList.add(t);

            } catch (Exception e) {
                LOG.warn("列表转换失败", e);
                throw new AssistantException(e);
            }
        }

        return resultList;
    }

    /**
     * 单个对象转换
     *
     * @param target 目标对象
     * @param source 源对象
     *
     * @return 转换后的目标对象
     */
    public static <T> T convert(T target, Object source) {
        return convert(target, source, null);
    }

    /**
     * 单个对象转换
     *
     * @param target 目标对象
     * @param source 源对象
     * @param ignoreProperties 需要过滤的属性
     *
     * @return 转换后的目标对象
     */
    public static <T> T convert(T target, Object source, String[] ignoreProperties) {

        //过滤的属性
        List<String> ignoreList = (ignoreProperties != null) ? Arrays.asList(ignoreProperties)
            : null;

        //拷贝相同的属性
        copySameProperties(target, source, ignoreList);

        return target;
    }

    /**
     * 拷贝相同的属性
     *
     * @param target the target
     * @param source the source
     * @param ignoreList the ignore list
     */
    private static void copySameProperties(Object target, Object source, List<String> ignoreList) {

        //获取目标对象属性信息
        PropertyDescriptor[] targetPds = getPropertyDescriptors(target.getClass());

        for (PropertyDescriptor targetPd : targetPds) {

            try {
                if (targetPd.getWriteMethod() == null
                    || (ignoreList != null && ignoreList.contains(targetPd.getName()))) {

                    continue;
                }

                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(),
                    targetPd.getName());

                if (sourcePd != null && sourcePd.getReadMethod() != null) {

                    Method readMethod = sourcePd.getReadMethod();
                    if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                        readMethod.setAccessible(true);
                    }
                    Object value = readMethod.invoke(source);

                    Method writeMethod = targetPd.getWriteMethod();

                    //自定义转换
                    value = typeConvert(sourcePd.getPropertyType(), targetPd.getPropertyType(),
                        value);

                    if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                        writeMethod.setAccessible(true);
                    }

                    writeMethod.invoke(target, value);
                }
            } catch (Exception e) {
                LOG.warn("Bean转换时拷贝同名的属性失败[field={}]", targetPd.getName());
                throw new AssistantException(e);
            }

        }
    }

    /**
     * 用户自定义转换
     *
     * @param sourcePropertyType the source property type
     * @param targetPropertyType the target property type
     * @param value the value
     * @return the object
     */
    private static Object typeConvert(Class<?> sourcePropertyType, Class<?> targetPropertyType,
                                      Object value) {

        Map<Class<?>, TypeConverter> converters = getConverters();

        if (converters == null || converters.size() == 0) {
            return value;
        }

        for (Map.Entry<Class<?>, TypeConverter> entry : converters.entrySet()) {

            if (!entry.getKey().isAssignableFrom(targetPropertyType)) {
                continue;
            }
            TypeConverter converter = entry.getValue();

            return converter.convert(sourcePropertyType, targetPropertyType, value);
        }
        return value;
    }

    /**
     * 注册转换器
     *
     * @param clazz the clazz
     * @param converter the converter
     */
    public static void registerConverter(Class<?> clazz, TypeConverter converter) {
        BeanConverterConfig.getInstance().registerConverter(clazz, converter);
    }

    /**
     * 移除注册的转换器
     *
     * @param clazz the clazz
     */
    public static void unregisterConverter(Class<?> clazz) {
        BeanConverterConfig.getInstance().unregisterConverter(clazz);
    }

    /**
     * 清空注册的转换器
     */
    public static void clearConverter() {
        BeanConverterConfig.getInstance().clearConverter();
    }

    /**
     * 获取注册的转换器
     * @return the converters
     */
    public static Map<Class<?>, TypeConverter> getConverters() {
        registerConverter(IEnum.class, new String2EnumConverter());
        registerConverter(String.class, new Enum2StringConverter());
        return BeanConverterConfig.getInstance().getConverters();
    }

    /**
     * 返回JavaBean所有属性的<code>PropertyDescriptor</code>
     *
     * @param beanClass the bean class
     * @return the property descriptor [ ]
     */
    private static PropertyDescriptor[] getPropertyDescriptors(Class<?> beanClass) {

        IntrospectionCache introspectionCache = IntrospectionCache.forClass(beanClass);
        return introspectionCache.getPropertyDescriptors();
    }

    /**
     * 返回JavaBean给定JavaBean给定属性的 <code>PropertyDescriptors</code>
     *
     * @param beanClass the bean class
     * @param propertyName the name of the property
     * @return the corresponding PropertyDescriptor, or <code>null</code> if none
     */
    private static PropertyDescriptor getPropertyDescriptor(Class<?> beanClass, String propertyName) {

        IntrospectionCache introspectionCache = IntrospectionCache.forClass(beanClass);
        return introspectionCache.getPropertyDescriptor(propertyName);
    }
}
