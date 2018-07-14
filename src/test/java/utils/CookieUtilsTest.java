package utils;

import org.junit.Test;

import javax.servlet.http.Cookie;

import static org.junit.Assert.*;

public class CookieUtilsTest {

    @Test
    public void getCookieByName() {
        Cookie[] cookies = new Cookie[1];
        Cookie cookie = new Cookie("1","ok");
        cookies[0] = cookie;
        assertNotNull(CookieUtils.getCookieByName("1",cookies));
    }
}