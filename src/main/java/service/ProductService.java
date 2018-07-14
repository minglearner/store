package service;

import model.PageBean;
import model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findNewProducts();

    List<Product> findHotProducts();

    Product findByPid(String pid);

    PageBean<Product> findByPage(int currentPage, int pageSize, String cid);
}
