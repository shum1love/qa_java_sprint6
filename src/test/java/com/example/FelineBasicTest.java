package com.example;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FelineBasicTest {
    private Feline feline;

    @Before
    public void setUp() {
        feline = new Feline();
    }

    @Test
    public void testGetFamily() {
        assertEquals("Кошачьи", feline.getFamily());
    }

    @Test
    public void testGetKittens() {
        assertEquals(1, feline.getKittens());
    }
}