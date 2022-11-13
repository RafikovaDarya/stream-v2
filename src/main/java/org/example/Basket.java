package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Basket {
    private final Product[] products;
    private int finValue = 0;

    public Basket(Product[] products) {
        this.products = products.clone();
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
        System.out.println("ИТОГО: " + finValue + "руб.");
    }

    public void saveTxt(File textFile) throws FileNotFoundException {
        var pw = new PrintWriter(textFile);

        Stream.of(products).forEach(p ->
                pw.printf("%s@%d@%d\n", p.getTitle(), p.getPrice(), p.getInBasket()));
        pw.close();
    }

    public static Basket loadFromTxtFile(File textFile) throws FileNotFoundException, ParseException {
        Scanner sc = new Scanner(textFile);
        List<Product> products = new ArrayList<>();
        String name;
        int price;
        int inBasket;
        NumberFormat nf = NumberFormat.getInstance();
        while (sc.hasNext()) {
            String[] d = sc.nextLine().split("@");
            name = d[0];
//            price = Double.parseDouble(d[1].replace(',', '.'));
            price = nf.parse(d[1]).intValue();
            inBasket = Integer.parseInt(d[2]);
            products.add(new Product(name, price, inBasket));
        }
        return new Basket(products.toArray(Product[]::new));
    }
}
