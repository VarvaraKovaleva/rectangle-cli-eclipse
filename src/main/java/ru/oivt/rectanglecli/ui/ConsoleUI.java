package ru.oivt.rectanglecli.ui;

import ru.oivt.rectanglecli.model.Rectangle;
import ru.oivt.rectanglecli.util.InputUtils;

import java.util.Scanner;

//Управляющий класс UI. Отвечает за ввод/вывод, меню и цикл приложения.

public class ConsoleUI {
    private final Scanner sc;
    private Rectangle rectangle; // Храним текущий прямоугольник

    public ConsoleUI(Scanner sc) {
        this.sc = sc;
    }

// Главный цикл приложения.

    public void run() {
        printHeader();
        readParameters(); // Запрашиваем a и b, создаем Rectangle
        
        boolean running = true;
        while (running) {
            printMenu();
            
            // Пока меню - заглушка, просто читаем выбор
            int choice = InputUtils.readInt(sc, "Ваш выбор: ");
            
            if (choice == 0) {
                System.out.println("Выход...");
                running = false;
            } else {
                System.out.println(">>> В разработке: выбран пункт " + choice + " <<<");
                InputUtils.pause(sc);
            }
        }
    }

//Запрашивает у пользователя параметры и создает объект Rectangle.
    private void readParameters() {
        System.out.println("Введите параметры прямоугольника:");
        double a = InputUtils.readPositiveDouble(sc, "  a (длина): ");
        double b = InputUtils.readPositiveDouble(sc, "  b (ширина): ");
        this.rectangle = new Rectangle(a, b);
        System.out.println("Прямоугольник создан!\n");
    }

    private void printHeader() {
        System.out.println("========================================");
        System.out.println(" Rectangle CLI Calculator");
        System.out.println("========================================\n");
    }

//Пока выводит только параметры и заглушку меню.

    private void printMenu() {
        System.out.println("----------------------------------------");
        System.out.printf("Параметры: a = %.2f, b = %.2f%n", rectangle.getA(), rectangle.getB());
        System.out.println("----------------------------------------");
        System.out.println("1) Периметр (заглушка)");
        System.out.println("2) Площадь (заглушка)");
        System.out.println("0) Выход");
        System.out.println();
    }
}