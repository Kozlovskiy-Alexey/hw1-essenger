package by.it.academy.hw1_messenger.messenger.controller.mains;

import by.it.academy.hw1_messenger.messenger.model.AuditUser;
import by.it.academy.hw1_messenger.messenger.model.User;
import by.it.academy.hw1_messenger.messenger.storage.sql.DBAuditUserStorage;
import by.it.academy.hw1_messenger.messenger.storage.api.IAuditUserStorage;

import java.time.LocalDateTime;

public class DBAuditMain2 {
    public static void main(String[] args) {

        IAuditUserStorage auditUserStorage = DBAuditUserStorage.getInstance();

        AuditUser regUser = new AuditUser(LocalDateTime.now(),
                "Регистрация",
                User.Builder.createBuilder().setLogin("Alex").build(),
                User.Builder.createBuilder().setLogin("Alex").build());

        AuditUser renameUser = new AuditUser(LocalDateTime.now(),
                "Переименование",
                User.Builder.createBuilder().setLogin("Alex").build(),
                User.Builder.createBuilder().setLogin("Alex").build());

        auditUserStorage.create(regUser, renameUser);



    }
}
