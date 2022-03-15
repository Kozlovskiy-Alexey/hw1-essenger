package by.it.academy.hw1_messenger.messenger.storage;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

public class DBInitializer {
    private volatile static DBInitializer instance;
    private ComboPooledDataSource cpds;

    private DBInitializer() throws IOException, SQLException, PropertyVetoException {
        cpds = new ComboPooledDataSource();
        cpds.setDriverClass("org.postgresql.Driver");
        cpds.setJdbcUrl("jdbc:postgresql://localhost:5433/homework?ApplicationName=Messenger");
        cpds.setUser("postgres");
        cpds.setPassword("postgre");
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        cpds.setMaxStatements(180);
    }

    public DataSource getDataSource() {
        return this.cpds;
    }

    public static DBInitializer getInstance() {
        DBInitializer result = instance;
        if (result != null) {
            return result;
        }
        synchronized (DBInitializer.class) {
            if (instance == null) {
                try {
                    instance = new DBInitializer();
                } catch (IOException | SQLException | PropertyVetoException e) {
                    throw new RuntimeException("Ошибка подключения к базе данных", e);
                }
            }
            return instance;
        }
    }
}
