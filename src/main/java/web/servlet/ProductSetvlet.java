package web.servlet;


import model.PageBean;
import model.Product;
import org.apache.log4j.Logger;
import service.ProductService;
import utils.BeanFactory;
import utils.NumberUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductSetvlet extends BaseServlet{
    private static final Logger LOGGER = Logger.getLogger(ProductSetvlet.class);
    private ProductService ps = (ProductService)BeanFactory.getBean("ProductService");

    /**
     * 通过id查找商品
     * @param request
     * @param response
     * @return
     */
    public String findById(HttpServletRequest request, HttpServletResponse response){
        String pid = request.getParameter("pid");
        Product product = ps.findByPid(pid);
        request.setAttribute("product",product);
        return "/jsp/product_info.jsp";
    }

    /**
     * 查询所有商品
     * @param request
     * @param response
     * @return
     */
    public String findAll(HttpServletRequest request,HttpServletResponse response){
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        String cid = request.getParameter("cid");
        PageBean<Product> products = ps.findByPage(NumberUtils.string2Int(currentPage),NumberUtils.string2Int(pageSize),cid);
        request.setAttribute("pr",products);
        return "/jsp/product_list.jsp";
    }
}
