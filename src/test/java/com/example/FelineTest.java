package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith; // Импортируем аннотацию для запуска тестов с использованием Mockito
import org.mockito.Mockito; // Импортируем Mockito для создания моков
import org.mockito.junit.MockitoJUnitRunner; // Импортируем раннер для Mockito
import org.junit.runners.Parameterized; // Импортируем аннотацию для параметризованных тестов
import java.util.Arrays; // Импортируем Arrays для работы с массивами
import java.util.List; // Импортируем List для проверки возвращаемых значений


import java.util.List; // Импортируем List для проверки возвращаемых значений

import static org.junit.Assert.*;
import static org.mockito.Mockito.*; // методы Mockito

@RunWith(Parameterized.class) // Указываем, что тест будет параметризован
public class FelineTest {

    private Feline feline; // Экземпляр класса Feline

    // Параметры для теста
    @Parameterized.Parameter
    public String expectedFood; // Ожидаемая еда
    @Parameterized.Parameter(1) // Указываем индекс второго параметра
    public String unexpectedFood; // Неожидаемая еда

    // Параметры для групповой теста
    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "Животные", "Растения" },
                { "Птицы", "Листья" },
                { "Рыба", "Фрукты" }
        });
    }

    @Before
    public void setUp() {
        feline = new Feline(); // Создаем новый объект Feline перед каждым тестом
    }

    @Test
    public void testEatMeat() throws Exception {
        // Получаем список еды от метода eatMeat
        List<String> food = feline.eatMeat();

        // Проверяем, что метод возвращает ожидаемую еду
        assertTrue(food.contains(expectedFood)); // Закрывающая скобка добавлена
        assertFalse(food.contains(unexpectedFood)); // Проверяем отсутствие неожиданной еды в списке
    }

    @Test
    public void testGetFamily() {
        // Проверяем, что метод getFamily() возвращает "Кошачьи"
        String family = feline.getFamily(); // Вызываем метод getFamily
        assertEquals("Кошачьи", family); // Сравниваем с ожидаемым результатом
    }

    @Test
    public void testGetKittens() {
        // Проверяем, что метод getKittens() возвращает 1 по умолчанию
        int kittensCount = feline.getKittens(); // Вызываем метод getKittens
        assertEquals(1, kittensCount); // Сравниваем с ожидаемым результатом
    }

    @Test
    public void testGetKittensWithParameter() {
        // Проверяем, что метод getKittens(int) возвращает переданное значение
        int count = 5; // Указываем количество котят
        int kittensCount = feline.getKittens(count); // Вызываем метод getKittens с параметром
        assertEquals(count, kittensCount); // Проверяем, что возвращаемое значение равно переданному
    }

    @Test(expected = Exception.class) // Указываем, что ожидаем исключение
    public void testGetFoodException() throws Exception {
        // Создаем мок для родителя Animal
        Animal animalMock = Mockito.spy(feline); // Создаем шпион для класса Feline

        // Принуждаем мок, чтобы метод getFood вызывал исключение
        Mockito.doThrow(new Exception("Неизвестный вид животного")).when(animalMock).getFood("Неизвестный вид");

        // Проверяем, что вызов throw вызывает ожидаемое исключение
        animalMock.getFood("Неизвестный вид");
    }
}