package by.it.academy.hw1_messenger.messenger.service;

import by.it.academy.hw1_messenger.messenger.dto.Message;
import by.it.academy.hw1_messenger.messenger.service.api.IMessengerLoader;
import by.it.academy.hw1_messenger.messenger.service.api.IMessengerService;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class MessagesLoader implements IMessengerLoader {

    private final IMessengerService messengerService;

    public MessagesLoader(IMessengerService service) {
        this.messengerService = service;
    }

    @Override
    public void load(String filePath) {

        File file = Path.of(filePath).toFile();

        if (file.exists()) {
            if (messengerService instanceof MessengerService) {
                MessengerService service = (MessengerService) messengerService;
                try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))) {
                    String line;
                    while ((line = (String) reader.readObject()) != null) {
                        String[] splitListString = line.split("&&");
                        String[] messagesList = splitListString[1].split("::");
                        for (String m : messagesList) {
                            if (!m.isEmpty()) {
                                String[] split = m.split("#");
                                Message message = new Message(LocalDateTime.parse(split[0]).truncatedTo(ChronoUnit.SECONDS), split[1], split[2]);
                                service.addMessage(splitListString[0], message);
                            }
                        }
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

        Map<String, List<Message>> messagesMap = messengerService.getMessagesMap();
        Set<Map.Entry<String, List<Message>>> entries = messagesMap.entrySet();

        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file, false))) {
            for (Map.Entry<String, List<Message>> e : entries) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(e.getKey()).append("&&");
                List<Message> messageList = e.getValue();
                for (Message m : messageList) {
                    stringBuilder.append(m.getDateTime()).append("#").append(m.getFromLogin()).append("#").append(m.getMessage()).append("::");
                }
                writer.writeObject(stringBuilder.toString());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}



















