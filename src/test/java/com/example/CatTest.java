package com.example;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;  // Импортируем List для проверки возвращаемых значений

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CatTest {

    private Cat cat; // Экземпляр класса Cat
    private Feline felineMock; // Мок для доступа к классу Feline

    @Before
    public void setUp() {
        felineMock = mock(Feline.class); // Создаем мок для Feline
        cat = new Cat(felineMock); // Передаем мок в Cat
    }

    @Test
    public void testGetSound() {
        String sound = cat.getSound(); // Получаем звук из cat
        assertEquals("Мяу", sound); // Проверяем, соответствует ли он "Мяу"
    }

    @Test
    public void testGetFood() throws Exception {
        // Настраиваем, чтобы getFood возвращал желаемый результат от мокированного Feline
        when(felineMock.eatMeat()).thenReturn(List.of("Животные", "Птицы", "Рыба"));

        // Убедимся, что метод getFood() у кота возвращает правильные значения
        List<String> food = cat.getFood();

        // Проверяем результат
        assertTrue(food.contains("Животные"));
        assertTrue(food.contains("Птицы"));
        assertTrue(food.contains("Рыба"));
    }
}
