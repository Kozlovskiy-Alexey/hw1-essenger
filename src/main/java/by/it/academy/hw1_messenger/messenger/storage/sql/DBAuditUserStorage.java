package by.it.academy.hw1_messenger.messenger.storage.sql;

import by.it.academy.hw1_messenger.messenger.model.AuditUser;
import by.it.academy.hw1_messenger.messenger.model.Pageble;
import by.it.academy.hw1_messenger.messenger.model.User;
import by.it.academy.hw1_messenger.messenger.storage.sql.api.SQLInitializer;
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
        this.dataSource = SQLInitializer.getInstance().getDataSource();
    }

    @Override
    public long create(AuditUser auditUser) {
        if (auditUser == null) {
            throw new IllegalArgumentException("Аудите не может быть null");
        }
        String sql = "INSERT INTO messenger.audit_user (textMessage, author, dt_create, \"user\")\n" +
                "    VALUES (?, ?, ?, ?);";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"})) {
            ps.setObject(1, auditUser.getText());
            ps.setObject(2, auditUser.getAuthor() != null ? auditUser.getAuthor().getLogin() : null);
            ps.setObject(3, auditUser.getDtCreate());
            ps.setObject(4, auditUser.getUser().getLogin());
            ps.executeUpdate();

            try (ResultSet gk = ps.getGeneratedKeys()){
                if (gk.next()) {
                    return gk.getLong(1);
                } else {
                    throw new SQLException("ошибка создания аудита, ключ не получен");
                }
             }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Ошибка выполения SQL при вставке объекта AuditUser");
        }
    }

    @Override
    public long create(AuditUser audit1, AuditUser audit2) {
        if(audit1 == null || audit2 == null){
            throw new IllegalArgumentException("Аудит не может быть null");
        }

        String sql = "INSERT INTO messenger.audit_user(textMessage, author, dt_create, \"user\")\n" +
                "VALUES (?, ?, ?, ?);\n" +
                "\n" +
                "INSERT INTO messenger.audit_user(textMessage, author, dt_create, \"user\")\n" +
                "VALUES (?, ?, ?, ?);";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id"});
        ) {
            conn.setAutoCommit(false);

            ps.setObject(1, audit1.getText());
            ps.setObject(2, audit1.getAuthor() != null ? audit1.getAuthor().getLogin() : null);
            ps.setObject(3, audit1.getDtCreate());
            ps.setObject(4, audit1.getUser().getLogin());

            ps.setObject(5, audit2.getText());
            ps.setObject(6, audit2.getAuthor() != null ? audit2.getAuthor().getLogin() : null);
            ps.setObject(7, audit2.getDtCreate());
            ps.setObject(8, audit2.getUser().getLogin());

            ps.execute();
            conn.commit();

            return 1L;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка выполнение SQL", e);
        }
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
                "    audit.textMessage,\n" +
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
                String text = rs.getString("textMessage");
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
        return data;
    }

    public static DBAuditUserStorage getInstance() {
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
