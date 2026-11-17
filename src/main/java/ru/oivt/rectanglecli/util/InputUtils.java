package ru.oivt.rectanglecli.util;

import java.util.Scanner;

//Утилитарный класс для безопасного чтения данных из консоли.
//Все методы статические, так как класс не хранит состояние.
public class InputUtils {

//Запрашивает у пользователя вещественное число строго больше 0.

    public static double readPositiveDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim(); // читаем строку целиком и убираем пробелы по краям

            if (line.isEmpty()) {
                System.out.println("Ошибка: пустой ввод. Повторите.");
                continue; // возвращаемся в начало цикла
            }

            try {
                // Пытаемся превратить строку в число
                double value = Double.parseDouble(line);
                
                // Проверяем, что число > 0
                if (value <= 0) {
                    System.out.println("Ошибка: значение должно быть > 0. Повторите.");
                    continue;
                }
                
                return value; // Если всё хорош
                
            } catch (NumberFormatException e) {
                // Сюда мы попадаем, если ввели "abc" или "3,5"
                System.out.println("Ошибка: нужно число (например 10 или 3.5). Повторите.");
            }
        }
    }

//Запрашивает у пользователя целое число (для выбора пункта меню).
    public static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();

            try {
                return Integer.parseInt(line); // Пытаемся превратить в целое число
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
        }
    }

//Пауза: ждет, пока пользователь нажмет Enter, чтобы консоль не "улетала" слишком быстро.
    public static void pause(Scanner sc) {
        System.out.print("Нажмите Enter, чтобы продолжить...");
        sc.nextLine(); // просто читаем пустую строку
    }
}