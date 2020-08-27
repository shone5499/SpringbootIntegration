package com.shone.elasticsearchstudy.common.aspect;

import com.shone.elasticsearchstudy.common.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;

/**
 * AOP 记录用户操作日志
 *
 * @author xiaoguojian
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(* com.shone.elasticsearchstudy.controller.*.*(..))")
    public void pointcut() {
        // do nothing
    }

    @Before("pointcut()")
    public void doBefore(JoinPoint point) {
        startTime.set(System.currentTimeMillis());
        // 获取 request
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        // 设置 IP 地址
        String ip = IpUtil.getIpAddr(request);

        log.info("REQ_URL:{}, ARGS:{}, METHOD:{}, CLI_IP:{}", request.getRequestURL().toString(),
                Arrays.asList(point.getArgs()), request.getMethod(), ip);

    }

    /**
     * 执行完成后，记录相关信息
     */
    @After("pointcut()")
    public void doAfter() {
        // 记录执行时间
        log.info("EXE_TIME: {}", System.currentTimeMillis() - startTime.get());
        startTime.remove();
    }
}
