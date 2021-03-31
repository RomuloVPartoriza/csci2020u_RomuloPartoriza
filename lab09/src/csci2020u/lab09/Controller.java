package csci2020u.lab09;

import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class Controller {

    @FXML private Canvas canvas;
    private float[] AAPL;
    private float[] GOOG;

    private int x = 900;
    private int y = 700;

    private float maxValue = 0.0000f;
    private double width;
    private double hieght;

    @FXML
    public void initialize() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        GetStock apple = new GetStock("AAPL");
        apple.downloadStockPrices();
        AAPL = apple.getClosingVal();

        GetStock google = new GetStock("GOOG");
        google.downloadStockPrices();
        GOOG = google.getClosingVal();

        plotLine(gc);
        drawLinePlot(gc);
    }

    public void plotLine(GraphicsContext gc) {
        for (int x = 0; x < AAPL.length; x++) {
            if (AAPL[x] > maxValue) {
                maxValue = AAPL[x];
            }
        }

        for (int x = 0; x < GOOG.length; x++) {
            if (GOOG[x] > maxValue) {
                maxValue = GOOG[x];
            }
        }

        width = (x - 275) / (GOOG.length + 1);
        hieght = (y - 275) / maxValue;

        gc.strokeLine(100, 100, 100, y - 75);
        gc.strokeLine(100, y - 75, x - 100, y - 75);
    }

    private void drawLinePlot(GraphicsContext gc) {
        double start = 100;
        gc.setStroke(Color.BLUE);

        for (int x = 0; x < AAPL.length - 1; x++) {
            gc.strokeLine(start, (y - 100) - (AAPL[x] * hieght), (start + width), (y - 100) - (AAPL [x+1] * hieght));
            start = start + width;
        }
        start = 100;

        gc.setStroke(Color.RED);

        for (int x = 0; x < GOOG.length - 1; x++) {
            gc.strokeLine(start, (y - 100) - (GOOG[x] * hieght), (start + width), (y - 100) - (GOOG [x+1] * hieght));
            start = start + width;
        }
    }
}
