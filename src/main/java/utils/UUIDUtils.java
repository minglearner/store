package utils;

import java.util.UUID;

public class UUIDUtils {
    public static String getId() {
        String id = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        return id;
    }
}
