package com.codegym.service.product;

import com.codegym.dao.category.ICategoryDao;
import com.codegym.dao.product.IProductDao;
import com.codegym.model.Product;

import java.util.List;

public class ProductService implements IProductService{
    IProductDao productDao;
    ICategoryDao categoryDao;

    public ProductService(IProductDao productDao) {
        this.productDao = productDao;
    }

    public ProductService(IProductDao productDao, ICategoryDao categoryDao) {
        this.productDao = productDao;
        this.categoryDao = categoryDao;

    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public Product findById(int id) {
        return productDao.findById(id);
    }

    @Override
    public boolean create(Product product) {
        return productDao.create(product);
    }

    @Override
    public boolean updateById(int id, Product product) {
        return productDao.updateById(id, product);
    }

    @Override
    public boolean deleteById(int id) {
        return productDao.deleteById(id);
    }

    @Override
    public List<Product> findProductByName(String name) {
        name = "%" + name + "%";
        return productDao.findProductByName(name);
    }

    @Override
    public List<Product> findAllProductByCategoryId(int category_id) {
        return productDao.findAllProductByCategoryId(category_id);
    }
}
