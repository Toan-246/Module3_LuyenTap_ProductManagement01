package com.codegym.service.category;

import com.codegym.dao.category.CategoryDao;
import com.codegym.dao.category.ICategoryDao;
import com.codegym.model.Category;
import com.codegym.model.Product;

import java.util.List;

public class CategoryService implements ICategoryService{
    private ICategoryDao categoryDao = new CategoryDao();

    public CategoryService() {
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category findById(int id) {
        return categoryDao.findById(id);
    }

    @Override
    public boolean create(Category category) {
        return categoryDao.create(category);
    }

    @Override
    public boolean updateById(int id, Category category) {
        return categoryDao.updateById(id, category);
    }

    @Override
    public boolean deleteById(int id) {
        return categoryDao.deleteById(id);
    }


}
