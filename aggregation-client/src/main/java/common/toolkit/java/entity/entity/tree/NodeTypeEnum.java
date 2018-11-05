/**
 * Project Name:tailaikesms-common
 * File Name:NodeTypeEnum.java
 * Package Name:com.tailaike.sms.enums
 * Date:2013年11月22日 上午11:30:55
 * Copyright (c) 2013, www.wisdombud.com All Rights Reserved.
 */

package common.toolkit.java.entity.entity.tree;

/**
 * ClassName: NodeTypeEnum.
 * date: 2013年11月22日 上午11:30:55
 * @author SEAN
 * @version
 * @since JDK 1.6
 */
public enum NodeTypeEnum {
    ROOT(-1, "root"), ROOTRES(-2, "rootres"), RES(-3, "res"), OBJ(-4, "obj"), CAUSE(-5, "cause"), TERMINAL(-6,
            "terminal"), CITY(-7, "city"), SITE(-8, "site"), GROUP(-9, "group"), OTHER(-10, "other"),
    // 片区
    DISTRICT(-11, "district"),
    // 国家
    COUNTRY(-12, "country"),
    // 大区
    REGION(-13, "region");

    private int    value;
    private String name;

    NodeTypeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * value.
     * @return the value
     * @since JDK 1.6
     */
    public int getValue() {
        return this.value;
    }

    /**
     * name.
     * @return the name
     * @since JDK 1.6
     */
    public String getName() {
        return this.name;
    }

    /**
     * value.
     * @param value the value to set
     * @since JDK 1.6
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * name.
     * @param name the name to set
     * @since JDK 1.6
     */
    public void setName(String name) {
        this.name = name;
    }

    public static NodeTypeEnum valueOfIndex(int index) {
        for (NodeTypeEnum nodeType : NodeTypeEnum.values()) {
            if (nodeType.getValue() == index) {
                return nodeType;
            }
        }
        return null;
    }

}
