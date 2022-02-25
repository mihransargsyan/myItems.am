package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import model.Category;
import model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/userHome")
public class UserHomeServlet extends HttpServlet {

    private ItemManager itemManager = new ItemManager();
    private CategoryManager categoryManager = new CategoryManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Item> allItems = itemManager.getAllItems();
        req.setAttribute("allItems", allItems);
        List<Category> allCategories = categoryManager.getAllCategories();
        req.setAttribute("allCategories", allCategories);
        req.getRequestDispatcher("/WEB-INF/userHome.jsp").forward(req, resp);
    }
}
