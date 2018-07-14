package service.impl;

import dao.ProductDao;
import model.PageBean;
import model.Product;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import service.ProductService;
import utils.BeanFactory;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = Logger.getLogger(ProductServiceImpl.class);
    private ProductDao productDao = (ProductDao) BeanFactory.getBean("ProductDao");

    /**
     * 查找最新商品
     *
     * @return
     */
    @Override
    public List<Product> findNewProducts() {
        return productDao.findNewProducts();
    }

    /**
     * 查找热门商品
     *
     * @return
     */
    @Override
    public List<Product> findHotProducts() {
        return productDao.findHotProducts();
    }

    /**
     * 通过pid查询商品
     *
     * @param pid
     * @return
     */
    @Override
    public Product findByPid(@NotNull String pid) {
        return productDao.findByPid(pid);
    }

    /**
     * 分页查询
     *
     * @param currentPage
     * @param pageSize
     * @param cid
     * @return
     */
    @Override
    public PageBean<Product> findByPage(@NotNull int currentPage,@NotNull int pageSize,@NotNull String cid) {
        List<Product> products = productDao.findByPage(currentPage, pageSize, cid);
        PageBean<Product> productPageBean = new PageBean<Product>(products, pageSize, currentPage,
                productDao.getTotalCount(cid));
        return productPageBean;
    }
}
