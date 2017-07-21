package com.xiaoshan.common;

import com.xiaoshan.common.helper.CurrentRequestHelper;
import com.xiaoshan.common.helper.JavaHelper;
import com.xiaoshan.datacontract.RequestUserDto;
import com.xiaoshan.datacontract.response.UserResponse;
import com.xiaoshan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 2017/7/12.
 */
public class AuthenticationFilter extends OncePerRequestFilter implements Filter {

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (!request.getRequestURI().contains("/api")) {
            filterChain.doFilter(request, response);
            return;
        }
        String authorization = request.getHeader("Authorization");
        if (authorization == null) {
            authorization = request.getParameter("Authorization");
        }

        RequestUserDto dto = new RequestUserDto();
        if (!JavaHelper.isNullOrEmpty(authorization)) {
            try {
                UserResponse user = userService.verfiyUser(authorization);
                dto.setId(user.getId());
                dto.setAccount(user.getAccount());
                dto.setPassword(user.getPassword());
            } catch (Exception e) {
                response.sendError(401, "token失效,请求地址:" + request.getRequestURI());
                return;
            }
        }
        Authentication auth = new CurrentRequestHelper(dto);
        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request, response);
    }
}
