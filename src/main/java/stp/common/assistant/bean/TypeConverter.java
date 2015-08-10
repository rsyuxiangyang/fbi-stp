package stp.common.assistant.bean;

/**
 * 数据类型转换接口
 * <p/>
 * User: liyd
 * Date: 13-5-9 下午12:04
 * version $Id: TypeConverter.java, v 0.1 Exp $
 */
public interface TypeConverter {

    /**
     * Convert the specified input object into an output object of the
     * specified type.
     *
     * @param sourceClass the source class
     * @param targetClass Data type to which this value should be converted
     * @param value The input value to be converted
     * @return The converted value
     */
    public Object convert(Class<?> sourceClass, Class<?> targetClass, Object value);

}
