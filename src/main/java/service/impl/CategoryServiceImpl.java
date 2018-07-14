package service.impl;

import dao.CategoryDao;
import model.Category;
import org.apache.log4j.Logger;
import service.CategoryService;
import utils.BeanFactory;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private static final Logger LOGGER = Logger.getLogger(CategoryServiceImpl.class);
    private CategoryDao categoryDao = (CategoryDao) BeanFactory.getBean("CategoryDao");

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
