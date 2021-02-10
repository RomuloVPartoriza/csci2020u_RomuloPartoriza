package csci2020u.lab04;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.DatePicker;

public class Controller {
    @FXML TextField userName;
    @FXML PasswordField passwordField;
    @FXML TextField fullName;
    @FXML TextField address;
    @FXML TextField pNumber;
    @FXML DatePicker birthDate;

    public void handleSubmitButtonAction(ActionEvent actionEvent) {
        System.out.println("Username:" + userName.getText());
        System.out.println("Password:" + passwordField.getText());
        System.out.println("Full Name:" + fullName.getText());
        System.out.println("E-Mail:" + address.getText());
        System.out.println("Phone #:" + pNumber.getText());
        System.out.println("Date of Birth:" + birthDate.getValue());
    }
}
