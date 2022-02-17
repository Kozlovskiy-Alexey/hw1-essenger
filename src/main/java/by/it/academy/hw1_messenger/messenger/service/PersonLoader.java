package by.it.academy.hw1_messenger.messenger.service;

import by.it.academy.hw1_messenger.messenger.dto.Person;
import by.it.academy.hw1_messenger.messenger.service.api.IMessengerLoader;
import by.it.academy.hw1_messenger.messenger.service.api.IMessengerService;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

public class PersonLoader implements IMessengerLoader {

    private final IMessengerService messengerService;

    public PersonLoader(IMessengerService service) {
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
                        service.addPerson((Person) line);
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
        List<Person> personList = messengerService.getPersonList();

        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file))) {
            for (Person p : personList) {
                writer.writeObject(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



















