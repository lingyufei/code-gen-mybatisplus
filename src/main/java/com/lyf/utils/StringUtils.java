package com.lyf.utils;

import com.google.common.base.CaseFormat;
import org.springframework.util.ObjectUtils;

public class StringUtils {
    public static String LowerUnderscoreToLowerCamel(String underscore){
        if (!org.springframework.util.StringUtils.hasText(underscore)) {
            return "";
        }
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, underscore);
    }

    public static String LowerUnderScoreToUpperCamel(String underscore){
        if (!org.springframework.util.StringUtils.hasText(underscore)) {
            return "";
        }
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, underscore);
    }
}
