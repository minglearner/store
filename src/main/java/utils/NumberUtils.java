package utils;


import org.apache.log4j.Logger;

public class NumberUtils {
    private static final Logger LOGGER = Logger.getLogger(NumberUtils.class);

    public static int string2Int(String word) {
        try {
            return Integer.parseInt(word);
        } catch (NumberFormatException e) {
            LOGGER.error("transform error:" + e.getMessage(), e);
        }
        return 0;
    }
}
