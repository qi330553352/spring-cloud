/**
 * Project Name:common-toolkit
 * File Name:ComboJsonDao.java
 * Package Name:common.toolkit.java.util.serialization
 * Date:2013年11月25日 下午9:06:37
 * Copyright (c) 2013, www.wisdombud.com All Rights Reserved.
 */

package common.toolkit.java.entity.util.serialization;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * ClassName: ComboJsonData. <br/>
 * Function: TODO <br/>
 * date: 2013年11月25日 下午9:06:37 <br/>
 * @author ghlin
 * @version
 * @since JDK 1.6
 */
public class ComboJsonData<T extends Serializable> {
    private Collection<ComboVo<T>> data;

    /**
     * Creates a new instance of ComboJsonData.
     */

    public ComboJsonData() {
    }

    /**
     * Creates a new instance of ComboJsonData.
     * @param data
     */

    public ComboJsonData(List<ComboVo<T>> data) {
        this.data = data;
    }

    /**
     * data.
     * @return the data
     * @since JDK 1.6
     */
    public Collection<ComboVo<T>> getData() {
        return this.data;
    }

    /**
     * data.
     * @param data the data to set
     * @since JDK 1.6
     */
    public void setData(Collection<ComboVo<T>> data) {
        this.data = data;
    }

}
