package com.xmy.blog.handler;

import com.sun.scenario.animation.shared.AnimationAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xmy
 * @date 2021/3/7 8:02 下午
 */
//定义这个类，来拦截处理所有的异常
@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        //记录错误信息
        logger.error("Request url:{},Exception :{}",request.getRequestURL(),e);

        //当存在状态码时，异常交由SpringBoot拦截，自己不拦截
        if (AnnotationUtils.findAnnotation(e.getClass(),ResponseStatus.class) != null) {
            throw e;
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURL());
        mv.addObject("exception",e);
        mv.setViewName("error/error");
        return mv;
    }
}
