package sample.kmeans.generator;

import sample.kmeans.data.Point;

import java.util.ArrayList;
import java.util.List;

public class PointGenerator implements IGenerator{

    @Override
    public List<Point> generate(int amount) {

        List<Point> points = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            points.add(new Point());
        }
        return points;
    }
}
