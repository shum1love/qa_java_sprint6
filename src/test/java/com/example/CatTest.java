package com.example;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

public class CatTest {
    private Cat cat;
    private Feline felineMock;

    @Before
    public void setUp() {
        felineMock = mock(Feline.class); // Создаем мок для Feline
        cat = new Cat(felineMock); // Передаем мок в Cat
    }

    @Test
    public void testGetSound() {
        assertEquals("Мяу", cat.getSound());
    }

    @Test
    public void testGetFood() throws Exception {
        when(felineMock.eatMeat()).thenReturn(List.of("Животные"));
        List<String> food = cat.getFood();
        assertTrue(food.contains("Животные"));
    }
}