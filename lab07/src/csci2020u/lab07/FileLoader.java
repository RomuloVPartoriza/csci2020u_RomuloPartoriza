package csci2020u.lab07;
import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class FileLoader {
    private final String filename;
    private int counter;
    private Map<String, Integer> wordCounts;

    public FileLoader(String filename){
        this.filename = filename;
        this.wordCounts = new TreeMap<>();
        this.counter = 0;
    }

    public void readCSV(){
        String line = "";

        try{
            BufferedReader br = new BufferedReader(new FileReader(this.filename));
            while ((line = br.readLine())!=null){
                String[] columns = line.split(",");
                countWord(columns);
                counter++;
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void countWord(String[] column){
        if(wordCounts.containsKey(column[5])){
            int previous = wordCounts.get(column[5]);
            wordCounts.put(column[5], previous + 1);
        } else {
            wordCounts.put(column[5], 1);
        }
    }

    public Map<String, Integer> getWordCounts() {
        return this.wordCounts;
    }

    public int getCounter() {
        return this.counter;
    }
}
