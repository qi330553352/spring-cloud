package common.toolkit.java.entity.util;

/**
 * Description: Boolean util class.
 * @author SEAN
 */
public class QuartzUtil {

    public static final int UNIT_MINUTE = 1;
    public static final int UNIT_HOUR   = 2;
    public static final int UNIT_DAY    = 3;

    /**
     * 拼接Quartz表达式
     * @param period
     * @param unit
     * @return
     */
    public static String getQuartzStr(Integer period, Integer unit) {
        String ret = null;
        if (period != null && unit != null) {
            //UNIT_MINUTE
            if (UNIT_MINUTE == unit) {
                if (1 == (period.longValue())) {
                    ret = "0 * * * * ?";
                } else {
                    ret = "0 0/" + period.longValue() + " * * * ?";
                }
            }
            //UNIT_HOUR
            if (UNIT_HOUR == unit) {
                if (1 == (period.longValue())) {
                    ret = "0 0 * * * ?";
                } else {
                    ret = "0 0 0/" + period.longValue() + " * * ?";
                }
            }
            //UNIT_DAY
            if (UNIT_DAY == unit) {
                if (1 == (period.longValue())) {
                    ret = "0 0 0 * * ?";
                } else {
                    ret = "0 0 0 1/" + period.longValue() + " * ?";
                }
            }
        }
        return ret;
    }

    public static String getLinuxStyleCron(Integer period, Integer unit) {
        String ret = null;
        if (period != null && unit != null) {
            //UNIT_MINUTE
            if (UNIT_MINUTE == unit) {
                ret = "*/" + period.longValue() + " * * * *";
            }
            //UNIT_HOUR
            if (UNIT_HOUR == unit) {
                ret = "* */" + period.longValue() + " * * *";
            }
            //UNIT_DAY
            if (UNIT_DAY == unit) {
                ret = "* * */" + period.longValue() + " * *";
            }
        }
        return ret;
    }
}
