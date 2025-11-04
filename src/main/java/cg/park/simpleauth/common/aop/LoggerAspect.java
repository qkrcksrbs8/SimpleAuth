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

    @Pointcut("execution(* cg.park.simpleauth.domain..*Controller.*(..)) ")
    public void domainMethods() {}

    @Before("domainMethods()")
    public void before(JoinPoint joinPoint) {
        HttpServletRequest request = HttpRequestUtil.currentRequest();
        logger.info("{\"SSID\" : \"{}\", \"METHOD\" : \"{}\", \"URI\" : \"{}\", \"REQ\" : \"{}\", \"PARAM\" : {}}", HttpRequestUtil.currentSsid(), request.getMethod(), request.getRequestURI(), PcgUtils.currentType(joinPoint), PcgUtils.requestParam(joinPoint));
    }

    @AfterReturning(
        pointcut =
            "execution(* cg.park.simpleauth.domain..*Controller.*(..)) ", returning="retValue"
    )
    public void after(JoinPoint joinPoint, Object retValue) {
        logger.info("{\"SSID\" : \"{}\", \"RES\" : \"{}\", \"RESULT\" : {}}", HttpRequestUtil.currentSsid(), PcgUtils.currentType(joinPoint), PcgUtils.cutString(((ResponseEntity) retValue).getBody().toString()));
    }

}
