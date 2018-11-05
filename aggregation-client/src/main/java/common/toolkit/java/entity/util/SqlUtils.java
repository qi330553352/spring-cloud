/**
 * Project Name:com.tailaike.sms
 * File Name:SqlUtils.java
 * Package Name:com.tailaike.utils
 * Date:2013年8月27日 上午9:44:09
 * Copyright (c) 2013, www.wisdombud.com All Rights Reserved.
 */

package common.toolkit.java.entity.util;


/**
 * ClassName: SqlUtils. <br/>
 * Function: TODO <br/>
 * date: 2013年8月27日 上午9:44:09 <br/>
 * @author ghlin
 * @version
 * @since JDK 1.6
 */
public final class SqlUtils {
    public static final String PERCENTAGE_SIGNAL = "%";

    private SqlUtils() {

    }

    /**
     * 这里用一句话描述这个方法的作用.
     * @author ghlin
     * @param string 元查询条件
     * @return String
     */
    public static String decrateString(final String string) {
        if (StringUtil.isBlank(string)) {
            return PERCENTAGE_SIGNAL;
        }
        return PERCENTAGE_SIGNAL + string + PERCENTAGE_SIGNAL;
    }

    /**
     * 这里用一句话描述这个方法的作用.
     * @author ghlin
     * @param string 元查询条件
     * @return String
     */
    public static String decrateFrontString(final String string) {
        if (StringUtil.isBlank(string)) {
            return PERCENTAGE_SIGNAL;
        }
        return string + PERCENTAGE_SIGNAL;
    }
}
