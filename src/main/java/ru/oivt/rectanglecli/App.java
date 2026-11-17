package ru.oivt.rectanglecli;

import ru.oivt.rectanglecli.model.Rectangle;

public class App {
    public static void main(String[] args) {
        // Создаем прямоугольник 10 на 5
        Rectangle r = new Rectangle(10, 5);
        
        System.out.println("Периметр должен быть 30: " + r.perimeter());
        System.out.println("Площадь должна быть 50: " + r.area());
        System.out.println("Диагональ (примерно 11.18): " + r.diagonal());
        
        // Проверка квадрата
        Rectangle square = new Rectangle(4, 4);
        System.out.println("Квадрат 4х4, радиус вписанной: " + square.inscribedCircleRadius());
    }
}