package com.bm.common.aspect;

import com.bm.common.annotation.Logs;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author hex
 * @create 2022-05-24
 */
@Slf4j
@Aspect
@Component
public class LogsAspect {

	@Pointcut("@annotation(com.bm.common.annotation.Logs)")
	public void cutOffPoint() {

	}

	@Before("cutOffPoint()")
	public void before() {
		log.info("------执行方法之前");
	}

	@After("cutOffPoint()")
	public void after() {
		log.info("------执行方法之后");
	}

	@Around("cutOffPoint() && @annotation(logs)")
	public Object around(ProceedingJoinPoint pjp, Logs logs) throws Throwable {
		Object obj = null;
		try {
			Long startTime = System.currentTimeMillis();
			// 日志
			String logStr = logs.value();
			// ip
			String ip = getIp();
			// 类名
			String className = pjp.getTarget().getClass().getName();
			// 方法名
			String method = pjp.getSignature().getName();
			// 参数
			String params = Arrays.toString(pjp.getArgs());
			// 返回值
			obj = pjp.proceed();
			log.info("logStr:" + logStr + " ip:" + ip);
			log.info("类名:" + className + " 方法:" + method);
			log.info("入参:" + params + " 出参 :" + obj);
			log.info("耗时:" + (System.currentTimeMillis() - startTime));
		} catch (Throwable e) {
			log.error(e.getMessage());
			throw e;
		}
		return obj;
	}

	private String getIp() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
