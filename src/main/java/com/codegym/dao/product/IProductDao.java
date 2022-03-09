package com.codegym.dao.product;

import com.codegym.dao.IGeneralDao;
import com.codegym.model.Product;

import java.util.List;

public interface IProductDao extends IGeneralDao <Product>{
    List<Product> findProductByName (String name);
}
