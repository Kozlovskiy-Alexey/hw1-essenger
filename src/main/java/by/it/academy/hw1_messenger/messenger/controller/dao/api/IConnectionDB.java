package by.it.academy.hw1_messenger.messenger.controller.dao.api;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnectionDB {

    Connection connection() throws SQLException;
}
