package by.it.academy.hw1_messenger.messenger.model;

import java.io.Serializable;
import java.time.LocalDate;


public class User implements Serializable {

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthday;

    public User(String login, String password, String firstName, String lastName, LocalDate birthday) {
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

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
