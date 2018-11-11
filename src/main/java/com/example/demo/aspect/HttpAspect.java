package com.example.demo.aspect;

import com.example.demo.controller.studentController;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * AOP请求处理
 */
@Aspect
@Component
public class HttpAspect {
    private final  static Logger logger = LoggerFactory.getLogger (studentController.class);
    @Pointcut("execution(public * com.example.demo.controller.studentController.*(..))")//@Pointcut 定义需要请求的方法
    public void log() {

    }
    @Before("log()")              //@Before注解请求方法前调用log类
    public void before(JoinPoint joinpoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes ();
        HttpServletRequest request= attributes.getRequest ();
        //url
        logger.info("url：{}",request.getRequestURL ());
        //method
        logger.info("method：{}",request.getMethod ());
        //ip
        logger.info("method：{}",request.getRemoteAddr ());
        //类方法
        logger.info("method：{}",joinpoint.getSignature ().getDeclaringTypeName ()+"."+joinpoint.getSignature ().getName ());
        //参数
        logger.info("args：{}",joinpoint.getArgs ());


    }

    @After("log()")           //@After注解请求方法后调用let类
    public void after() {
        System.out.println ("22222222222222222222222222");
    }

    @AfterReturning(returning = "object" ,pointcut = "log()")
    public void  doafterReturning( Object object){
        logger.info ("response={}",object);
    }
}
