/**
 * Project Name:buptjob-product
 * File Name:PrefixFieldNamingStrategy.java
 * Package Name:com.wisdombud.bupt.gson
 * Date:2013年8月7日 上午10:55:43
 * Copyright (c) 2013, www.wisdombud.com All Rights Reserved.
 */

package common.toolkit.java.entity.gson;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang.StringUtils;

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
        if (StringUtils.isBlank(this.prefix)) {
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

    public static void main(String[] args) {
        class BObject {
            public String b;

            /**
             * b.
             * @return the b
             * @since JDK 1.6
             */
            public String getB() {
                return this.b;
            }

            /**
             * b.
             * @param b the b to set
             * @since JDK 1.6
             */
            public void setB(String b) {
                this.b = b;
            }

            public BObject(String b) {
                super();
                this.b = b;
            }

        }

        class Myclass {
            public String a;

            /**
             * a.
             * @return the a
             * @since JDK 1.6
             */
            public String getA() {
                return this.a;
            }

            /**
             * bobj.
             * @return the bobj
             * @since JDK 1.6
             */
            public BObject getBobj() {
                return this.bobj;
            }

            /**
             * a.
             * @param a the a to set
             * @since JDK 1.6
             */
            public void setA(String a) {
                this.a = a;
            }

            /**
             * bobj.
             * @param bobj the bobj to set
             * @since JDK 1.6
             */
            public void setBobj(BObject bobj) {
                this.bobj = bobj;
            }

            public BObject bobj;

            public Myclass(String a) {
                super();
                this.a = a;
            }

            public Myclass(String a, BObject bobj) {
                super();
                this.a = a;
                this.bobj = bobj;
            }
        }
        Gson gson = new GsonBuilder().setFieldNamingStrategy(new PrefixFieldNamingStrategy("test")).create();
        Myclass acls = new Myclass("a");
        System.out.println(gson.toJson(acls));
    }

}
