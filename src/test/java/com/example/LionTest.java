package com.example;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

public class LionTest {
    private Lion lion;
    private Feline felineMock;

    @Before
    public void setUp() throws Exception {
        felineMock = mock(Feline.class); // Создаем мок для Feline
        lion = new Lion("Самец", felineMock); // Передаем мок в Lion
    }

    @Test
    public void testDoesHaveMane() {
        assertTrue(lion.doesHaveMane());
    }

    @Test(expected = Exception.class)
    public void testInvalidSex() throws Exception {
        lion = new Lion("Неправильный пол", felineMock);
    }

    @Test
    public void testGetKittens() {
        when(felineMock.getKittens()).thenReturn(1); // Подготавливаем мок
        assertEquals(1, lion.getKittens());
    }

    @Test
    public void testGetFood() throws Exception {
        when(felineMock.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        List<String> food = lion.getFood();
        assertTrue(food.contains("Животные"));
    }

    // ... добавь аналогичные тесты для других видов еды
}