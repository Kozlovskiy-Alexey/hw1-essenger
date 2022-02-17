package by.it.academy.hw1_messenger.messenger.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthday;

    public Person(String login, String password, String firstName, String lastName, LocalDate birthday) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

}
