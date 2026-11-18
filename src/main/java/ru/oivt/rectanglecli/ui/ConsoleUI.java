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
        readParameters();

        boolean running = true;
        while (running) {
            printMenu();

            int choice = InputUtils.readInt(sc, "Ваш выбор: ");
            System.out.println(); // Пустая строка для красоты

            switch (choice) {
                case 1:
                    showPerimeter();
                    InputUtils.pause(sc);
                    break;
                case 2:
                    showArea();
                    InputUtils.pause(sc);
                    break;
                case 7:
                    System.out.println("Выход. До свидания!");
                    running = false;
                    break;
                default:
                    System.out.println("Ошибка: неизвестный пункт меню (" + choice + ").");
                    System.out.println("Повторите ввод.\n");
                    break;
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

//Меню

    private void printMenu() {
        System.out.println("----------------------------------------");
        System.out.printf("Параметры прямоугольника: a = %.2f, b = %.2f%n",
                rectangle.getA(), rectangle.getB());
        System.out.println("----------------------------------------");
        System.out.println("Выберите действие:");
        System.out.println("1) Периметр P = 2(a + b)");
        System.out.println("2) Площадь  S = a * b");
        // Остальные пункты добавим на следующих шагах
        System.out.println("7) Выход");
        System.out.println();
    }
    //Периметр
    private void showPerimeter() {
        System.out.println("Периметр:");
        System.out.printf("P = 2(a + b) = 2(%.2f + %.2f) = %.2f%n",
                rectangle.getA(), rectangle.getB(), rectangle.perimeter());
    }
//Площадь
    private void showArea() {
        System.out.println("Площадь:");
        System.out.printf("S = a * b = %.2f * %.2f = %.2f%n",
                rectangle.getA(), rectangle.getB(), rectangle.area());
    }
}