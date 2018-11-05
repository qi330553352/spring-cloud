package common.toolkit.java.entity.gson;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GsonResult implements Serializable {
    /**
     * 成功
     */
    public static final boolean SUCCESS     = true;
    /**
     * 失败
     */
    public static final boolean ERROR       = false;

    /**
     * 成功消息
     */
    public static final String  SUCCESS_MSG = "操作成功！";
    /**
     * 失败消息
     */
    public static final String  ERROR_MSG   = "操作失败:发生未知异常！";

    /**
     * 结果状态码(可自定义结果状态码) true:操作成功 false:操作失败
     */
    private boolean             success;
    /**
     * 响应结果描述
     */
    private String              msg;
    /**
     * 其它数据信息（比如跳转地址）
     */
    private Object              data;

    public GsonResult() {
        super();
    }

    /**
     * @param success
     *            结果状态码(可自定义结果状态码或者使用内部静态变量 true:操作成功 false:操作失败 )
     * @param msg
     *            响应结果描述
     * @param data
     *            其它数据信息（比如跳转地址）
     */
    public GsonResult(boolean success, String msg, Object data) {
        super();
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 默认操作成功结果.
     */
    public static GsonResult successResult() {
        return new GsonResult(SUCCESS, SUCCESS_MSG, null);
    }

    /**
     * 带参数操作成功结果
     */
    public static GsonResult successResult(Object data) {
        return new GsonResult(SUCCESS, SUCCESS_MSG, data);
    }

    /**
     * 默认操作失败结果.
     */
    public static GsonResult errorResult() {
        return new GsonResult(ERROR, ERROR_MSG, null);
    }

    /**
     * 带参数操作失败结果.
     */
    public static GsonResult errorResult(Object data) {
        return new GsonResult(ERROR, ERROR_MSG, data);
    }

    /**
     * 结果状态码(可自定义结果状态码) true:操作成功 false:操作失败
     */
    public boolean getSuccess() {
        return success;
    }

    /**
     * 设置结果状态码(约定 true:操作成功 false:操作失败)
     * @param success
     *            结果状态码
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * 响应结果描述
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置响应结果描述
     * @param msg
     *            响应结果描述
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 其它数据信息（比如跳转地址）
     */
    public Object getData() {
        return data;
    }

    /**
     * 设置其它数据信息（比如跳转地址）
     * @param data
     *            其它数据信息（比如跳转地址）
     */
    public void setData(Object data) {
        this.data = data;
    }

}
