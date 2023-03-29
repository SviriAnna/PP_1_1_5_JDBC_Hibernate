package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static jm.task.core.jdbc.util.Util.getConnection;


public class UserDaoJDBCImpl implements UserDao, AutoCloseable {

    public UserDaoJDBCImpl() {

    }
    UserDaoJDBCImpl userDaoJDBC;
    public void createUsersTable() throws SQLException {

        try (Statement statement = getConnection().createStatement()) {
            String SQL = "CREATE TABLE if not exists userTable(id bigINT AUTO_INCREMENT, name varchar(255), lastname varchar(255), age tinyint, PRIMARY KEY (id))";
            statement.executeUpdate(SQL);
        }
    }
    public void dropUsersTable() throws SQLException {
        try (Statement statement = getConnection().createStatement()) {
            String SQL = "DROP TABLE if exists userTable";
            statement.executeUpdate(SQL);
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try (PreparedStatement preparedStatement =
                     getConnection().prepareStatement("INSERT INTO usertable VALUES(id,?,?,?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        }
    }

    public void removeUserById(long id) throws SQLException {
        try (PreparedStatement preparedStatement =
                     getConnection().prepareStatement("DELETE FROM usertable WHERE `ID` = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();) {
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
        try(Statement statement = getConnection().createStatement()) {
            String SQL = "DELETE from usertable";
            statement.executeUpdate(SQL);
        }
    }
    @Override
    public void close() throws Exception {
    super.clone();
    }
}
