package cg.park.simpleauth.common.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.UUID;

public class HttpRequestUtil {

    public static HttpServletRequest currentRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    public static HttpSession currentSession() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
    }

    public static HttpServletResponse currentResponse () {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
    }

    public static String currentSsid() {
        String ssid = PcgUtils.convertString(currentSession().getAttribute("PCG-SSID"));
        if (PcgUtils.isBlank(ssid)) {
            ssid = UUID.randomUUID().toString().replaceAll("-", "");
            currentSession().setAttribute("PCG-SSID", ssid);
        }
        return ssid;
    }
}
