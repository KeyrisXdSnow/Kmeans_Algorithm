package sample.kmeans.data;

import sample.kmeans.randomizer.Randomizer;

import java.util.ArrayList;
import java.util.List;

public class Cluster {


    private double centerX, centerY;
    private List<Point> pointList;

    public Cluster() {
        pointList = new ArrayList<>();
    }

    public void initCenters() {
        Randomizer randomizer = Randomizer.getInstance();
        centerX = randomizer.randomX();
        centerY = randomizer.randomY();

        pointList.clear();
    }

    public void initCenters(double minX, double maxX, double minY,double maxY) {
        Randomizer randomizer = Randomizer.getInstance();
        centerX = randomizer.randomX(minX, maxX);
        centerY = randomizer.randomY(minY, maxY);

        pointList.clear();
    }

    public void addPoint(Point point){
        pointList.add(point);
    }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    public List<Point> getPointList() {
        return pointList;
    }

}
