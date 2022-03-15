package by.it.academy.hw1_messenger.messenger.storage.api;

import by.it.academy.hw1_messenger.messenger.model.AuditUser;
import by.it.academy.hw1_messenger.messenger.model.Pageble;

import java.util.List;

public interface IAuditUserStorage {

    long create(AuditUser auditUser);

    List<AuditUser> read(Pageble pageble);
}
