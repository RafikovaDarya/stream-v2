package org.example;

import java.io.Serializable;

public class Product implements Serializable {
    private final String name;
    private final int price;
    private int inBasket = 0;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }
    public Product(String name, int price, int inBasket) {
        this.name = name;
        this.price = price;
        this.inBasket = inBasket;
    }

    public String getTitle() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getInBasket() {
        return inBasket;
    }

    public void changeitems(int numb) {
        this.inBasket += numb;
    }

}
