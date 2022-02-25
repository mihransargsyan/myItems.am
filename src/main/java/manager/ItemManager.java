package manager;

import db.DBConnectionProvider;
import model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void addItem(Item item) {
        String sql = "INSERT INTO item(title, price, category_id, pic_url, user_id) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, item.getTitle());
            statement.setDouble(2, item.getPrice());
            statement.setInt(3, item.getCategoryID());
            statement.setString(4, item.getPicURL());
            statement.setInt(5, item.getUserID());
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
                item.setCategoryID(resultSet.getInt("category_id"));
                item.setPicURL(resultSet.getString("pic_url"));
                item.setUserID(resultSet.getInt("user_id"));
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
                item.setCategoryID(resultSet.getInt("category_id"));
                item.setPicURL(resultSet.getString("pic_url"));
                item.setUserID(resultSet.getInt("user_id"));
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
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getInt("id"));
                item.setTitle(resultSet.getString("title"));
                item.setPrice(resultSet.getDouble("price"));
                item.setCategoryID(resultSet.getInt("category_id"));
                item.setPicURL(resultSet.getString("pic_url"));
                item.setUserID(resultSet.getInt("user_id"));
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

}


