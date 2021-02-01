package sample.kmeans.data;

import sample.kmeans.randomizer.Randomizer;

public class Point {

    private double x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        Randomizer randomizer = Randomizer.getInstance();
        x = randomizer.randomX();
        y = randomizer.randomY();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
