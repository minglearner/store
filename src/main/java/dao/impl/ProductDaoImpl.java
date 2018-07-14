package dao.impl;

import dao.ProductDao;
import model.PageBean;
import model.Product;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;
import utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private static final Logger LOGGER = Logger.getLogger(ProductDaoImpl.class);

    @Override
    public List<Product> findNewProducts(){
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product order by pdate desc limit 0,10";
        List<Product> products = null;
        try {
            products = queryRunner.query(sql, new BeanListHandler<>(Product.class));
        } catch (SQLException e) {
            LOGGER.error("not find new products:" + e.getMessage(), e);
        }
        return products;
    }

    @Override
    public List<Product> findHotProducts(){
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "SELECT * FROM product WHERE is_hot = 1 order by pdate desc limit 0,10";
        List<Product> products = null;
        try {
            products = qr.query(sql, new BeanListHandler<>(Product.class));
        } catch (SQLException e) {
            LOGGER.error("not find hot products:" + e.getMessage(), e);
        }
        return products;
    }

    @Override
    public Product findByPid(String pid){
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "SELECT * FROM product WHERE pid = ? order by pdate desc limit 0,10";
        Product product = null;
        try {
            product = qr.query(sql, new BeanHandler<>(Product.class), pid);
        } catch (SQLException e) {
            LOGGER.error("not find product by pid:" + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<Product> findByPage(int currPage, int pageSize, String cid){
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "SELECT * FROM product WHERE cid = ? order by pdate desc limit ?,?";
        List<Product> products = null;
//        PageBean pageBean = new PageBean(products, pageSize, currPage, getTotalCount(cid));
        try {
            products = qr.query(sql, new BeanListHandler<>(Product.class), cid,
                    (currPage - 1) * pageSize, pageSize);
        } catch (SQLException e) {
            LOGGER.error("not find product by page:" + e.getMessage(), e);
        }
        return products;
    }

    @Override
    public int getTotalCount(String cid){
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from product where cid = ?";
        int totalCount = 0;
        try {
            totalCount = ((Long) qr.query(sql, new ScalarHandler(), cid)).intValue();
        } catch (SQLException e) {
            LOGGER.error("not find totalCount:" + e.getMessage(), e);
        } catch (ClassCastException ce) {
            LOGGER.error("long to int error:" + ce.getMessage(), ce);
        }
        return totalCount;
    }
}
