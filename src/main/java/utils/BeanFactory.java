package utils;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

/**
 * 根据id获取实体类
 */
public class BeanFactory {
    private static final Logger LOGGER = Logger.getLogger(BeanFactory.class);

    public static Object getBean(@NotNull String id){
        try {
            //获取document对象
            Document document = new SAXReader().read(BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml"));
            //获取指定bean对象
            Element el = (Element)document.selectSingleNode("//bean[@id='"+id+"']");
            //获取bean对象属性
            String value = el.attributeValue("class");
            //反射
            return Class.forName(value).getDeclaredConstructor().newInstance();
        } catch (DocumentException e) {
            LOGGER.error("get document erro"+e.getMessage(),e);
        } catch (IllegalAccessException e) {
            LOGGER.error("get Object erro"+e.getMessage(),e);
        } catch (InstantiationException e) {
            LOGGER.error("get Object erro"+e.getMessage(),e);
        } catch (ClassNotFoundException e) {
            LOGGER.error("get Object erro"+e.getMessage(),e);
        } catch (NoSuchMethodException e) {
            LOGGER.error("get Object erro"+e.getMessage(),e);
        } catch (InvocationTargetException e) {
            LOGGER.error("get Object erro"+e.getMessage(),e);
        }
        return null;
    }
}
