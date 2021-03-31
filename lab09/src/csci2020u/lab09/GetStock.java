package csci2020u.lab09;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class GetStock {
    private static String urlStock;
    private static String[][] data;
    private static float[] closingVal;

    GetStock (String stockSymbol) {
        this.urlStock = stockSymbol;
    }

    public static float[] getClosingVal() {
        return closingVal;
    }

    public static void downloadStockPrices() {
        String url = "https://query1.finance.yahoo.com/v7/finance/download/" + urlStock + "?period1=1262322000&period2=1616817600&interval=1mo&events=history&includeAdjustedClose=true";
        String line = "";
        int index = 0;

        try {
            URL csv = new URL(url);
            URLConnection urlConnection = csv.openConnection();
            Scanner input = new Scanner(urlConnection.getInputStream());

            data = new String[136 - 1][7];

            closingVal = new float[136 - 1];

            line = input.nextLine();

            while(input.hasNextLine()) {

                line = input.nextLine();
                String[] values = line.split(",");

                for(int i = 0; i <= 6; i++){
                    data[index][i] = values[i];
                }
                closingVal[index] = Float.parseFloat(data[index][3]);

                index++;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
