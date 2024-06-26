package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Alex", "Petrakov", (byte) 23);
        userService.saveUser("Ka","Peov", (byte) 23);
        userService.saveUser("Dima","Petrakov", (byte) 23);
        userService.saveUser("Sasha","Petrakov", (byte) 23);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.closeSessionFactory();
    }
}
