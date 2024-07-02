package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection;
    public UserDaoJDBCImpl() {
        this.connection = Util.connect();

    }

    public void createUsersTable() {
    try (Statement statement = connection.createStatement()) {
        statement.execute(
                "CREATE TABLE IF NOT EXISTS users(" +
                        "id BIGSERIAL PRIMARY KEY," +
                        "name VARCHAR(255)," +
                        "lastname VARCHAR (255)," +
                        "age SMALLINT );"
        );
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }

    public void dropUsersTable() {
    try(Statement statement = connection.createStatement()) {
        statement.executeUpdate("DROP TABLE  IF EXISTS users");
    }catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO  users(name,lastname,age) VALUES (?,?,?)")) {
            statement.setString(1,name);
            statement.setString(2,lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
    try(PreparedStatement statement = connection.prepareStatement(
            "DELETE FROM users WHERE id=?")) {
        statement.setLong(1,id);

        if(statement.executeUpdate() > 0) {
            System.out.println("Пользователь с ID " + id + " удален");
        }else {
            System.out.println("Пользователь с ID " + id + " не найден");
        }
    }catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setAge(resultSet.getByte("age"));
                user.setLastName(resultSet.getString("lastName"));
                users.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении пользователей из базы данных", e);
        }

        return users;
    }

    public void cleanUsersTable() {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users")) {
            int rowsAffected = statement.executeUpdate();
            System.out.println("Удалено из таблицы пользователей: " + rowsAffected + " строк");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при очистке таблицы пользователей", e);
        }

    }
}
