package by.it.academy.hw1_messenger.messenger.view.api;

import by.it.academy.hw1_messenger.messenger.model.AuditUser;
import by.it.academy.hw1_messenger.messenger.model.Pageble;
import by.it.academy.hw1_messenger.messenger.model.User;

import java.util.List;

public interface IAuditService {

    void regAudit(User user);

    void loginAudit(User user);

    List<AuditUser> get(Pageble pageble);
}
