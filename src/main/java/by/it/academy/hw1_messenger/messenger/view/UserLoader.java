package by.it.academy.hw1_messenger.messenger.view;

import by.it.academy.hw1_messenger.messenger.model.User;
import by.it.academy.hw1_messenger.messenger.view.api.IMessengerLoader;
import by.it.academy.hw1_messenger.messenger.view.api.IMessengerService;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

public class UserLoader implements IMessengerLoader {

    private final IMessengerService messengerService;

    public UserLoader(IMessengerService service) {
        this.messengerService = service;
    }

    @Override
    public void load(String filePath) {
        File file = Path.of(filePath).toFile();
        if (file.exists()) {
            if (messengerService instanceof MessengerService) {
                MessengerService service = (MessengerService) messengerService;
                try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))) {
                    Object line = null;
                    while ((line = reader.readObject()) != null) {
                        service.addUser((User) line);
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void unLoad(String filePath) {
        File file = Path.of(filePath).toFile();
        List<User> userList = messengerService.getUserList();

        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file))) {
            for (User p : userList) {
                writer.writeObject(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



















