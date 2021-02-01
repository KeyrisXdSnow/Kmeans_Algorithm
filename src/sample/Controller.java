package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.kmeans.Constants;
import sample.kmeans.KMeansAlgorithm;
import sample.kmeans.data.Cluster;
import sample.kmeans.data.Point;
import sample.kmeans.generator.ClusterGenerator;
import sample.kmeans.generator.IGenerator;
import sample.kmeans.generator.PointGenerator;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private IGenerator clusterGenerator, pointGenerator;
    private KMeansAlgorithm kMeansAlgorithm;
    private List<Point> pointList = new ArrayList<>();
    private List<Cluster> clusterList = new ArrayList<>();

    @FXML
    private BubbleChart<?, ?> beforeBubbleChart, afterBubbleChart;
    @FXML
    private Button bKmeans;
    @FXML
    private TextField tfNumberOfPoints,tfNumberOfClusters,tfNumberOfIterations;

    @FXML
    void initialize() {

        clusterGenerator = new ClusterGenerator();
        pointGenerator = new PointGenerator();
        kMeansAlgorithm = new KMeansAlgorithm();

        bKmeans.setOnAction(event -> {

            Constants.NUMBER_OF_POINTS = Integer.parseInt(tfNumberOfPoints.getText());
            Constants.MAX_ITERATIONS = Integer.parseInt(tfNumberOfIterations.getText());
            Constants.NUMBER_OF_CLUSTERS = Integer.parseInt(tfNumberOfClusters.getText());

            generatePoints();
            generateClusters();
            calculateKMeans();

        });
    }

    private void generatePoints() {
        pointList.clear();
        pointList = pointGenerator.generate(Constants.NUMBER_OF_POINTS);
    }

    private void generateClusters() {
        clusterList.clear();
        clusterList = clusterGenerator.generate(Constants.NUMBER_OF_CLUSTERS);
    }


    private void viewPointsAfterBubbleChart (BubbleChart bubbleChart) {
        bubbleChart.getData().clear();

        for (Cluster cluster : clusterList) {
            XYChart.Series series = new XYChart.Series();

            for (Point point : cluster.getPointList()) {
                series.getData().add(new XYChart.Data(point.getX(),point.getY(),Constants.POINT_SCALE));
            }
            bubbleChart.getData().add(series);
        }

        XYChart.Series series = new XYChart.Series();
        for (Cluster cluster : clusterList) {

            series.getData().add(new XYChart.Data(cluster.getCenterX(),cluster.getCenterY(),Constants.POINT_SCALE+5));

        }
        bubbleChart.getData().add(series);


    }

    private void calculateKMeans () {
        System.out.println("Fist iteration...");
        kMeansAlgorithm.fistStart(pointList,clusterList);
        viewPointsAfterBubbleChart(beforeBubbleChart);

        System.out.println("Finish work");
        kMeansAlgorithm.start(pointList,clusterList,Constants.MAX_ITERATIONS);
        viewPointsAfterBubbleChart(afterBubbleChart);
    }

}
