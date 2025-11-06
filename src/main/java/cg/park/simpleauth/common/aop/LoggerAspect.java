package cg.park.simpleauth.common.aop;

import cg.park.simpleauth.common.util.HttpRequestUtil;
import cg.park.simpleauth.common.util.PcgUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

    Logger logger = LoggerFactory.getLogger(super.getClass());

    private void beforeLog(JoinPoint joinPoint) {
        HttpServletRequest request = HttpRequestUtil.currentRequest();
        logger.info("{\"SSID\" : \"{}\", \"REQ\" : \"{}\", \"METHOD\" : \"{}\", \"URI\" : \"{}\", \"PARAM\" : {}}", HttpRequestUtil.currentSsid(), PcgUtils.currentType(joinPoint), request.getMethod(), request.getRequestURI(), PcgUtils.requestParam(joinPoint));
    }

    private void afterLog(JoinPoint joinPoint, Object retValue) {
        logger.info("{\"SSID\" : \"{}\", \"RES\" : \"{}\", \"RESULT\" : {}}", HttpRequestUtil.currentSsid(), PcgUtils.currentType(joinPoint), PcgUtils.cutString(((ResponseEntity) retValue).getBody().toString()));
    }

    @Pointcut("execution(* cg.park.simpleauth.domain..*Controller.*(..)) ")
    public void domainMethods() {}


    @Before("domainMethods()")
    public void before(JoinPoint joinPoint) {
        beforeLog(joinPoint);
    }

    @AfterReturning(pointcut = "execution(* cg.park.simpleauth.domain..*Controller.*(..)) ", returning="retValue")
    public void after(JoinPoint joinPoint, Object retValue) {
        afterLog(joinPoint, retValue);
    }

}
