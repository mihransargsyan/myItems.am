package servlet;

import com.sun.corba.se.spi.ior.iiop.IIOPProfileTemplate;
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

@WebServlet("/category")
public class CategorySservlet extends HttpServlet {

    private ItemManager itemManager = new ItemManager();
    private CategoryManager categoryManager = new CategoryManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        List<Item> itemListByCatId = itemManager.getAllItemsByCategoryId(categoryId);
        List<Category> allCategories = categoryManager.getAllCategories();
        req.setAttribute("allCategories", allCategories);
        req.setAttribute("itemListByCatId", itemListByCatId);
        req.getRequestDispatcher("/WEB-INF/category.jsp").forward(req,resp);
    }

}
