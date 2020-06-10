package com.example.marsrover;

import lombok.Data;

import java.util.regex.Pattern;

@Data
public class Position {
    int x;
    int y;
    char direction;
    private static final String POSITION_REGEX = "^[0-9]\\d*\\s[0-9]\\d*\\s[N|E|S|W]$";

    public Position() {
    }

    public Position(int x, int y, char direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }


    public Position(String position) {
        if (isValidate(position)) {
            String[] temp = position.trim().split("\\s");
            this.x = Integer.valueOf(temp[0]);
            this.y = Integer.valueOf(temp[1]);
            this.direction = Character.valueOf(temp[2].charAt(0));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public boolean isValidate(String position) {
        if (Pattern.compile(POSITION_REGEX).matcher(position).matches()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("%d %d %c", x, y, direction);
    }
}
