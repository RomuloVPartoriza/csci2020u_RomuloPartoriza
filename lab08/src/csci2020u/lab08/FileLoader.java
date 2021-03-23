package csci2020u.lab08;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.util.Map;

public class FileLoader {
    private final String filename;
    private ObservableList<StudentRecord> marks;

    public FileLoader(String filename){
        this.filename = filename;
        this.marks = FXCollections.observableArrayList();
    }

    public void readCSV(){
        String line = "";

        try{
            BufferedReader br = new BufferedReader(new FileReader(this.filename));
            while ((line = br.readLine())!=null){
                String[] columns = line.split(",");
                newStudent(columns);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void newStudent(String[] column){
        StudentRecord addStudent = new StudentRecord(column[0],Float.parseFloat(column[1]),Float.parseFloat(column[2]),Float.parseFloat(column[3]));
        marks.add(addStudent);
    }

    public ObservableList<StudentRecord> getNewStudent(){
        return this.marks;
    }
}
