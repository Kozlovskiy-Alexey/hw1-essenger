package by.it.academy.hw1_messenger.messenger.controller.dao;

import by.it.academy.hw1_messenger.messenger.controller.dao.api.IConnectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB implements IConnectionDB {

    private final String USER = "postgres";
    private final String URL = "jdbc:postgresql://localhost:5433/homework?ApplicationName=TestMyApp";
    private final String PASSWORD = "postgre";
    private final String CLASS = "org.postgresql.Driver";

    @Override
    public Connection connection() throws SQLException {

        try {
            Class.forName(CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return  connection;

    }
}
