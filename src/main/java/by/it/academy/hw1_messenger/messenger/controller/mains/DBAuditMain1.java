package by.it.academy.hw1_messenger.messenger.controller.mains;

import by.it.academy.hw1_messenger.messenger.model.AuditUser;
import by.it.academy.hw1_messenger.messenger.model.Pageble;
import by.it.academy.hw1_messenger.messenger.model.User;
import by.it.academy.hw1_messenger.messenger.storage.sql.DBAuditUserStorage;
import by.it.academy.hw1_messenger.messenger.storage.api.IAuditUserStorage;

import java.time.LocalDateTime;
import java.util.List;

public class DBAuditMain1 {
    public static void main(String[] args) {

        IAuditUserStorage auditUserStorage = DBAuditUserStorage.getInstance();

        AuditUser regUser = new AuditUser(LocalDateTime.now(),
                "Регистрация",
                User.Builder.createBuilder().setLogin(null).build(),
                User.Builder.createBuilder().setLogin("Alex").build());

        AuditUser renameUser = new AuditUser(LocalDateTime.now(),
                "Переименование",
                User.Builder.createBuilder().setLogin("Alex").build(),
                User.Builder.createBuilder().setLogin("Alex").build());

        auditUserStorage.create(regUser);
        auditUserStorage.create(renameUser);

        List<AuditUser> read = auditUserStorage.read(Pageble.of(1, 10));
        read.forEach(System.out::println);

    }
}
