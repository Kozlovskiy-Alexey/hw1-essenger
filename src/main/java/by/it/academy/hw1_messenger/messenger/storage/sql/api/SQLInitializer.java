package by.it.academy.hw1_messenger.messenger.storage.sql.api;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

public class SQLInitializer {
    private volatile static SQLInitializer instance;
    private ComboPooledDataSource cpds;

    private SQLInitializer() throws IOException, SQLException, PropertyVetoException {
        cpds = new ComboPooledDataSource();
        cpds.setDriverClass("org.postgresql.Driver");
        cpds.setJdbcUrl("jdbc:postgresql://localhost:5433/homework?ApplicationName=Messenger");
        cpds.setUser("postgres");
        cpds.setPassword("postgre");
        cpds.setMinPoolSize(3);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        cpds.setMaxStatements(180);
    }

    public DataSource getDataSource() {
        return this.cpds;
    }

    public static SQLInitializer getInstance() {
        SQLInitializer result = instance;
        if (result != null) {
            return result;
        }
        synchronized (SQLInitializer.class) {
            if (instance == null) {
                try {
                    instance = new SQLInitializer();
                } catch (IOException | SQLException | PropertyVetoException e) {
                    throw new RuntimeException("Ошибка подключения к базе данных", e);
                }
            }
            return instance;
        }
    }
}
