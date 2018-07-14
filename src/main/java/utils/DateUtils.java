package utils;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式工具类
 */
public class DateUtils {
    private static final Logger LOGGER = Logger.getLogger(DateUtils.class);
    public static Date getDate(String time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            LOGGER.error("date format error"+e.getMessage(),e);
        }
        return date;
    }
}
