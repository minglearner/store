package utils;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.xml.XMLSerializer;

import java.util.List;
import java.util.Map;

/**
 * 转化json字符串
 */
public class JsonUtils {
    /**
     * 数组转换成json
     * @param objects
     * @return
     */
    public static String array2Json(Object[] objects){
        JSONArray jsonArray = JSONArray.fromObject(objects);
        return jsonArray.toString();
    }

    /**
     * 对象转化成json
     * @param object
     * @return
     */
    public static String object2Json(Object object){
        JSONObject jsonObject = JSONObject.fromObject(object);
        return jsonObject.toString();
    }

    /**
     * 集合转化成json
     * @param list
     * @return
     */
    public static String list2Json(List list){
        JSONArray jsonArray = JSONArray.fromObject(list);
        return jsonArray.toString();
    }

    /**
     * map转化成json
     * @param map
     * @return
     */
    public static String map2Json(Map map){
        JSONObject jsonArray = JSONObject.fromObject(map);
        return jsonArray.toString();
    }

    /**
     * xml转化成json
     * @param xml
     * @return
     */
    public static String xml2Json(String xml){
        JSONArray jsonArray = (JSONArray) new XMLSerializer().read(xml);
        return jsonArray.toString();
    }

    /**
     *除去不想生成的对象(特别适合级联对象)
     * @param excludes
     * @return
     */
    public static JsonConfig configJson(String[] excludes){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(excludes);
        jsonConfig.setIgnoreDefaultExcludes(true);
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        return jsonConfig;
    }
}
