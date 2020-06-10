package com.example.marsrover;

import lombok.Data;

/**
 * @author myoung
 */
@Data
class Rover {

    private static int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static String cardinal_compass = "WNES";

    private Plateau plateau;
    private Position position;

    Rover() {
        this.plateau = new Plateau();
        this.position = new Position();
    }

    /**
     * Execute the navigation command, based on the character:'L','R', or 'M'.
     *
     * @param navigationCommands Command Strings.
     * @throws IllegalArgumentException Command strings contain illegal character, other than 'L', 'R' and 'M'.
     */
    void execute(String navigationCommands) throws IllegalArgumentException {
        for (Character character : navigationCommands.toCharArray()) {
            if (position != null) {

                switch (character) {
                    case 'L':
                        turnLeft();
                        break;
                    case 'R':
                        turnRight();
                        break;
                    case 'M':
                        moveForward();
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            }
        }
    }

    /**
     * Calcuate the direction after the turn, based on the initial facing, left(-1)/right(+1),
     * getting the index of the array of directions
     *
     * @param c    direction character before turn
     * @param turn -1 for let, 1 for right
     * @return the direction after the turn
     */
    char getDirectionAfterTurn(char c, int turn) {
        int directionIndex = cardinal_compass.indexOf(c);
        int len = cardinal_compass.length();
        int temp = directionIndex + turn;
        int newIndex = (temp < 0 || temp >= len) ? (len + temp) % len : temp;
        return cardinal_compass.charAt(newIndex);
    }

    /**
     * Check the coordinates if they are out of the given area
     *
     * @param x Coordinate X
     * @param y Coordinate Y
     * @return true, if out of bound; false, if not.
     */
    private boolean isOutOfBound(int x, int y) {
        return (x > plateau.getX()) || (y > plateau.getY()) || (x < 0) || (y < 0);
    }

    /**
     * Algorithm for turn-left, turn = -1
     */
    void turnLeft() {
        int turn = -1;
        position.setDirection(getDirectionAfterTurn(position.getDirection(), turn));
        //System.out.println(position);
    }

    /**
     * Algorithm for turn-right, turn = 1
     */
    void turnRight() {
        int turn = 1;
        position.setDirection(getDirectionAfterTurn(position.getDirection(), turn));
        //System.out.println(position);
    }

    /**
     * Algorithm for moving forward, apply the direction matrix with cardinal compass index
     * for calculating the new coordinates.
     * Make sure the rover is not out of bound before setting the new position.
     */
    private void moveForward() {
        int directIndex = cardinal_compass.indexOf(position.direction);
        int[] offset = directions[directIndex];
        int newX = position.x + offset[0];
        int newY = position.y + offset[1];
        if (isOutOfBound(newX, newY)) {
            throw new IllegalArgumentException("Danger: Rover is out of bound. ");
        } else {
            position.setX(newX);
            position.setY(newY);
            //System.out.println(position);
        }
    }
}
