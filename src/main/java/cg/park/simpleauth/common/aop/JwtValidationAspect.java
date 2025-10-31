package cg.park.simpleauth.common.aop;

import cg.park.simpleauth.common.enums.ResultType;
import cg.park.simpleauth.common.util.ApiResponse;
import cg.park.simpleauth.common.util.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class JwtValidationAspect {

    @Autowired
    JwtProvider jwt;

    @Around("@annotation(cg.park.simpleauth.common.anotaion.ValidJwt)")
    public Object validJwt(ProceedingJoinPoint joinPoint) throws Throwable {

        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attrs.getRequest();
        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ApiResponse(ResultType.INVALID_TOKEN));
        }

        if (!jwt.isValid(token)) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ApiResponse(ResultType.INVALID_TOKEN));
        }

        return joinPoint.proceed();
    }

}
