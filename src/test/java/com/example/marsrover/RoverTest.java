package com.example.marsrover;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoverTest {
    Rover rover;
    String navigationCommands;
    Plateau plateau;

    @BeforeEach
    public void init() {
        rover = new Rover();
        plateau = new Plateau("5 5");
    }

    @Test
    void executeTest() {
        plateau = new Plateau("5 5");
        Position position = new Position("1 2 N");
        rover.setPlateau(plateau);
        rover.setPosition(position);
        rover.execute("LMLMLMLMM");
        Assertions.assertEquals(new Position("1 3 N"), rover.getPosition());
    }

    @Test
    void executeTest2() {
        plateau = new Plateau("5 5");
        Position position = new Position("3 3 E");
        rover.setPlateau(plateau);
        rover.setPosition(position);
        rover.execute("MMRMMRMRRM");
        Assertions.assertEquals(new Position("5 1 E"), rover.getPosition());

    }

    @Test
    void moveForwardTest() {
        Position position = new Position("1 2 N");
        plateau = new Plateau("5 6");
        rover.setPlateau(plateau);
        rover.setPosition(position);
        navigationCommands = "L";
        rover.execute(navigationCommands);
        Position expected = new Position("1 2 W");
        Assertions.assertEquals(expected, rover.getPosition());
        rover.execute("M");
        expected = new Position("0 2 W");
        Assertions.assertEquals(expected, rover.getPosition());

    }

    @Test
    void getDirectionAfterTurn() {
        Assertions.assertEquals('W', rover.getDirectionAfterTurn('N', -1));
        Assertions.assertEquals('S', rover.getDirectionAfterTurn('W', -1));
        Assertions.assertEquals('E', rover.getDirectionAfterTurn('S', -1));
        Assertions.assertEquals('N', rover.getDirectionAfterTurn('E', -1));
        Assertions.assertEquals('E', rover.getDirectionAfterTurn('N', 1));
        Assertions.assertEquals('S', rover.getDirectionAfterTurn('E', 1));
        Assertions.assertEquals('W', rover.getDirectionAfterTurn('S', 1));
        Assertions.assertEquals('N', rover.getDirectionAfterTurn('W', 1));
    }


    @Test
    void setPlateau() {
        plateau = new Plateau("5 6");
        rover.setPlateau(plateau);
        Assertions.assertEquals(5, rover.getPlateau().getX());
        Assertions.assertEquals(6, rover.getPlateau().getY());
    }


    @Test
    void setPosition() {
        Position position = new Position("1 2 W");
        Rover rover = new Rover();
        rover.setPosition(position);
        Assertions.assertEquals(1, rover.getPosition().x);
        Assertions.assertEquals(2, rover.getPosition().y);
        Assertions.assertEquals('W', rover.getPosition().direction);
    }


    @Test
    void turnLeft() {
        Position position = new Position("1 2 N");
        rover.setPosition(position);
        rover.turnLeft();
        Position expected = new Position("1 2 W");
        Assertions.assertEquals(expected, rover.getPosition());
        rover.turnLeft();
        Assertions.assertEquals("1 2 S", rover.getPosition().toString());
        rover.turnLeft();
        Assertions.assertEquals("1 2 E", rover.getPosition().toString());
        rover.turnLeft();
        Assertions.assertEquals("1 2 N", rover.getPosition().toString());

    }

    @Test
    void turnRight() {
        Position position = new Position("1 2 N");
        rover.setPosition(position);
        rover.turnRight();
        Assertions.assertEquals("1 2 E", rover.getPosition().toString());
        rover.turnRight();
        Assertions.assertEquals("1 2 S", rover.getPosition().toString());
        rover.turnRight();
        Assertions.assertEquals("1 2 W", rover.getPosition().toString());
        rover.turnRight();
        Assertions.assertEquals("1 2 N", rover.getPosition().toString());
        rover.turnRight();
        Assertions.assertNotEquals("1 2 N", rover.getPosition().toString());

    }


}