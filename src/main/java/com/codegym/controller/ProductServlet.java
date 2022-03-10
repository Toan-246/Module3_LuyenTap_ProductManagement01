package com.codegym.controller;

import com.codegym.dao.product.ProductDao;
import com.codegym.model.Category;
import com.codegym.model.Product;
import com.codegym.service.category.CategoryService;
import com.codegym.service.category.ICategoryService;
import com.codegym.service.product.IProductService;
import com.codegym.service.product.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    IProductService productService = new ProductService(new ProductDao());
    ICategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
                List<Category> categories = categoryService.findAll();
                request.setAttribute("categoryList", categories);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/product/create.jsp");
                requestDispatcher.forward(request, response);
                break;
            }
            case "edit": {
                List<Category> categories = categoryService.findAll();
                request.setAttribute("categories", categories);
                int id = Integer.parseInt(request.getParameter("id"));
                Product product = productService.findById(id);
                request.setAttribute("product", product);
                Category productCategory = categoryService.findCategoryByProductId(id);
                request.setAttribute("productCategory", productCategory);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/product/edit.jsp");
                requestDispatcher.forward(request, response);
                break;
            }
            case "delete": {
                int id = Integer.parseInt(request.getParameter("id"));
                Product product = productService.findById(id);
                request.setAttribute("product", product);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/product/delete.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case "view": {
                int id = Integer.parseInt(request.getParameter("id"));
                Product product = productService.findById(id);
                request.setAttribute("product", product);
                Category category = categoryService.findCategoryByProductId(id);
                request.setAttribute("category", category);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/product/view.jsp");
                dispatcher.forward(request, response);
                break;
            }
            default: {
                String q = request.getParameter("q");
                if (q == null) {
                    List<Product> products = productService.findAll();
                    List<Category> categories = categoryService.findAll();
                    request.setAttribute("productList", products);
                    request.setAttribute("categoryList", categories);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/product/list.jsp");
                    requestDispatcher.forward(request, response);
                }
                else {
                    List <Product> products = productService.findProductByName(q);
                    request.setAttribute("productList", products);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/product/list.jsp");
                    requestDispatcher.forward(request, response);
                }
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
                String name = request.getParameter("name");
                double price = Double.parseDouble(request.getParameter("price"));
                String description = request.getParameter("description");
                int category_id = Integer.parseInt(request.getParameter("category_id"));
                Product product = new Product(name, price, description, category_id);
                productService.create(product);
                response.sendRedirect("/products");
                break;
            }
            case "edit": {
                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                double price = Double.parseDouble(request.getParameter("price"));
                String description = request.getParameter("description");
                int category_id = Integer.parseInt(request.getParameter("category_id"));
                Product product = new Product(id,name, price, description, category_id);
                productService.updateById(id, product);
                response.sendRedirect("/products");
                break;
            }
            case "delete": {
                int id = Integer.parseInt(request.getParameter("id"));
                productService.deleteById(id);
                response.sendRedirect("/products");
                break;
            }
        }
    }
}
