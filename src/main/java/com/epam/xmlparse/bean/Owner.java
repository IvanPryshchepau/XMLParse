package com.epam.xmlparse.bean;

/**
 * Created by ivanpryshchepau on 7/19/16.
 */
public class Owner {

    private String surname;

    Owner() {
    }

    Owner(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Owner: " +
                "surname='" + surname + '\'';
    }
}
