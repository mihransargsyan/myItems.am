package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import model.Category;
import model.Item;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;


@WebServlet(urlPatterns = "/addItem")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class AddItemServlet extends HttpServlet {

    private final String UPLOAD_DIR = "C:\\Users\\NZP\\IdeaProjects\\myItems.am\\src\\main\\java\\upload";
    private ItemManager itemManager = new ItemManager();
    private CategoryManager categoryManager = new CategoryManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", categoryManager.getAllCategories());
        req.getRequestDispatcher("/WEB-INF/addItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        double price = Double.parseDouble(req.getParameter("price"));
        int catId = Integer.parseInt(req.getParameter("category_id"));
        Category category = categoryManager.getCategoryById(catId);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Item item = Item.builder()
                .title(title)
                .price(price)
                .category(category)
                .user(user)
                .build();

        for (Part part : req.getParts()) {
            if (getFileName(part) != null) {
                String fileName = System.currentTimeMillis() + getFileName(part);
                String fullFileName = UPLOAD_DIR + File.separator + fileName;
                part.write(fullFileName);
                if (getFileName(part).equals("")) {
                    item.setPicURL("");
                } else {
                    item.setPicURL(fileName);
                }
            }
        }
        itemManager.addItem(item);
        resp.sendRedirect("/myItems");
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return null;
    }

}
