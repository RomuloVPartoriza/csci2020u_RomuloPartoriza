package csci2020u.lab06;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;


public class Controller {
    @FXML private Canvas canvas;
    @FXML private GraphicsContext gc;

    //red
    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };

    //blue
    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };

    private static String[] ageGroups = {
        "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };

    private static int[] purchasesByAgeGroup = {
        648, 1021, 2453, 3173, 1868, 2247
    };

    private static Color[] pieColours = {
        Color.AQUA, Color.GOLD, Color.DARKORANGE,
        Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };

    @FXML
    public void initialize(){
        gc = canvas.getGraphicsContext2D();

        drawGraph(gc);
        drawPieChart(gc);
    }

    private void drawPieChart(GraphicsContext gc) {
        int numOfPurchasesAgeGr = 0;
        for (int purchasesForAgeGr: purchasesByAgeGroup){
            numOfPurchasesAgeGr += purchasesForAgeGr;
        }

        double startAngle = 0.0;
        for(int i = 0; i < purchasesByAgeGroup.length; i++){
            double slicePercentage = (double) purchasesByAgeGroup[i] / (double) numOfPurchasesAgeGr;
            double sweepAngle = slicePercentage * 360.0;

            gc.setFill(pieColours[i]);
            gc.fillArc(550,100,400,400,startAngle,sweepAngle, ArcType.ROUND);

            startAngle += sweepAngle;
        }
    }

    public void drawGraph(GraphicsContext gc){
        int x = 120;
        int y = 400;
        double xInc = (x/avgHousingPricesByYear.length);
        double width = 50;
        double maxVal = Double.NEGATIVE_INFINITY;
        double minVal = Double.MAX_VALUE;

        for(double val: avgHousingPricesByYear){
            if(val > maxVal){
                maxVal = val;
            } if(val < minVal){
                minVal = val;
            }
        }
        for(double val: avgCommercialPricesByYear){
            if(val > maxVal){
                maxVal = val;
            } if(val < minVal){
                minVal = val;
            }
        }

        gc.setFill(Color.RED);
        for(double val: avgHousingPricesByYear){
            double height = ((val-minVal) / (maxVal - minVal)) * y+50;
            gc.fillRect(width,((y-height)+100),xInc,height+10);
            width += 3.1 * xInc;
        }

        gc.setFill(Color.BLUE);
        width = xInc+50;
        for(double val: avgCommercialPricesByYear){
            double height = ((val-minVal) / (maxVal - minVal)) * y;
            gc.fillRect(width,((y-height)+100),xInc,height+10);
            width += 3.1 * xInc;
        }

        // spaces
        gc.setFill(Color.WHITE);
        for(double val: avgCommercialPricesByYear){
            double height = ((val-minVal) / (maxVal - minVal)) * y;
            gc.fillRect(width,((y-height)+100),xInc,height+10);
            width += 3.1 * xInc;
        }
    }
}


