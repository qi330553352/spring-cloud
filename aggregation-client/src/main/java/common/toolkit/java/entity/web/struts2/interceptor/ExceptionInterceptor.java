package common.toolkit.java.entity.web.struts2.interceptor;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import common.toolkit.java.entity.exception.ServiceException;
import common.toolkit.java.entity.exception.WisdombudException;
import common.toolkit.java.entity.gson.GsonResult;
import common.toolkit.java.entity.util.ExceptionsUtil;
import common.toolkit.java.entity.web.struts2.utils.Struts2Utils;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.StaleObjectStateException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义struts2 Action层异常拦截器. <br>
 * 如果是Ajax请求发生的异常(包含Action层抛出的业务异常)则以Ajax方式返回，否则返回500错误页面. <br>
 * 1、Hibernate Validator校验运行时异常. <br>
 * 2、外键关联引用处理. <br>
 * 3、Hibernate乐观锁 并发异常处理. <br>
 * 4、参数类异常 Spring Assert、Apache Common Validate抛出该异常. <br>
 * 5、NullPointerException空指针异常. <br>
 * 6、ServiceException业务异常. <br>
 * 7、SystemException系统异常 <br>
 * 8、其它异常,无法处理的异常返回异常相信信息.
 * @author 尔演&Eryan eryanwcp@gmail.com
 * @date 2012-10-29 上午9:05:20
 */
@SuppressWarnings("serial")
public class ExceptionInterceptor extends AbstractInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionInterceptor.class);

    @SuppressWarnings("unchecked")
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        GsonResult result;
        try {
            // 递归调用拦截器
            return invocation.invoke();
        } catch (Exception e) {
            logger.error(e.getMessage());
            // 非Ajax请求 将跳转到500错误页面
            // if(!WebUtils.isAjaxRequest(Struts2Utils.getRequest())){
            // throw e;
            // }
            // Ajax方式返回错误信息
            String emsg = e.getMessage();
            StringBuilder sb = new StringBuilder();
            final String MSG_DETAIL = " 详细信息:";
            boolean isWarn = false;// 是否是警告级别的异常
            Object obj = null;// 其它信息
            sb.append("发生异常:");
            // Hibernate Validator Bo注解校验异常处理
            if (ExceptionsUtil.isCausedBy(e, ConstraintViolationException.class)) {
                sb.append("存在数据被引用,无法执行操作！");
            }
            // Hibernate 外键关联引用操作异常.
            else if (ExceptionsUtil.isCausedBy(e, org.hibernate.exception.ConstraintViolationException.class)) {
                isWarn = true;
                sb.append("存在数据被引用,无法执行操作！");
            }
            // Hibernate乐观锁 并发异常处理
            else if (ExceptionsUtil.isCausedBy(e, StaleObjectStateException.class)) {
                isWarn = true;
                sb.append("当前记录已被其它用户修改或删除！");
            } else if (ExceptionsUtil.isCausedBy(e, ObjectNotFoundException.class)) {
                sb.append("当前记录不存在或已被其它用户删除！");
            }
            // 参数类异常 Spring Assert、Apache Common Validate抛出该异常
            else if (ExceptionsUtil.isCausedBy(e, IllegalArgumentException.class)) {
                isWarn = true;
                sb.append(emsg);// 将":"替换为","
            }
            // 空指针异常
            else if (ExceptionsUtil.isCausedBy(e, NullPointerException.class)) {
                sb.append("程序没写好,发生空指针异常！");
            }
            // 业务异常
            else if (ExceptionsUtil.isCausedBy(e, ServiceException.class)) {
                ServiceException serviceException = (ServiceException) e;
                result = new GsonResult(GsonResult.ERROR, serviceException.getMessage(), null);
                Struts2Utils.renderText(new Gson().toJson(result));// 以Text方式返回json异常字符串 兼容IE
                return null;
            }
            // 系统异常
            else if (ExceptionsUtil.isCausedBy(e, WisdombudException.class)) {
                sb.append(emsg);// 将":"替换为","
            }
            // 其它异常
            else {
                sb.append("未知异常！");
            }
            result = new GsonResult(GsonResult.ERROR, sb.toString(), obj);
            logger.error(result.toString());
            Struts2Utils.renderText(new Gson().toJson(result));// 以Text方式返回json异常字符串 兼容IE
            return null;
        }
    }
}