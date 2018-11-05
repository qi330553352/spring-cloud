package common.toolkit.java.entity.util.number;

import common.toolkit.java.entity.util.ObjectUtil;
import common.toolkit.java.entity.util.StringUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Description: Double 工具类
 * @author 银时：yinshi.nc / yinshi.nc@taobao.com
 * @Date Jan 12, 2012
 */
public class DoubleUtil {
    public static final DecimalFormat TWO_DIGIT_FORMAT = new DecimalFormat("#.00");

    /**
     * 将String转换成double类型<br>
     * 放心，这个方法不会抛出任何异常，如果不是合法的转换，会返回0d.
     * @return
     */
    public static double compareToBigger(double a, double b) {
        return a >= b ? a : b;
    }

    /**
     * 将String转换成double类型<br>
     * 放心，这个方法不会抛出任何异常，如果不是合法的转换，会返回0d.
     * @return
     */
    public static double parseDouble(String string) {
        try {
            return Double.parseDouble(StringUtil.trimToEmpty(string));
        } catch (Exception e) {
            return 0d;
        }
    }

    /**
     * return |a-b|
     */
    public static double subtractionToPositive(double a, double b) {
        return Math.abs(a - b);
    }

    /**
     * 确保不会出现异常<br>
     * 放心，这个方法不会抛出任何异常，如果不是合法的转换，会返回0d.
     * @return double
     */
    public static double trimToZero(Object o) {
        if (ObjectUtil.isBlank(o))
            return 0;
        if (o instanceof String) {
            return DoubleUtil.parseDouble((String) o);
        } else if (o instanceof Integer) {
            return new Double((Integer) o);
        }
        return (Double) o;
    }

    /**
     * 格式化数值. <br/>
     * @author ghlin
     * @param db
     * @param decFormat
     * @return
     */
    public static double formatDouble(Double db, DecimalFormat decFormat) {
        if (db == null) {
            return 0d;
        }
        return Double.parseDouble(decFormat.format(db));
    }

    /**
     * 格式化数值. <br/>
     * @author ghlin
     * @param db BigDecimal类型
     * @param decFormat
     * @return
     */
    public static double formatDouble(BigDecimal bigDecimal, DecimalFormat decFormat) {
        if (bigDecimal == null) {
            return 0d;
        }
        return DoubleUtil.formatDouble(bigDecimal.doubleValue(), decFormat);
    }

}
