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
        printHeader();//Запрос параметров
        readParameters();//Название калькулятора

        boolean running = true;
        while (running) {
            printMenu();

            int choice = InputUtils.readInt(sc, "Ваш выбор: ");
            System.out.println();

            switch (choice) {
                case 1://Периметр
                    showPerimeter();
                    InputUtils.pause(sc);
                    break;
                case 2://Площадь
                    showArea();
                    InputUtils.pause(sc);
                    break;
                case 3://Диагональ
                    showDiagonal();
                    InputUtils.pause(sc);
                    break;
                case 4://Длина описанной окружности
                    showCircumscribedCircle();
                    InputUtils.pause(sc);
                    break;
                case 5://Радиус вписанной окружности
                    showInscribedCircle();
                    InputUtils.pause(sc);
                    break;
                case 6://Изменение параметров
                    readParameters(); // Запрашиваем a и b заново
                    break;
                case 7://Выход
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
        System.out.println("3) Диагональ d = sqrt(a^2 + b^2)");
        System.out.println("4) Длина описанной окружности C = π * d");
        System.out.println("5) Радиус вписанной окружности (только квадрат)");
        System.out.println("6) Ввести новые параметры");
        System.out.println("7) Выход");
        System.out.println();
    }
    
    
//Методы расчета
    //Периметр
    private void showPerimeter() {
        System.out.println("Периметр:");
        // Выводим название, формулу, подстановку и результат (ТЗ, раздел 3.3)
        System.out.printf("P = 2(a + b) = 2(%.2f + %.2f) = %.2f%n",
                rectangle.getA(), rectangle.getB(), rectangle.perimeter());
    }

    private void showArea() {
        System.out.println("Площадь:");
        System.out.printf("S = a * b = %.2f * %.2f = %.2f%n",
                rectangle.getA(), rectangle.getB(), rectangle.area());
    }

    private void showDiagonal() {
        System.out.println("Диагональ:");
        // Используем sqrt в тексте, чтобы было понятно, откуда формула
        System.out.printf("d = sqrt(a^2 + b^2) = sqrt(%.2f^2 + %.2f^2) = %.2f%n",
                rectangle.getA(), rectangle.getB(), rectangle.diagonal());
    }

    private void showCircumscribedCircle() {
        System.out.println("Длина описанной окружности:");
        // C = 2πR, где R = d/2, то есть C = π * d
        double c = Math.PI * rectangle.diagonal();
        System.out.printf("C = π * d = π * %.2f = %.2f%n",
                rectangle.diagonal(), c);
    }

    private void showInscribedCircle() {
        System.out.println("Радиус вписанной окружности:");
        
        // только для квадрата, иначе — понятное сообщение
        if (!rectangle.isSquare()) {
            System.out.println("Вписанная окружность существует только для квадрата.");
            return; // Вышли из метода, пауза выполнится в run()
        }
        
        // Для квадрата: r = a / 2
        System.out.printf("r = a / 2 = %.2f / 2 = %.2f%n",
                rectangle.getA(), rectangle.inscribedCircleRadius());
    }
}