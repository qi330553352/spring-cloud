/**
 * Project Name:buptjob-product
 * File Name:PrefixFieldNamingStrategy.java
 * Package Name:com.wisdombud.bupt.gson
 * Date:2013年8月7日 上午10:55:43
 * Copyright (c) 2013, www.wisdombud.com All Rights Reserved.
 */

package common.toolkit.java.entity.util.serialization;

import com.google.gson.FieldNamingStrategy;
import common.toolkit.java.entity.util.StringUtil;

import java.lang.reflect.Field;

/**
 * ClassName: PrefixFieldNamingStrategy. <br/>
 * Function: TODO <br/>
 * date: 2013年8月7日 上午10:55:43 <br/>
 * @author ghlin
 * @version
 * @since JDK 1.6
 */
public class PrefixFieldNamingStrategy implements FieldNamingStrategy {

    private String prefix;

    /**
     * Creates a new instance of PrefixFieldNamingStrategy.
     * @param prefix
     */

    public PrefixFieldNamingStrategy(String prefix) {
        super();
        this.prefix = prefix;
    }

    /**
     * 给json数据添加前缀.
     * @author ghlin
     * @param f
     * @return
     * @see com.google.gson.FieldNamingStrategy#translateName(Field)
     */

    @Override
    public String translateName(Field f) {
        StringBuilder sb = new StringBuilder();
        if (StringUtil.isBlank(this.prefix)) {
            return f.getName();
        }
        return sb.append(this.prefix).append(".").append(f.getName()).toString();
    }

    /**
     * prefix.
     * @param prefix the prefix to set
     * @since JDK 1.6
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
