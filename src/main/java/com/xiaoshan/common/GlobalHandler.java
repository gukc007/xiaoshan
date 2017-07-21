package com.xiaoshan.common;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by gukc007 on 2017-05-07.
 */
@ControllerAdvice
public class GlobalHandler implements ResponseBodyAdvice<Object>{

    private static final String errorMsg = "{\"code\":\"%s\",\"msg\":\"%s\",\"hasException\":1}";
    private static final String jsonOkMsg = "{\"code\":\"0\",\"data\":\"%s\"}";

    @ExceptionHandler(value = Exception.class)
    @ResponseBody //在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
    public String exceptionHandler(HttpServletRequest req, Exception e) throws Exception {
//        if (e instanceof MethodArgumentNotValidException) {
//            //参数校验异常
//            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
//            if (ex.getBindingResult().hasErrors()) {
//                Optional<ObjectError> firstError = ex.getBindingResult().getAllErrors().stream().findFirst();
//                if (firstError.isPresent()) {
//                    return String.format(errorMsg, "500", firstError.get().getDefaultMessage());
//                }
//            }
//        }
        if (e instanceof BusinessExceptionBase) {
            //业务异常
            BusinessExceptionBase businessExceptionBase = (BusinessExceptionBase) e;
            return String.format(errorMsg, businessExceptionBase.getCode(), businessExceptionBase.getMessage());
        }
        return String.format(errorMsg, "500", e.getMessage());
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        String name = methodParameter.getMethod().getName();
        return !(name.equals("uiConfiguration") || name.equals("swaggerResources") || name.equals("getDocumentation"));
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        serverHttpResponse.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
//        serverHttpResponse.getHeaders().set("Access-Control-Allow-Origin", "*");
        if (o instanceof String ) {
            String str = ((String) o).replaceAll("\n|\r", "");
            if (str.contains("\"hasException\":1")) {
                if (str.contains("\"code\":\"401\"")) {
                    serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
                }
                return str;
            }else {
                return String.format(jsonOkMsg, str);
            }
        }
        return new ResponseBase("200", o);
    }

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(LocalDateTime.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
