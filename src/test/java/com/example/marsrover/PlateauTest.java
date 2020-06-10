package com.example.marsrover;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlateauTest {
    Plateau plateau;

    @BeforeEach
    void init() {
        plateau = new Plateau();
    }

    @Test
    void isValidFormat() {
        Assertions.assertTrue(plateau.isValidFormat("3 4"));
        Assertions.assertFalse(plateau.isValidFormat("-1 4"));
        Assertions.assertFalse(plateau.isValidFormat("34"));
        Assertions.assertFalse(plateau.isValidFormat("-1, 4"));
    }

    @Test
    void testToString(){
        plateau= new Plateau("5 6");
        String actual = plateau.toString();
        Assertions.assertEquals("5 6", actual);
    }
    @Test
    void exceptionTest(){
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class,()->{
            Plateau plateau1 = new Plateau("123");
        });
    }
}