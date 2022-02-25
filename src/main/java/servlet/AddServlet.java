package servlet;

import manager.CategoryManager;
import model.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/add")

public class AddServlet extends HttpServlet {

    private CategoryManager categoryManager = new CategoryManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> allCategories = categoryManager.getAllCategories();
        req.setAttribute("allCategories", allCategories);
        req.getRequestDispatcher("/WEB-INF/addItem.jsp").forward(req, resp);
    }

}
