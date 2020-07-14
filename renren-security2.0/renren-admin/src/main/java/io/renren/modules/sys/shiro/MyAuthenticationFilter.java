package io.renren.modules.sys.shiro;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class MyAuthenticationFilter extends AccessControlFilter {
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        //若是预检请求，直接放行
        if (StringUtils.equalsIgnoreCase("OPTIONS", ((ShiroHttpServletRequest) request).getMethod())) {
            return true;
        }
        Subject subject = getSubject(request, response);
        if(subject.getPrincipal() == null) {
            return false;
        }
        return true;
    }

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(200);
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        JSONObject json = new JSONObject();
        json.put("code","401");
        json.put("msg","用户未登录");
        out.println(json);
//        httpServletResponse.sendRedirect("/login.html");
        out.flush();
        out.close();
        return false;
    }
    private boolean isAjax(ServletRequest request){
        String header = ((HttpServletRequest)request).getHeader("X-Requested-With");
        if("XMLHttpRequest".equalsIgnoreCase(header)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
