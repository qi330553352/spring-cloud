/**
 * Project Name:common-toolkit
 * File Name:GridJsonData.java
 * Package Name:common.toolkit.java.util.serialization
 * Date:2013年11月25日 下午9:04:58
 * Copyright (c) 2013, www.wisdombud.com All Rights Reserved.
 */

package common.toolkit.java.entity.util.serialization;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: GridJsonData. <br/>
 * Function: TODO <br/>
 * date: 2013年11月25日 下午9:04:58 <br/>
 * @author ghlin
 * @version
 * @since JDK 1.6
 */
public class GridJsonData<T extends Serializable> {
    private long    totalProperty;
    private List<T> data = new ArrayList<T>();

    /**
     * Creates a new instance of GridJsonData.
     */

    public GridJsonData() {
    }

    /**
     * Creates a new instance of GridJsonData.
     * @param totalProperty
     * @param data
     */

    public GridJsonData(long totalProperty, List<T> data) {
        this.totalProperty = totalProperty;
        this.data = data;
    }

    /**
     * totalProperty.
     * @return the totalProperty
     * @since JDK 1.6
     */
    public long getTotalProperty() {
        return this.totalProperty;
    }

    /**
     * data.
     * @return the data
     * @since JDK 1.6
     */
    public List<T> getData() {
        return this.data;
    }

    /**
     * totalProperty.
     * @param totalProperty the totalProperty to set
     * @since JDK 1.6
     */
    public void setTotalProperty(long totalProperty) {
        this.totalProperty = totalProperty;
    }

    /**
     * data.
     * @param list the data to set
     * @since JDK 1.6
     */
    public void setData(List<T> list) {
        this.data = list;
    }

}
