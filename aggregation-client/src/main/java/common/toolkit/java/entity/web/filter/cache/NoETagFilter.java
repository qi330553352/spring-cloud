/*
 * Copyright 2011 samaxes.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package common.toolkit.java.entity.web.filter.cache;

import common.toolkit.java.entity.web.filter.util.HTTPCacheHeader;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * <p>
 * Filter responsible for disabling ETag header from the HTTP response.
 * <p>
 * <p>
 * Example configuration:
 * </p>
 * 
 * <pre>
 * &lt;filter&gt;
 *     &lt;filter-name&gt;noEtag&lt;/filter-name&gt;
 *     &lt;filter-class&gt;com.samaxes.filter.NoETagFilter&lt;/filter-class&gt;
 * &lt;/filter&gt;
 * </pre>
 * <p>
 * Map the filter to Tomcat&#x27;s {@code DefaultServlet}:
 * </p>
 * 
 * <pre>
 * &lt;filter-mapping&gt;
 *     &lt;filter-name&gt;noEtag&lt;/filter-name&gt;
 *     &lt;url-pattern&gt;/*&lt;/url-pattern&gt;
 * &lt;/filter-mapping&gt;
 * </pre>
 * @author Samuel Santos
 * @author John Yeary
 * @version 2.1.0
 */
public class NoETagFilter implements Filter {

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * Disables {@code ETag} HTTP header. {@inheritDoc}
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        filterChain.doFilter(servletRequest, new HttpServletResponseWrapper((HttpServletResponse) servletResponse) {
            @Override
            public void setHeader(String name, String value) {
                if (!HTTPCacheHeader.ETAG.getName().equalsIgnoreCase(name)) {
                    super.setHeader(name, value);
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
    }
}
