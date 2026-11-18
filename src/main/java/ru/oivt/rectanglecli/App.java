package ru.oivt.rectanglecli;

import ru.oivt.rectanglecli.ui.ConsoleUI;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Создаём Scanner для чтения из консоли
        Scanner sc = new Scanner(System.in);
        
        // Создаём UI, передавая ему Scanner
        ConsoleUI ui = new ConsoleUI(sc);
        
        // Запускаем главный цикл
        ui.run();
        
        // Закрываем Scanner
        sc.close();
    }
}