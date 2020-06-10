package com.example.marsrover;

import lombok.Data;

import java.util.regex.Pattern;

/**
 * @author myoung
 */
@Data
public class Plateau {
    private int x;
    private int y;
    private static final String AREA_REGEX = "^[1-9]\\d*\\s[1-9]\\d*$";

    public Plateau() {
    }

    public Plateau(String coordinates) {
        // Validate the coordinate string
        if (isValidFormat(coordinates)) {
            String[] temp = coordinates.trim().split("\\s");
            this.x = Integer.valueOf(temp[0]);
            this.y = Integer.valueOf(temp[1]);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public boolean isValidFormat(String coordinates) {
        if (Pattern.compile(AREA_REGEX).matcher(coordinates).matches()) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public String toString(){
        return String.format("%d %d", x, y);
    }

}
