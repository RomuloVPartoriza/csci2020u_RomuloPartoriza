package csci2020u.lab05;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {
    @FXML private TableView tableView;
    @FXML private TableColumn sid;
    @FXML private TableColumn a;
    @FXML private TableColumn m;
    @FXML private TableColumn e;
    @FXML private TableColumn fM;
    @FXML private TableColumn g;

    @FXML
    public void initialize(){
        sid.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        a.setCellValueFactory(new PropertyValueFactory<>("assignments"));
        m.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        e.setCellValueFactory(new PropertyValueFactory<>("exam"));
        fM.setCellValueFactory(new PropertyValueFactory<>("finalMark"));
        g.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));

        tableView.setItems(DataSource.getAllMarks());
    }
}
