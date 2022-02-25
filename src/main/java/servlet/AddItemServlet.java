package servlet;

import manager.ItemManager;
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
    ItemManager itemManager = new ItemManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        double price = Double.parseDouble(req.getParameter("price"));
        int categoryId = Integer.parseInt(req.getParameter("category_id"));
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        int userId = user.getId();
        Item item = Item.builder()
                .title(title)
                .price(price)
                .categoryID(categoryId)
                .userID(userId)
                .build();
        for (Part part : req.getParts()) {
            if (getFileName(part) != null) {
                String fileName = System.currentTimeMillis() + getFileName(part);
                String fullFileName = UPLOAD_DIR + File.separator + fileName;
                part.write(fullFileName);
                item.setPicURL(fileName);
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
