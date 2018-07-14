package web.servlet;

import model.Product;
import service.ProductService;
import utils.BeanFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class IndexServlet extends BaseServlet{
    private ProductService ps = (ProductService) BeanFactory.getBean("ProductService");
    public  String index(HttpServletRequest request, HttpServletResponse response){
        List<Product>  hotList = ps.findHotProducts();
        List<Product>  newList = ps.findNewProducts();

        request.setAttribute("hotList",hotList);
        request.setAttribute("newList",hotList);

        return "/jsp/index.jsp";
    }
}
