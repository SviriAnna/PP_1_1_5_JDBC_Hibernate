package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
/*import jm.task.core.jdbc.dao.UserDaoJDBCImpl;*/
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl userService =  new UserServiceImpl();
        DriverManager.getConnection("jdbc:mysql://localhost:3306/usersDataBase", "root", "root");
        userService.createUsersTable();

        userService.saveUser("Name1", "LastName1", (byte) 20);
        userService.saveUser("Name2", "LastName2", (byte) 25);
        userService.saveUser("Name3", "LastName3", (byte) 31);
        userService.saveUser("Name4", "LastName4", (byte) 38);

        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
