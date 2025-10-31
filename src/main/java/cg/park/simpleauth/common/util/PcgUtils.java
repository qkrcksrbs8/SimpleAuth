package cg.park.simpleauth.common.util;

import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

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

}
