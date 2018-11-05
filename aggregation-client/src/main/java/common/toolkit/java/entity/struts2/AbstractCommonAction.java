package common.toolkit.java.entity.struts2;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AbstractCommonAction extends ActionSupport implements ServletRequestAware, ServletResponseAware,
        SessionAware {

    private static final Logger   logger           = Logger.getLogger(AbstractCommonAction.class);
    private static final long     serialVersionUID = 1L;

    // 分页参数
    protected int                 start;
    protected int                 limit;
    protected long                totalPage;

    // combo查询参数
    protected String              query;
    // 排序信息
    protected String              sort;
    // 错误码、错误消息
    // log内容
    protected String              logMark;

    protected Map<String, Object> session;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    /**
     * 发送消息.
     * @author ghlin
     * @param isSuccess
     * @param data
     * @param msg
     */
    public <T> void sendMsg(boolean isSuccess, T data, String msg) {
        Map<String, Object> responseMap = new HashMap<String, Object>();
        responseMap.put("success", isSuccess);
        responseMap.put("data", data);
        responseMap.put("message", msg);

        this.sendResponseMsg(new Gson().toJson(responseMap));
    }

    /**
     * 发送成功消息.
     * @author ghlin
     * @param data
     * @param msg
     */
    public <T> void sendSuccessMsg(T data, String msg) {
        this.sendMsg(true, data, msg);
    }

    /**
     * 发送成功消息.
     * @author ghlin
     */
    public void sendSuccessMsg() {
        this.sendMsg(true, "", "");
    }

    /**
     * 发送失败消息.
     * @author ghlin
     * @param data
     * @param errorMsg
     */
    public <T> void sendFailMsg(T data, String errorMsg) {
        this.sendMsg(false, data, errorMsg);
    }

    /**
     * 发送数组消息.
     * @author ghlin
     */

    public <E> void sendArrayMsg(Collection<E> collectObj) {
        this.sendResponseMsg(new Gson().toJson(collectObj));
    }

    /**
     * 发送respons消息.
     * @author ghlin
     * @param jsonMsg
     */
    public void sendResponseMsg(String jsonMsg) {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            System.out.println(jsonMsg);
            writer.write(jsonMsg);
            writer.flush();
        } catch (IOException e) {
            logger.error("Json convert send to page error!", e);
        } finally {
            if (null != writer) {
                writer.close();
                writer = null;
            }
        }
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    /**
     * query.
     * @return the query
     * @since JDK 1.6
     */
    public String getQuery() {
        return query;
    }

    /**
     * query.
     * @param query the query to set
     * @since JDK 1.6
     */
    public void setQuery(String query) {
        this.query = query;
    }

    public String getLogMark() {
        return logMark;
    }

    public void setLogMark(String logMark) {
        this.logMark = logMark;
    }

    /**
     * start.
     * @return the start
     * @since JDK 1.6
     */
    public int getStart() {
        return this.start;
    }

    /**
     * limit.
     * @return the limit
     * @since JDK 1.6
     */
    public int getLimit() {
        return this.limit;
    }

    /**
     * start.
     * @param start the start to set
     * @since JDK 1.6
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * limit.
     * @param limit the limit to set
     * @since JDK 1.6
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

}
