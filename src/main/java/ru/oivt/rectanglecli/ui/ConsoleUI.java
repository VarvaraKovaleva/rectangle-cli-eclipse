package ru.oivt.rectanglecli.ui;

import ru.oivt.rectanglecli.model.Rectangle;
import ru.oivt.rectanglecli.model.Circle;
import ru.oivt.rectanglecli.model.Triangle;
import ru.oivt.rectanglecli.model.Shape;
import ru.oivt.rectanglecli.util.InputUtils;
import ru.oivt.rectanglecli.util.HistoryManager;


import java.util.Scanner;

/**
 * Главный класс пользовательского интерфейса (UI).
 * Отвечает за ввод/вывод, меню и цикл приложения.
 * 
 * Работает с фигурами через интерфейс Shape (полиморфизм):
 * конкретный тип (Rectangle, Circle, Triangle) выбирается в chooseShape().
 */

public class ConsoleUI {
    private final Scanner sc;
    private Shape currentShape; // Текущая фигура (может быть любого типа)
    private final HistoryManager history = new HistoryManager(); //История

    public ConsoleUI(Scanner sc) {
        this.sc = sc;
    }
    

//Главный цикл приложения: печать меню, чтение выбора пользователя, выполнение действия и возврат в меню    

    public void run() {
        printHeader();//Запрос параметров
        chooseShape(); // Запрашиваем фигуру и её параметры при старте

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
                	chooseShape(); // Ввод новых параметров или смена фигуры
                    break;
                case 8://Историясесии
                    history.print();
                    InputUtils.pause(sc);
                    break;
                case 9://Помощь  
                    showHelp();
                    InputUtils.pause(sc);
                    break;
                case 10://О программе
                    showAbout();
                    InputUtils.pause(sc);
                    break;
                case 11://Очистка экрана
                    clearScreen();
                    // Паузу не делаем: очистка экрана сама по себе даёт «чистый» вид
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
    
    /**
     * Запрашивает у пользователя тип фигуры и её параметры.
     * Создаёт объект соответствующего класса и сохраняет в currentShape.
     * 
     * Квадрат реализован как Rectangle(a, a) — частный случай прямоугольника.
     */
    private void chooseShape() {
        System.out.println("Выберите фигуру:");
        System.out.println("1) Прямоугольник");
        System.out.println("2) Квадрат");
        System.out.println("3) Круг");
        System.out.println("4) Треугольник (равносторонний)");

        int type = InputUtils.readInt(sc, "Ваш выбор: ");
        System.out.println();

        switch (type) {
            case 2: // Квадрат — прямоугольник с равными сторонами
                double side = InputUtils.readPositiveDouble(sc, "Введите сторону квадрата a: ");
                currentShape = new Rectangle(side, side);
                break;
            case 3: // Круг
                double r = InputUtils.readPositiveDouble(sc, "Введите радиус круга r: ");
                currentShape = new Circle(r);
                break;
            case 4: // Равносторонний треугольник
                double tSide = InputUtils.readPositiveDouble(sc, "Введите сторону треугольника a: ");
                currentShape = new Triangle(tSide);
                break;
            case 1: // Прямоугольник
            default: // При любом другом вводе — безопасный дефолт
                double a = InputUtils.readPositiveDouble(sc, "Введите длину прямоугольника a: ");
                double b = InputUtils.readPositiveDouble(sc, "Введите ширину прямоугольника b: ");
                currentShape = new Rectangle(a, b);
                break;
        }
        System.out.println("Фигура создана!\n");
    }


    private void printHeader() {
        System.out.println("========================================");
        System.out.println(" Rectangle CLI Calculator");
        System.out.println("========================================\n");
    }



    /**
     * Выводит текущие параметры фигуры и список доступных действий.
     * Пункты 3, 4, 5 показываются только для прямоугольника (и квадрата),
     * так как для круга и треугольника эти характеристики не определены.
     */
    private void printMenu() {
        System.out.println("----------------------------------------");
        // Полиморфный вывод параметров: определяем тип через instanceof
        if (currentShape instanceof Rectangle r) {
            System.out.printf("Фигура: %s, a = %.2f, b = %.2f%n",
                    r.getName(), r.getA(), r.getB());
        } else if (currentShape instanceof Circle c) {
            System.out.printf("Фигура: %s, r = %.2f%n", c.getName(), c.getRadius());
        } else if (currentShape instanceof Triangle t) {
            System.out.printf("Фигура: %s, a = %.2f%n", t.getName(), t.getSide());
        }

        System.out.println("----------------------------------------");
        System.out.println("Выберите действие:");
        System.out.println("1) Периметр");
        System.out.println("2) Площадь");

        // Пункты 3-5 доступны только для прямоугольника (включая квадрат)
        if (currentShape instanceof Rectangle) {
            System.out.println("3) Диагональ d = sqrt(a^2 + b^2)");
            System.out.println("4) Длина описанной окружности C = π * d");
            System.out.println("5) Радиус вписанной окружности (только квадрат)");
        }

        System.out.println("6) Ввести новые параметры");
        System.out.println("8) Показать историю");
        System.out.println("9) Справка (help)");
        System.out.println("10) О программе (about)");
        System.out.println("11) Очистить экран (clear)");
        System.out.println("7) Выход");
        System.out.println();
    }

    /**
     * Периметр фигуры. Работает для любой фигуры (полиморфизм).
     */
    private void showPerimeter() {
        double p = currentShape.perimeter();
        System.out.printf("Периметр (%s): %.2f%n", currentShape.getName(), p);
        history.add(String.format("Периметр (%s): %.2f", currentShape.getName(), p));
    }

    /**
     * Площадь фигуры. Работает для любой фигуры (полиморфизм).
     */
    private void showArea() {
        double s = currentShape.area();
        System.out.printf("Площадь (%s): %.2f%n", currentShape.getName(), s);
        history.add(String.format("Площадь (%s): %.2f", currentShape.getName(), s));
    }

    /**
     * Диагональ — только для прямоугольника. Проверка instanceof — «ремень
     * безопасности», если метод вызовут в обход меню.
     */
    private void showDiagonal() {
        if (!(currentShape instanceof Rectangle r)) {
            System.out.println("Диагональ доступна только для прямоугольника.");
            return;
        }
        double d = r.diagonal();
        System.out.printf("Диагональ: d = sqrt(%.2f^2 + %.2f^2) = %.2f%n",
                r.getA(), r.getB(), d);
        history.add(String.format("Диагональ (a=%.2f, b=%.2f): %.2f",
                r.getA(), r.getB(), d));
    }

    /**
     * Длина описанной окружности — только для прямоугольника.
     */
    private void showCircumscribedCircle() {
        if (!(currentShape instanceof Rectangle r)) {
            System.out.println("Описанная окружность доступна только для прямоугольника.");
            return;
        }
        double c = Math.PI * r.diagonal();
        System.out.printf("Длина описанной: C = π * d = π * %.2f = %.2f%n",
                r.diagonal(), c);
        history.add(String.format("Описанная окружность (a=%.2f, b=%.2f): %.2f",
                r.getA(), r.getB(), c));
    }

    /**
     * Радиус вписанной окружности — только для квадрата.
     */
    private void showInscribedCircle() {
        if (!(currentShape instanceof Rectangle r)) {
            System.out.println("Вписанная окружность доступна только для прямоугольника.");
            return;
        }
        if (!r.isSquare()) {
            System.out.println("Вписанная окружность существует только для квадрата.");
            return;
        }
        double rad = r.inscribedCircleRadius();
        System.out.printf("Радиус вписанной: r = a / 2 = %.2f / 2 = %.2f%n",
                r.getA(), rad);
        history.add(String.format("Вписанная (a=%.2f, b=%.2f): %.2f",
                r.getA(), r.getB(), rad));
    }

    /**
     * Справка по командам (аналог --help).
     */
    private void showHelp() {
        System.out.println("=== СПРАВКА ===");
        System.out.println("Программа вычисляет характеристики фигур.");
        System.out.println("Доступные фигуры: прямоугольник, квадрат, круг, треугольник.");
        System.out.println();
        System.out.println("Основные операции:");
        System.out.println("  1 — периметр");
        System.out.println("  2 — площадь");
        System.out.println("  3 — диагональ (только прямоугольник/квадрат)");
        System.out.println("  4 — длина описанной окружности (только прямоугольник/квадрат)");
        System.out.println("  5 — радиус вписанной окружности (только квадрат)");
        System.out.println("  6 — ввести новые параметры / сменить фигуру");
        System.out.println("  7 — выход");
        System.out.println();
        System.out.println("Дополнительные команды:");
        System.out.println("  8  — история вычислений");
        System.out.println("  9  — эта справка");
        System.out.println("  10 — о программе");
        System.out.println("  11 — очистить экран");
    }

    /**
     * Информация о программе (аналог --version / --about).
     */
    private void showAbout() {
        System.out.println("=== О ПРОГРАММЕ ===");
        System.out.println("Название: Shape CLI Calculator");
        System.out.println("Версия:   3.0 (полиморфизм + доп. задания)");
        System.out.println("Автор:    [Твоё Имя]");
        System.out.println("Курс:     Java-разработка, учебный проект");
        System.out.println();
        System.out.println("Описание:");
        System.out.println("  Консольный калькулятор характеристик фигур.");
        System.out.println("  Поддерживает прямоугольник, квадрат, круг, треугольник.");
        System.out.println("  Реализован полиморфизм через интерфейс Shape.");
        System.out.println();
        System.out.println("Технологии: Java 17+, JUnit 5, Eclipse IDE.");
    }

    /**
     * Очистка экрана через ANSI escape-последовательности.
     * Работает в Eclipse Console, Linux, macOS, Windows Terminal.
     */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}