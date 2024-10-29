package com.example;

import org.junit.Before; // Импортируем аннотацию для подготовки тестов
import org.junit.Test; // Импортируем аннотацию для определения тестов
import org.junit.runner.RunWith; // Импортируем аннотацию, чтобы запускать параметры
import org.mockito.Mockito; // Импортируем Mockito для создания моков
import org.mockito.junit.MockitoJUnitRunner; // Импортируем раннер для Mockito
import org.junit.runners.Parameterized; // Импортируем аннотацию для параметризации
import java.util.Arrays; // Импортируем для работы с массивами
import java.util.List; // Импортируем для работы с списками
import static org.junit.Assert.*; // Импортируем статические методы для утверждений
import static org.mockito.Mockito.*; // Импортируем методы Mockito для моков

@RunWith(Parameterized.class) // Указываем, что тесты будут параметризованными
public class LionTest {

    private Lion lion; // Экземпляр класса Lion

    // Параметры для теста
    @Parameterized.Parameter
    public String sex; // Пол льва (Определяет имеет ли он гриву)

    @Parameterized.Parameter(1) // Указываем индекс второго параметра
    public boolean expectedHasMane; // Ожидаемое значение 'имеет гриву'

    // Параметры для групповой теста
    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "Самец", true }, // Ожидаем, что у самца есть грива
                { "Самка", false }  // Ожидаем, что у самки нет гривы
        });
    }

    @Before
    public void setUp() throws Exception {
        lion = new Lion(sex); // Создаем новый объект Lion перед каждым тестом с указанным полом
    }

    @Test
    public void testDoesHaveMane() {
        // Проверяем, что метод doesHaveMane возвращает правильное значение на основе пола
        assertEquals(expectedHasMane, lion.doesHaveMane());
    }

    @Test(expected = Exception.class) // Ожидаем, что вызов приведет к исключению
    public void testInvalidSex() throws Exception {
        lion = new Lion("Неправильный пол"); // Создаем льва с неправильным полом
    }

    @Test
    public void testGetKittens() {
        // Проверяем, что метод getKittens возвращает 1, так как по умолчанию возвращается 1
        assertEquals(1, lion.getKittens());
    }

    @Test
    public void testGetFood() throws Exception {
        // Создаем мок для Feline
        Feline felineMock = Mockito.mock(Feline.class);
        when(felineMock.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба")); // Определяем поведение мока

        // Сюда вставим мок в Lion
        lion = Mockito.spy(new Lion("Самец") {
            {
                feline = felineMock; // Заменяем Feline на мок в классе Lion
            }
        });

        // Проверяем, что метод getFood возвращает ожидаемую еду
        List<String> food = lion.getFood();
        assertTrue(food.contains("Животные"));
        assertTrue(food.contains("Птицы"));
        assertTrue(food.contains("Рыба"));
    }
}
