package by.it.academy.hw1_messenger.messenger.storage;

import by.it.academy.hw1_messenger.messenger.model.AuditUser;
import by.it.academy.hw1_messenger.messenger.model.Pageble;
import by.it.academy.hw1_messenger.messenger.model.User;
import by.it.academy.hw1_messenger.messenger.storage.api.IAuditUserStorage;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DBAuditUserStorage implements IAuditUserStorage {
    private volatile static DBAuditUserStorage instance;
    private final DataSource dataSource;

    public DBAuditUserStorage() {
        this.dataSource = DBInitializer.getInstance().getDataSource();
    }

    @Override
    public long create(AuditUser auditUser) {
        return 0;
    }

    @Override
    public List<AuditUser> read(Pageble pageble) {
        Integer limit = null;
        Integer offset = null;

        if (pageble != null) {
            if (pageble.getSize() > 0) {
                limit = pageble.getSize();
            }
            if (limit != null && pageble.getPage() > 0) {
                offset = (pageble.getPage() - 1) * limit;
            }
        }
        List<AuditUser> data = new ArrayList<>();
        String sql = "SELECT\n" +
                "    audit.id,\n" +
                "    audit.text,\n" +
                "    audit.dt_create,\n" +
                "    audit.user,\n" +
                "    author.firstname AS author_firstname,\n" +
                "    author.lastname AS author_lastname,\n" +
                "    author.birthday AS author_birthday,\n" +
                "    author.registration AS author_registration,\n" +
                "    audit.author,\n" +
                "    author_admin.firstname AS author_admin_firstname,\n" +
                "    author_admin.lastname AS author_admin_lastname,\n" +
                "    author_admin.birthday AS author_admin_birthday,\n" +
                "    author_admin.registration AS author_admin_registration\n" +
                "FROM\n" +
                "    messenger.audit_user AS audit\n" +
                "    LEFT JOIN messenger.user AS author ON audit.author = author.login\n" +
                "    LEFT JOIN messenger.user AS author_admin ON audit.user = author_admin.login";
        if (limit != null) {
            sql += " LIMIT " + limit;
        }
        if (offset != null) {
            sql += " OFFSET " + offset;
        }
        sql += ";";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                LocalDateTime dtCreate = rs.getObject("dt_create", LocalDateTime.class);
                String text = rs.getString("text");
                User user = User.Builder.createBuilder()
                        .setLogin(rs.getString("user"))
                        .setFirstName(rs.getString("author_firstname"))
                        .setLastName(rs.getString("author_lastname"))
                        .setBirthday(rs.getObject("author_birthday", LocalDate.class))
                        .setRegistration(rs.getObject("author_registration", LocalDateTime.class))
                        .build();
                User author = User.Builder.createBuilder()
                        .setLogin(rs.getString("author"))
                        .setFirstName(rs.getString("author_admin_firstname"))
                        .setLastName(rs.getString("author_admin_lastname"))
                        .setBirthday(rs.getObject("author_admin_birthday", LocalDate.class))
                        .setRegistration(rs.getObject("author_admin_registration", LocalDateTime.class))
                        .build();
                AuditUser audit = new AuditUser(id, dtCreate, text, user, author);
                data.add(audit);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
        }
        return null;
    }

    private static DBAuditUserStorage getInstance() {
        DBAuditUserStorage result = instance;
        if (result != null) {
            return result;
        }
        synchronized (DBAuditUserStorage.class) {
            if (instance == null) {
                instance = new DBAuditUserStorage();
            }
        }
        return instance;
    }
}
