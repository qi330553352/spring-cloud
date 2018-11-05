/**
 * Project Name:tailaikesms-web
 * File Name:JSONTransferArray.java
 * Package Name:com.tailaike.sms.perf.action
 * Date:2013年11月26日 上午9:35:49
 * Copyright (c) 2013, www.wisdombud.com All Rights Reserved.
 */

package common.toolkit.java.entity.util.serialization;

import com.google.gson.annotations.Expose;

import java.util.Collection;

/**
 * ClassName: JSONTransferArray.
 * date: 2013年11月26日 上午9:35:49
 * @author SEAN
 * @version
 * @since JDK 1.6
 */
@Deprecated
public class JSONTransferGrid {
    @Expose
    private Collection<?> data;
    @Expose
    private long          totalProperty;

    /**
     * Creates a new instance of JSONTransferArray.
     */

    public JSONTransferGrid() {
    }

    /**
     * Creates a new instance of JSONTransferArray.
     * @param data
     * @param totalProperty
     */
    public JSONTransferGrid(Collection<?> data, long totalProperty) {
        this.data = data;
        this.totalProperty = totalProperty;
    }

    /**
     * data.
     * @return the data
     * @since JDK 1.6
     */
    public final Collection<?> getData() {
        return data;
    }

    /**
     * data.
     * @param data the data to set
     * @since JDK 1.6
     */
    public final void setData(Collection<?> data) {
        this.data = data;
    }

    /**
     * totalProperty.
     * @return the totalProperty
     * @since JDK 1.6
     */
    public final long getTotalProperty() {
        return totalProperty;
    }

    /**
     * totalProperty.
     * @param totalProperty the totalProperty to set
     * @since JDK 1.6
     */
    public final void setTotalProperty(long totalProperty) {
        this.totalProperty = totalProperty;
    }

}
