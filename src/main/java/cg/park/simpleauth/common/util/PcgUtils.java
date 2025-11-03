package cg.park.simpleauth.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Component
public final class PcgUtils {

    public static boolean isBlank(Object obj) {
        return StringUtils.isBlank((String) obj);
    }
    public static boolean isBlank(String str) {
        return StringUtils.isBlank(str);
    }

    public static boolean isBlank(String st1, String str2) {
        return StringUtils.isBlank(st1) || StringUtils.isBlank(str2);
    }

    public static boolean isBlank(String st1, String str2, String str3) {
        return StringUtils.isBlank(st1) || StringUtils.isBlank(str2) || StringUtils.isBlank(str3);
    }

    public static String currentType(JoinPoint joinPoint) {
        return joinPoint.getSignature().toShortString();
    }

    public static String convertString(String str) {
        return str == null ? "" : str;
    }

    public static String convertString(Object obj) {
        try {
            String str = (String) obj;
            return convertString(str);
        }
        catch (Exception e) {
            return "";
        }
    }

    public static String requestParam(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] paramNames = signature.getParameterNames(); // 파라미터 이름
        Object[] paramValues = joinPoint.getArgs();          // 값

        Map<String, Object> params = new LinkedHashMap<>();
        for (int i = 0; i < paramNames.length; i++) {
            params.put(paramNames[i], paramValues[i]);
        }

        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(params);
        } catch (Exception e) {
            json = "";
        }

        return json;
    }

    public static String cutString(Object obj) {
        try {
            return cutString(obj.toString());
        }
        catch (Exception e) {
            return "";
        }
    }

    public static String cutString(Object obj, int max) {
        try {
            return cutString(obj.toString(), max);
        }
        catch (Exception e) {
            return "";
        }
    }

    public static String cutString(String str) {
        return cutString(str, 500);
    }

    public static String cutString(String str, int max) {
        return isBlank(str)
            ? ""
            : str.length() > max ? str.substring(0, max)+"..." : str;
    }
}
