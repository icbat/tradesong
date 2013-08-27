package com.icbat.game.tradesong.utils;

public class Point {
    int x = 0;
    int y = 0;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /** Does the conversion using Integer.parseInt() */
    public Point(String x, String y) {
        this.x = Integer.parseInt(x);
        this.y = Integer.parseInt(y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    /** Currently, stages use a 32px:1 ratio */
    public void translateToStage() {
        x *= 32;
        y *= 32;
    }

    public void translateToMapStage(int mapHeight) {
        invertByMapY(mapHeight);
        translateToStage();
    }


    /** MUST be called before translateToStage() if necessary */
    public void invertByMapY(int mapHeight) {
        y = mapHeight - y - 1;
    }
}
