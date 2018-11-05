package common.toolkit.java.entity.util.serialization;

import com.google.gson.Gson;

public class FormJsonData {
    private boolean success;
    private Object  data;

    /**
     * Creates a new instance of FormJsonData.
     * @param success
     * @param data
     */
    public FormJsonData(boolean success, Object data) {
        super();
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
