package com.example.marsrover;


import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author myoung
 */


public enum RoverController {
    INSTANCE;

    private static final char TURN_LEFT = 'L';
    private static final char TURN_RIGHT = 'R';
    private static final char MOVE_FORWARD = 'M';

    //Three types of input command, String Regex Patterns: Area, Position, Navigation
    private static final String AREA_REGEX = "^[1-9]\\d*\\s[1-9]\\d*$";
    private static final String POSITION_REGEX = "^[0-9]\\d*\\s[0-9]\\d*\\s[N|E|S|W]$";
    private static final String NAVIGATION_REGEX = "^[L|R|M]*$";

    private Plateau plateau;
    private List<Rover> rovers;

    public void addRovers(List<Rover> rovers) {
        this.rovers = rovers;
    }

    public static RoverController getInstance() {
        return INSTANCE;
    }

    /**
     * Iterate the rover list and command strings to assign each rover with execution
     *
     * @param inputStrings
     */
    public void processInput(String[] inputStrings) {

        Iterator<Rover> it = rovers.iterator();
        Rover rover;

        int i = 0;
        while (i < inputStrings.length) {

            String s = inputStrings[i];
            InstructionType instruction = parseInput(s);
            if ((this.plateau == null) && (instruction != InstructionType.AREA)) {
                throw new IllegalArgumentException();
            } else {
                switch (instruction) {
                    case AREA:
                        this.plateau = new Plateau(s);
                        break;
                    case POSITION:
                        /** Get a rover from the list and instruct it by:
                         * 1. Assign position
                         * 2. Send navigation command
                         **/
                        if (it.hasNext()) {
                            rover = it.next();
                            rover.setPlateau(this.plateau);
                            rover.setPosition(new Position(s));
                            i++;
                            s = inputStrings[i];
                            instruction = parseInput(s);
                            if (instruction == InstructionType.NAVIGATION) {
                                rover.execute(s);
                            }
                        } else {
                            throw new IllegalArgumentException("Rovers are out of number, please assign more.");
                        }
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            }
            i++;

        }
        rovers.stream().map(r -> r.getPosition()).forEach(System.out::println);

    }

    /**
     * Parse the input in string to categorize the instruction type, returning the enum
     * @param input
     * @return InstructionType, enum
     */
    public InstructionType parseInput(String input) {

        if (Pattern.compile(AREA_REGEX).matcher(input).matches()) {
            return InstructionType.AREA;
        } else if (Pattern.compile(POSITION_REGEX).matcher(input).matches()) {
            return InstructionType.POSITION;
        } else if (Pattern.compile(NAVIGATION_REGEX).matcher(input).matches()) {
            return InstructionType.NAVIGATION;
        } else {
            return InstructionType.NOT_SUPPORTED;
        }

    }

    public void sendPosition() {

    }
}
