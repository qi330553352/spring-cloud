package common.toolkit.java.entity.web.struts2.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import common.toolkit.java.entity.constant.ResultConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@SuppressWarnings("all")
public class XSSCheckInterceptor extends AbstractInterceptor {
    private static Logger   logger   = Logger.getLogger(XSSCheckInterceptor.class);
    private static String   errorPath;                                             // 出错跳转的目的地
    private static String[] excludePaths;                                          // 不进行拦截的url
    private static String[] safeless = {
            "<script",   // 需要拦截的JS字符关键字
            "</script", "<iframe", "</iframe", "<frame", "</frame", "set-cookie", "%3cscript", "%3c/script",
            "%3ciframe", "%3c/iframe", "%3cframe", "%3c/frame", "src=\"javascript:", "<body", "</body", "%3cbody",
            "%3c/body",
                                     // "<",
                                     // ">",
                                     // "</",
                                     // "/>",
                                     // "%3c",
                                     // "%3e",
                                     // "%3c/",
                                     // "/%3e"
                                     };

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        final ActionContext context = invocation.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        Enumeration params = request.getParameterNames();

        boolean isSafe = true;
        String requestUrl = request.getRequestURI();
        if (isSafe(requestUrl)) {
            requestUrl = requestUrl.substring(requestUrl.indexOf("/"));
            if (!excludeUrl(requestUrl)) {
                while (params.hasMoreElements()) {
                    String cache = request.getParameter((String) params.nextElement());
                    if (StringUtils.isNotBlank(cache)) {
                        if (!isSafe(cache)) {
                            isSafe = false;
                            break;
                        }
                    }
                }
            }
        } else {
            isSafe = false;
        }

        if (!isSafe) {
            return ResultConstant.TO_HOME;
        }
        return invocation.invoke();
    }

    private static boolean isSafe(String str) {
        if (StringUtils.isNotBlank(str)) {
            for (String s : safeless) {
                if (str.toLowerCase().contains(s)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean excludeUrl(String url) {
        if (excludePaths != null && excludePaths.length > 0) {
            for (String path : excludePaths) {
                if (url.toLowerCase().equals(path)) {
                    return true;
                }
            }
        }
        return false;
    }
}
