package com.xiaoshan.common;

import com.xiaoshan.common.helper.CurrentRequestHelper;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by admin on 2017/7/11.
 */
public class AuthFunctionInterceptor extends HandlerInterceptorAdapter{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //只拦截api请求
        if (!request.getRequestURI().contains("/api/")) {
            return true;
        }
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            AllowAnonymous allow = handlerMethod.getMethod().getAnnotation(AllowAnonymous.class);
            return allow != null || CurrentRequestHelper.getCurrentUserId() > 0;
        }
        return true;
//        return super.preHandle(request, response, handler);
    }
}
