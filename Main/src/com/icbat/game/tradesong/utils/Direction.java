package com.icbat.game.tradesong.utils;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    public static Direction getDirectionFromString(String directionString) {
        Direction facing = UP;

        directionString = directionString.toLowerCase();
        if (directionString.equals("left"))
            facing = Direction.LEFT;

        if (directionString.equals("right"))
            facing = Direction.RIGHT;

        if (directionString.equals("up"))
            facing = Direction.UP;

        if (directionString.equals("down"))
            facing = Direction.DOWN;

        return facing;
    }
}
