package dao.impl;

import dao.CategoryDao;
import model.Category;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;
import utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private static final Logger LOGGER = Logger.getLogger(CategoryDaoImpl.class);

    @Override
    public List<Category> findAll(){
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "SELECT * FROM category";
        List<Category> categories = null;
        try {
            categories = qr.query(sql,new BeanListHandler<>(Category.class));
        } catch (SQLException e) {
            LOGGER.error("not find categories:" + e.getMessage(), e);
        }
        return categories;
    }
}
