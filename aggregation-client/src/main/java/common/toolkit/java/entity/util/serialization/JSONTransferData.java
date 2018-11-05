/**
 * Project Name:tailaikesms-web
 * File Name:JsonTransferData.java
 * Package Name:com.tailaike.sms.perf.action
 * Date:2013年11月26日 上午9:29:07
 * Copyright (c) 2013, www.wisdombud.com All Rights Reserved.
 */

package common.toolkit.java.entity.util.serialization;

import com.google.gson.annotations.SerializedName;

/**
 * ClassName: JsonTransferData.
 * date: 2013年11月26日 上午9:29:07
 * @author SEAN
 * @version
 * @since JDK 1.6
 */
@Deprecated
public class JSONTransferData<T> {
    @SerializedName(value = "success")
    private boolean success;
    @SerializedName(value = "data")
    private T       data;
    @SerializedName(value = "message")
    private String  message;

    /**
     * Creates a new instance of JSONTransferData.
     * @param success
     * @param data
     * @param message
     */

    public JSONTransferData(boolean success, T data, String message) {
        super();
        this.success = success;
        this.data = data;
        this.message = message;
    }

    /**
     * Creates a new instance of JSONTransferData.
     */

    private JSONTransferData() {
        super();
    }

    /**
     * success.
     * @return the success
     * @since JDK 1.6
     */
    public final boolean isSuccess() {
        return success;
    }

    /**
     * success.
     * @param success the success to set
     * @since JDK 1.6
     */
    public final void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * data.
     * @return the data
     * @since JDK 1.6
     */
    public final T getData() {
        return data;
    }

    /**
     * data.
     * @param data the data to set
     * @since JDK 1.6
     */
    public final void setData(T data) {
        this.data = data;
    }

    /**
     * message.
     * @return the message
     * @since JDK 1.6
     */
    public final String getMessage() {
        return message;
    }

    /**
     * message.
     * @param message the message to set
     * @since JDK 1.6
     */
    public final void setMessage(String message) {
        this.message = message;
    }

}
