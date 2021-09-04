package com.tangerinespecter.javabaseutils.common.filter;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
@Aspect
@Component
public class ManageLogAspect {

    private static final Executor executor = Executors.newFixedThreadPool(20, r -> {
        Thread t = new Thread(r);
        t.setName("log-aspect-" + t.getId());
        /*打开守护线程*/
        t.setDaemon(true);
        return t;
    });


    @Pointcut("execution(* com.tangerinespecter.javabaseutils.controller..*.*(..))")
    public void controllerAopPointCut() {
    }


    @Before("controllerAopPointCut()")
    public void controllerReturnBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("token");
        executor.execute(() -> logAccess(token, joinPoint.getArgs(), request.getRequestURI()));
    }


    private void logAccess(String token, Object[] args, String url) {
        try {
            String userStr = "【TangerineSpecter】";
            log.info("接口日志记录, 请求url: {}, 用户信息: {}, 请求参数: {}, token：{}", url, userStr, JSON.toJSONString(args), token);
        } catch (Exception e) {
            log.warn("记录用户访问信息出错", e);
        }
    }
}
