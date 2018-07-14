package dao;

import model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findNewProducts();

    List<Product> findHotProducts();

    Product findByPid(String pid);

    List<Product> findByPage(int currPage, int pageSize, String cid);

    int getTotalCount(String cid);
}
