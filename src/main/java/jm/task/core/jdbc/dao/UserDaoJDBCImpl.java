package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static jm.task.core.jdbc.util.Util.connection;

public class UserDaoJDBCImpl implements UserDao, AutoCloseable {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {

        try (Statement statement = connection.createStatement()) {
            String SQL = "CREATE TABLE if not exists userTable(id LONG, name varchar(255), lastname varchar(255), age tinyint)";
            statement.executeUpdate(SQL);
        }
    }
    public void dropUsersTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String SQL = "DROP TABLE if exists userTable";
            statement.executeUpdate(SQL);
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        long ID = Math.abs(new Random().nextLong());
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO usertable VALUES(?,?,?,?)")) {
            preparedStatement.setLong(1, ID);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastName);
            preparedStatement.setByte(4, age);
            preparedStatement.executeUpdate();
        }
    }

    public void removeUserById(long id) throws SQLException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE FROM usertable WHERE `ID` = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement();) {
            String SQL = "SELECT * FROM usertable";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        }
        return  users;
    }

    public void cleanUsersTable() throws SQLException {
        try(Statement statement = connection.createStatement()) {
            String SQL = "DELETE from usertable";
            statement.executeUpdate(SQL);
        }
    }
    @Override
    public void close() throws Exception {
    super.clone();
    }
}
