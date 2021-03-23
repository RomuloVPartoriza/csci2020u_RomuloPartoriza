package csci2020u.lab08;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.EventHandler;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML private TableView tableView;
    @FXML private TableColumn sid;
    @FXML private TableColumn a;
    @FXML private TableColumn m;
    @FXML private TableColumn e;
    @FXML private TableColumn fM;
    @FXML private TableColumn g;
    @FXML private MenuItem mnuNew;
    @FXML private MenuItem mnuOpen;
    @FXML private MenuItem mnuSave;
    @FXML private MenuItem mnuSaveAs;
    @FXML private MenuItem mnuExit;
    @FXML private TextField sidT;
    @FXML private TextField midtermT;
    @FXML private TextField assignmentsT;
    @FXML private TextField finalT;

    public void handleSubmitButtonAction(ActionEvent actionEvent) {
        System.out.println("Add Pressed");
        String cSidT = sidT.getText();
        String cMidtermT = midtermT.getText();
        String cassignmentsTT = assignmentsT.getText();
        String cfinalT = finalT.getText();
        DataSource.addStudent(cSidT,Float.parseFloat(cMidtermT),Float.parseFloat(cassignmentsTT),Float.parseFloat(cfinalT));
    }

    File currentFile = new File("StudentGradesSaved.csv");

    @FXML
    public void initialize() {
        sid.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        a.setCellValueFactory(new PropertyValueFactory<>("assignments"));
        m.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        e.setCellValueFactory(new PropertyValueFactory<>("exam"));
        fM.setCellValueFactory(new PropertyValueFactory<>("finalMark"));
        g.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));
        tableView.setItems(DataSource.getAllMarks());

        // Drop down menu items
        mnuNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList<StudentRecord> clear = FXCollections.observableArrayList();
                tableView.setItems(clear);
                System.out.println("New Pressed");
            }
        });
        mnuOpen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                load();
                System.out.println("Open Pressed");
            }
        });
        mnuSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                save();
                System.out.println("Save Pressed");
            }
        });

        mnuSaveAs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Data Files", "*.csv")
                );
                fileChooser.setInitialDirectory(new File("."));
                Stage dialogStage = new Stage();
                currentFile = fileChooser.showOpenDialog(dialogStage);
                save();
                System.out.println("Save As Pressed");
            }
        });
        mnuExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
                System.out.println("Exit Pressed");
            }
        });
    }

    public void save(){
        StudentRecord record;
        List <List<String>> arrayList = new ArrayList<>();

        for (int i = 0; i < tableView.getItems().size(); i++){
            record = (StudentRecord) tableView.getItems().get(i);
            arrayList.add(new ArrayList<>());
            arrayList.get(i).add(record.getStudentID());
            arrayList.get(i).add(String.valueOf(record.getAssignments()));
            arrayList.get(i).add(String.valueOf(record.getMidterm()));
            arrayList.get(i).add(String.valueOf(record.getExam()));
        }

        String string = "";

        for (int i = 0; i < arrayList.size(); i++){

            for (int j = 0; j < arrayList.get(i).size(); j++){
                string += arrayList.get(i).get(j);
                string +=',';
            }

            string +='\n';

        }
        try(PrintWriter writer = new PrintWriter(currentFile)){
            writer.append(string);
        }
        catch (FileNotFoundException err){
            System.out.println(err.getMessage());
        }
    }

    public void load(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        Stage dialogStage = new Stage();
        File file = fileChooser.showOpenDialog(dialogStage);
        FileLoader loadFile = new FileLoader(file.getAbsolutePath());
        loadFile.readCSV();
        tableView.setItems(loadFile.getNewStudent());
    }
}
