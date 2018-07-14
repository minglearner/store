package utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class MD5UtilsTest {

    @Test
    public void md5() {
        String text = "saf";
        assertNotNull(MD5Utils.md5(text));
    }
}