package utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class UUIDUtilsTest {

    @Test
    public void getId() {
        assertNotNull(UUIDUtils.getId());
    }
}