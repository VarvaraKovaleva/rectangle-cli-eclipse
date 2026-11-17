package ru.oivt.rectanglecli;

import ru.oivt.rectanglecli.util.InputUtils;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("--- Тест InputUtils ---");
        
        // Попробуй ввести: abc, потом -5, потом 3.5
        double a = InputUtils.readPositiveDouble(sc, "Введите a: ");
        System.out.println("Вы ввели: " + a);
        
        // Попробуй ввести: abc, потом 5
        int choice = InputUtils.readInt(sc, "Введите целое число: ");
        System.out.println("Вы ввели: " + choice);

        InputUtils.pause(sc);
        System.out.println("Программа завершена.");
    }
}