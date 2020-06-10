package com.example.marsrover;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class RoverControllerTest {
    RoverController roverController;

    @BeforeEach
    void init() {
        roverController = RoverController.getInstance();
    }

    @Test
    void parseInput() {
        RoverController controller = RoverController.getInstance();
        String area1 = "5 6";
        String area2 = "0 5";
        String area3 = "-1 5";
        String area4 = "123 321";

        String position = "1 2 N";
        String position1 = "1 2 W";
        String position2 = "1 2 S";
        String position3 = "11 22 E";
        String position4 = "1 2 E";
        String position5 = "-1 0 S";
        String position6 = "-11  S";

        String navigation = "LMLMLMLMM";
        String navigation1 = "RMRMRMMM";
        String navigation2 = "LRLRLRL";
        String navigation3 = "MMMMM";
        String navigation4 = "SS";
        String navigation5 = "LM$@#$";
        String navigation6 = "L1234";
        String navigation7 = "MTTEFag";
        String navigation8 = "lmlmmm";
        String navigation9 = "r123";

        Assertions.assertEquals(InstructionType.AREA, controller.parseInput(area1));
        Assertions.assertEquals(InstructionType.NOT_SUPPORTED, controller.parseInput(area2));
        Assertions.assertEquals(InstructionType.NOT_SUPPORTED, controller.parseInput(area3));
        Assertions.assertEquals(InstructionType.AREA, controller.parseInput(area4));

        Assertions.assertEquals(InstructionType.POSITION, controller.parseInput(position));
        Assertions.assertEquals(InstructionType.POSITION, controller.parseInput(position1));
        Assertions.assertEquals(InstructionType.POSITION, controller.parseInput(position2));
        Assertions.assertEquals(InstructionType.POSITION, controller.parseInput(position3));
        Assertions.assertEquals(InstructionType.POSITION, controller.parseInput(position4));
        Assertions.assertEquals(InstructionType.NOT_SUPPORTED, controller.parseInput(position5));
        Assertions.assertEquals(InstructionType.NOT_SUPPORTED, controller.parseInput(position6));

        Assertions.assertEquals(InstructionType.NAVIGATION, controller.parseInput(navigation));
        Assertions.assertEquals(InstructionType.NAVIGATION, controller.parseInput(navigation1));
        Assertions.assertEquals(InstructionType.NAVIGATION, controller.parseInput(navigation2));
        Assertions.assertEquals(InstructionType.NAVIGATION, controller.parseInput(navigation3));
        Assertions.assertEquals(InstructionType.NOT_SUPPORTED, controller.parseInput(navigation4));
        Assertions.assertEquals(InstructionType.NOT_SUPPORTED, controller.parseInput(navigation5));
        Assertions.assertEquals(InstructionType.NOT_SUPPORTED, controller.parseInput(navigation6));
        Assertions.assertEquals(InstructionType.NOT_SUPPORTED, controller.parseInput(navigation7));
        Assertions.assertEquals(InstructionType.NOT_SUPPORTED, controller.parseInput(navigation8));
        Assertions.assertEquals(InstructionType.NOT_SUPPORTED, controller.parseInput(navigation9));
    }

    @Test
    void testProcessInput() {
        List<Rover> rovers = new ArrayList<>();
        Rover r1 = new Rover();
        rovers.add(r1);
        Rover r2 = new Rover();
        rovers.add(r2);
        roverController.addRovers(rovers);
        String[] input = {"5 5", "1 2 N", "LMLMLMLMM", "3 3 E", "MMRMMRMRRM"};
        roverController.processInput(input);
    }


}