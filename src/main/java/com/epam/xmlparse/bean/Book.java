package com.epam.xmlparse.bean;

/**
 * Created by ivanpryshchepau on 7/19/16.
 */
public class Book {

    private String title;
    private int price;
    private Owner owner;

    public Book() {
        this.owner = new Owner();
    }

    public Book(String title, int price, String surname) {
        this.title = title;
        this.price = price;
        this.owner = new Owner(surname);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(String surname) {
        this.owner.setSurname(surname);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", " + owner.toString() +
                '}';
    }
}
