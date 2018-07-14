package web.servlet;

import model.Category;
import org.apache.log4j.Logger;
import service.CategoryService;
import utils.BeanFactory;
import utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CategoryServlet extends BaseServlet{
    private static final Logger LOGGER = Logger.getLogger(CategoryServlet.class);
    private CategoryService cs = (CategoryService) BeanFactory.getBean("CategoryService");

    public String findAll(HttpServletRequest request, HttpServletResponse response) {
        List<Category> categoryList = cs.findAll();
        String categorys = JsonUtils.list2Json(categoryList);
        response.setContentType("text/html;charset=utf-8");
        try {
            response.getWriter().print(categorys);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }
}
