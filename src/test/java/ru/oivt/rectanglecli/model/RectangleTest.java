package ru.oivt.rectanglecli.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit-тесты для класса Rectangle.
 * Проверяем, что формулы вычисляются корректно.
 */
class RectangleTest {

    @Test
    @DisplayName("Периметр 10x5 должен быть 30")
    void testPerimeter() {
        Rectangle r = new Rectangle(10, 5);
        // assertEquals(ожидаемое, фактическое, погрешность)
        // Погрешность 0.001 нужна, потому что double неточен
        assertEquals(30.0, r.perimeter(), 0.001);
    }

    @Test
    @DisplayName("Площадь 10x5 должна быть 50")
    void testArea() {
        Rectangle r = new Rectangle(10, 5);
        assertEquals(50.0, r.area(), 0.001);
    }

    @Test
    @DisplayName("Диагональ 3x4 должна быть 5 (пифагорова тройка)")
    void testDiagonal() {
        Rectangle r = new Rectangle(3, 4);
        // 3-4-5 — идеальный тест для диагонали
        assertEquals(5.0, r.diagonal(), 0.001);
    }

    @Test
    @DisplayName("Радиус вписанной окружности квадрата 4x4 должен быть 2")
    void testInscribedCircleRadiusForSquare() {
        Rectangle square = new Rectangle(4, 4);
        assertEquals(2.0, square.inscribedCircleRadius(), 0.001);
    }

    @Test
    @DisplayName("Для не-квадрата вписанная окружность должна бросать исключение")
    void testInscribedCircleRadiusForRectangleThrows() {
        Rectangle rect = new Rectangle(4, 5);
        // assertThrows проверяет, что вызов метода выбросит ожидаемое исключение
        assertThrows(IllegalStateException.class, rect::inscribedCircleRadius);
    }

    @Test
    @DisplayName("Квадрат определяется корректно")
    void testIsSquare() {
        assertTrue(new Rectangle(4, 4).isSquare());
        assertFalse(new Rectangle(4, 5).isSquare());
        // Проверка на погрешность double: 4.0000000001 ≈ 4
        assertTrue(new Rectangle(4.0000000001, 4).isSquare());
    }
}
