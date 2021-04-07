package csci2020u.lab10;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Controller {
    @FXML private TextField usrTxtF;
    @FXML private TextField msgTxtF;

    @FXML
    public void send() throws IOException {
        String message = usrTxtF.getText() + ": " + msgTxtF.getText();
        var socket = new Socket("localhost", 25505);
        var out = new PrintWriter(socket.getOutputStream(), true);

        out.println(message);
        out.flush();
    }

    @FXML
    public void exit(){
        System.exit(0);
    }

}
