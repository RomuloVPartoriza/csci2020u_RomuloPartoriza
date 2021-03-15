package csci2020u.lab07;

import java.util.Map;
import java.util.TreeMap;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

public class Controller {

    @FXML private Canvas canvas;

    final private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };

    @FXML
    public void initialize(){
        GraphicsContext gc = canvas.getGraphicsContext2D();

        FileLoader weatherCSV = new FileLoader("weatherwarnings-2015.csv");
        weatherCSV.readCSV();
        Map<String, Integer> weatherWarnings = weatherCSV.getWordCounts();
        Map<String, Double> weatherWarningsPercent = new TreeMap<>();

        for(Map.Entry<String,Integer> entry : weatherWarnings.entrySet()) {
            String word = entry.getKey();
            Double value = Double.valueOf(entry.getValue());
            weatherWarningsPercent.put(word, value/weatherCSV.getCounter());
        }

        drawPieChart(gc,weatherWarningsPercent);
    }

    private void drawPieChart(GraphicsContext gc,Map<String, Double> weatherWarningsPercent) {
        double x = 200.0;
        double y = 100.0;
        double arcWxH = 400.0;
        double wRect = 80.0;
        double hRect = 40.0;
        int i = 0;
        double startAngle = 0;
        double sweepAngle = 0;

        for(Map.Entry<String,Double> entry : weatherWarningsPercent.entrySet()) {
            String warning = entry.getKey();
            Double count = entry.getValue();
            sweepAngle = count * 360;

            gc.setFill(pieColours[i]);
            gc.fillArc(x + 280,y,arcWxH,arcWxH,startAngle,sweepAngle, ArcType.ROUND);
            gc.strokeArc(x + 280,y,arcWxH,arcWxH,startAngle,sweepAngle,ArcType.ROUND);

            gc.fillRect(x-100,y + 60 * i + 40, wRect,hRect);
            gc.strokeRect(x-100,y + 60 * i + 40, wRect,hRect);
            gc.setFill(Color.BLACK);

            gc.fillText(warning,x-10,y + 60*i+65);

            startAngle += sweepAngle;
            i++;
        }
    }
}
