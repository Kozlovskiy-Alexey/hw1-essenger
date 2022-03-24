package by.it.academy.hw1_messenger.messenger.view;

import by.it.academy.hw1_messenger.messenger.model.AuditUser;
import by.it.academy.hw1_messenger.messenger.model.Pageble;
import by.it.academy.hw1_messenger.messenger.model.User;
import by.it.academy.hw1_messenger.messenger.storage.sql.DBAuditUserStorage;
import by.it.academy.hw1_messenger.messenger.storage.api.IAuditUserStorage;
import by.it.academy.hw1_messenger.messenger.view.api.IAuditService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class AuditService implements IAuditService {
    private static volatile AuditService instance;
    private final IAuditUserStorage auditUserStorage;

    private AuditService() {
        auditUserStorage = DBAuditUserStorage.getInstance();
    }

    public static AuditService getInstance() {
        AuditService result = instance;
        if (result != null) {
            return result;
        }
        synchronized (AuditService.class) {
            if (instance == null) {
                instance = new AuditService();
            }
        }
        return instance;
    }
    @Override
    public void regAudit(User user) {
        String log = "Регистрация пользователя";
        AuditUser auditUser = new AuditUser(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), log, null, user);
        auditUserStorage.create(auditUser);
    }

    @Override
    public void loginAudit(User user) {
        String log = "Вход пользователя в систему";
        AuditUser auditUser = new AuditUser(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), log, user, user);
        auditUserStorage.create(auditUser);
    }

    @Override
    public List<AuditUser> get(Pageble pageble) {

        return auditUserStorage.read(pageble);
    }
}
