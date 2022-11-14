package org.example;

import java.io.*;

public class Basket implements Serializable {
    protected Product[] products;
    protected int finValue = 0;

    public Basket(Product[] products) {
        this.products = products;
    }

    public void initProdArray(int prodLength) {
        Product[] products = new Product[prodLength];
    }

    public void addToCart(int productNum, int amount) {
        products[productNum].changeitems(amount);
        finValue += products[productNum].getPrice() * amount;
    }

    public void printBasket() {
        int currentValue;
        finValue = 0;
        for (int i = 0; i < products.length; i++) {
            currentValue = products[i].getInBasket() * products[i].getPrice();
            finValue += currentValue;
            System.out.println(i + 1 + " " +
                    products[i].getTitle() + " - " + products[i].getPrice() + " руб/шт, " + "В корзине: " +
                    products[i].getInBasket() + ", Итого: " + currentValue + " руб.");
        }
        System.out.println("ИТОГО Товаров в корзине на " + finValue);
        System.out.println("Выберите товар и количество или введите `end`");
    }

    public void printCart() {
        System.out.println("Ваша корзина:");
        for (Product item : products) {
            if (item.getInBasket() > 0) {
                System.out.println(item.getTitle() + ", " + item.getInBasket() + " шт. "
                        + item.getPrice() + "руб/шт. В сумме: " + item.getInBasket() * item.getPrice());
            }
        }
        System.out.printf("ИТОГО: " + finValue + "руб.");
    }


    public void saveBin(File file) throws IOException {
        var fos = new FileOutputStream(file);
        var oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.close();


    }

    public static Basket loadFromBinFile(File file) throws IOException, ClassNotFoundException {
        var fis = new FileInputStream(file);
        var ois = new ObjectInputStream(fis);
        return (Basket) ois.readObject();
    }
}
