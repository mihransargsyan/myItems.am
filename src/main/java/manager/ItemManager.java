package manager;

import db.DBConnectionProvider;
import model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private UserManager userManager = new UserManager();
    private CategoryManager categoryManager = new CategoryManager();

    public void addItem(Item item) {
        String sql = "INSERT INTO item(title, price, category_id, pic_url, user_id) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, item.getTitle());
            statement.setDouble(2, item.getPrice());
            statement.setInt(3, item.getCategory().getId());
            statement.setString(4, item.getPicURL());
            statement.setInt(5, item.getUser().getId());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                item.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        String sql = "select * from item  order by id desc limit 20;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getInt("id"));
                item.setTitle(resultSet.getString("title"));
                item.setPrice(resultSet.getDouble("price"));
                item.setCategory(categoryManager.getCategoryById(resultSet.getInt("category_id")));
                item.setPicURL(resultSet.getString("pic_url"));
                item.setUser(userManager.getUserById(resultSet.getInt("user_id")));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public List<Item> getAllItemsByUserId(int id) {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM item WHERE user_id =" + id;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getInt("id"));
                item.setTitle(resultSet.getString("title"));
                item.setPrice(resultSet.getDouble("price"));
                item.setCategory(categoryManager.getCategoryById(resultSet.getInt("category_id")));
                item.setPicURL(resultSet.getString("pic_url"));
                item.setUser(userManager.getUserById(resultSet.getInt("user_id")));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public List<Item> getAllItemsByCategoryId(int id) {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM item  WHERE category_id = ? order by id desc limit 20";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getInt("id"));
                item.setTitle(resultSet.getString("title"));
                item.setPrice(resultSet.getDouble("price"));
                item.setCategory(categoryManager.getCategoryById(resultSet.getInt("category_id")));
                item.setPicURL(resultSet.getString("pic_url"));
                item.setUser(userManager.getUserById(resultSet.getInt("user_id")));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public void deleteItemById(int id) {
        String sql = "DELETE  FROM item WHERE id = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Item getItemById(int id) {
        String sql = "select * from item where id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return Item.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .price(resultSet.getDouble(3))
                        .category(categoryManager.getCategoryById(resultSet.getInt(4)))
                        .picURL(resultSet.getString(5))
                        .user(userManager.getUserById(resultSet.getInt(6)))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Item> getLast20Items() {
        String sql = "select * from item order by id desc limit 20";
        List<Item> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(Item.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .price(resultSet.getDouble(3))
                        .category(categoryManager.getCategoryById(resultSet.getInt(4)))
                        .picURL(resultSet.getString(5))
                        .user(userManager.getUserById(resultSet.getInt(6)))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Item> getLast20ItemsByCategory(int categoryId) {
        String sql = "select * from item where category_id=" + categoryId + " order by id desc limit 20";
        List<Item> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(Item.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .price(resultSet.getDouble(3))
                        .category(categoryManager.getCategoryById(resultSet.getInt(4)))
                        .picURL(resultSet.getString(5))
                        .user(userManager.getUserById(resultSet.getInt(6)))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Item> getAllUserItems(int id) {
        String sql = "select * from item where user_id = " + id;
        List<Item> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(Item.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .price(resultSet.getDouble(3))
                        .category(categoryManager.getCategoryById(resultSet.getInt(4)))
                        .picURL(resultSet.getString(5))
                        .user(userManager.getUserById(resultSet.getInt(6)))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}


