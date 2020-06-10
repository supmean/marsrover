package com.example.marsrover;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MarsRoverApplication {

    public static void main(String[] args) {

        SpringApplication.run(MarsRoverApplication.class, args);
        RoverController roverController = RoverController.getInstance();
        List<Rover> rovers = new ArrayList<>();
        Rover r1 = new Rover();
        Rover r2 = new Rover();
        Rover r3 = new Rover();
        rovers.add(r1);
        rovers.add(r2);
        rovers.add(r3);
        roverController.addRovers(rovers);
        //String[] input = {"5 5", "1 2 N", "LMLMLMLMM", "3 3 E", "MMRMMRMRRM"};
        roverController.processInput(args);
    }

}
