package utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.List;

/**
 * 处理cookie
 */
public class CookieUtils {

    public static Cookie getCookieByName(@NotNull String name,@NotNull Cookie[] cookies){
         List<Cookie> cookieList = Arrays.asList(cookies);
         if(CollectionUtils.isNotEmpty(cookieList)){
              Cookie cookie = cookieList.stream().filter(cook -> StringUtils.equalsIgnoreCase(name,cook.getName())).
                      findFirst().get();
              return cookie;
         }
         return null;
    }
}
