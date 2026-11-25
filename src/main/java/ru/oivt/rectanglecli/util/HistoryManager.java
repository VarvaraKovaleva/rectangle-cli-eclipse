package ru.oivt.rectanglecli.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Хранит историю вычислений за текущую сессию.
 * Отдельный класс — чтобы соблюсти SRP:
 */
public class HistoryManager {

    // ArrayList, а не массив: не нужно заранее знать размер
    private final List<String> history = new ArrayList<>();

    //Добавить запись в историю
    public void add(String record) {
        history.add(record);
    }

    //Вывести всю историю на экран
    public void print() {
        if (history.isEmpty()) {
            System.out.println("История пуста.");
            return;
        }
        System.out.println("--- История вычислений ---");
        for (int i = 0; i < history.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, history.get(i));
        }
        System.out.println("--------------------------");
    }
}
