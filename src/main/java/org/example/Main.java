package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

public class Main {
    public static final Product[] prod = {
            new Product("Гречка", 80),
            new Product("Молоко", 55),
            new Product("Сметана", 90),
            new Product("Творог", 60),
            new Product("Сыр", 120),
            new Product("Хлеб", 20)
    };

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        Scanner scanner = new Scanner(System.in);
        Basket basket = new Basket(prod);
        File f = new File("basket.txt");
        int productIndex;
        int productsAmount;

        if (f.exists()) {
            System.out.println("Загрузить корзину? Нажмите <ENTER> ");
            if (scanner.nextLine().equals("")) {
                basket = Basket.loadFromTxtFile(f);
            }
        }


        while (true) {
            basket.printBasket();
            String input = scanner.nextLine();
            String[] strNumbers = input.split(" ");
            if (strNumbers.length == 2) {
                try {
                    productIndex = Integer.parseInt(strNumbers[0]);
                    productsAmount = Integer.parseInt(strNumbers[1]);

                    if (productIndex <= 0 || productIndex > prod.length) {
                        System.out.println("Некорректно введен номер продукта");
                        continue;
                    }
                    if (productsAmount <= 0) {
                        System.out.println("Некорректно введено количество продукта");
                        continue;
                    }

                    basket.addToCart(productIndex - 1, productsAmount);
                    basket.saveTxt(f);
                } catch (NumberFormatException n) {
                    System.out.println("Введены некорректные символы: " + n);
//                    continue;
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else if ("end".equals(input)) {
                break;
            }
            System.out.println("Укажите 2 значения: 1 - продукт, 2 - количество.");
        }
        scanner.close();
        basket.printCart();
    }
}
