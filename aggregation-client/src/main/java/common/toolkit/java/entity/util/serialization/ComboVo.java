/**
 * Project Name:tailaikesms-common
 * File Name:ComboVo.java
 * Package Name:com.tailaike.tree.entity
 * Date:2013年11月25日 下午4:54:05
 * Copyright (c) 2013, www.wisdombud.com All Rights Reserved.
 */

package common.toolkit.java.entity.util.serialization;

import java.io.Serializable;

/**
 * ClassName: ComboVo. <br/>
 * Function: TODO <br/>
 * date: 2013年11月25日 下午4:54:05 <br/>
 * @author ghlin
 * @version
 * @since JDK 1.6
 */
public class ComboVo<T extends Serializable> {
    private String name;
    private T      value;

    /**
     * Creates a new instance of ComboVo.
     */

    public ComboVo() {
    }

    /**
     * Creates a new instance of ComboVo.
     * @param name
     * @param value
     */

    public ComboVo(String name, T value) {
        this.name = name;
        this.value = value;
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
     * @return the value
     * @since JDK 1.6
     */
    public T getValue() {
        return this.value;
    }

    /**
     * name.
     * @param name the name to set
     * @since JDK 1.6
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * value.
     * @param value the value to set
     * @since JDK 1.6
     */
    public void setValue(T value) {
        this.value = value;
    }

}
