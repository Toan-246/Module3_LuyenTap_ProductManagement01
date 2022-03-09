package com.codegym.controller;

import com.codegym.dao.category.CategoryDao;
import com.codegym.model.Category;
import com.codegym.service.category.CategoryService;
import com.codegym.service.category.ICategoryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryServlet", value = "/categories")
public class CategoryServlet extends HttpServlet {
    ICategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/category/create.jsp");
                requestDispatcher.forward(request, response);
                break;
            }
            case "edit": {
                int id = Integer.parseInt(request.getParameter("id"));
                Category category = categoryService.findById(id);
                request.setAttribute("category", category);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/category/edit.jsp");
                requestDispatcher.forward(request, response);
                break;
            }
            case "delete": {
                int id = Integer.parseInt(request.getParameter("id"));
                Category category = categoryService.findById(id);
                request.setAttribute("category", category);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/category/delete.jsp");
                requestDispatcher.forward(request, response);
                break;
            }
            default: {
                List<Category> categories = categoryService.findAll();
                request.setAttribute("categoryList", categories);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/category/list.jsp");
                requestDispatcher.forward(request, response);
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
                Category category = new Category(name);
                categoryService.create(category);
                response.sendRedirect("/categories");
                break;
            }
            case "edit": {
                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                Category category = new Category(name);
                categoryService.updateById(id, category);
                response.sendRedirect("/categories");
                break;
            }
            case "delete": {
                int id = Integer.parseInt(request.getParameter("id"));
                categoryService.deleteById(id);
                response.sendRedirect("/categories");
                break;
            }

        }
    }
}
