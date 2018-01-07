package com.payroll.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Controller
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle (HttpServletRequest request,
                              HttpServletResponse response,
                              Object handler) throws Exception {
    	RequestMapping rm = null;
    	if (handler instanceof HandlerMethod) {
    		rm = ((HandlerMethod) handler).getMethodAnnotation(RequestMapping.class);
    	} else if (handler instanceof ResourceHttpRequestHandler) {
    		return true;
    	}

        boolean alreadyLoggedIn = request.getSession()
                                         .getAttribute("user") != null;
        boolean isLogin = rm != null && rm.value().length > 0
                                              && ("/login".equals(rm.value()[0]) || "/".equals(rm.value()[0]));
        boolean isDashboard = rm != null && rm.value().length > 0
                && "/home".equals(rm.value()[0]);

       if (!alreadyLoggedIn && !isLogin && !isDashboard) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }

        return true;
    }
}
