package by.it.academy.hw1_messenger.messenger.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class User implements Serializable {

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private LocalDateTime registration;

    public User() {
    }

    public User(String login, String password, String firstName, String lastName, LocalDate birthday, LocalDateTime registration) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.registration = registration;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getRegistration() {
        return registration;
    }

    public void setRegistration(LocalDateTime registration) {
        this.registration = registration;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", registration=" + registration +
                '}';
    }

    public static class Builder {
        private String login;
        private String password;
        private String firstName;
        private String lastName;
        private LocalDate birthday;
        private LocalDateTime registration;

        private Builder() {

        }

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setBirthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder setRegistration(LocalDateTime registration) {
            this.registration = registration;
            return this;
        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public User build() {
            return new User(login, password, firstName, lastName, birthday, registration);
        }
    }
}
