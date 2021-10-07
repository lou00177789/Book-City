package com.liu.filter;

import com.liu.utils.JDBCUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author liuliang
 * @create2021-09-2021/9/7-22:35
 * @email 2640336916@qq.com
 * @explain
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JDBCUtils.commitAndClose();
        } catch (Exception e) {
            e.printStackTrace();
            JDBCUtils.rollbackAndClose();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
