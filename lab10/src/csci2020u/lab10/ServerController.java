package csci2020u.lab10;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


public class ServerController {
    @FXML private TextArea txtArea;

    @FXML
    public void initialize() throws IOException {
        ThreadPoolExecutor pool = (ThreadPoolExecutor)Executors.newFixedThreadPool(5);
        pool.execute(new NetCode());
    }

    @FXML
    public void setServerTextField(String message){
        String oldText = txtArea.getText();
        txtArea.setText(oldText + message + "\n\n");
    }

    @FXML
    public void exitBtn(){
        System.exit(0);
    }

    class NetCode implements Runnable{
        NetCode(){
        }
        @Override
        public void run() {
            ThreadPoolExecutor poolExc = (ThreadPoolExecutor)Executors.newFixedThreadPool(20);
            try (var listener = new ServerSocket(25505)) {
                System.out.println("The server is running... ");
                while (true) {
                    poolExc.execute(new instance(listener.accept()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    class instance implements Runnable {
        private Socket sock;

        instance(Socket socket) {
            System.out.println("New thread created");
            this.sock = socket;
        }

        @Override
        public void run() {
            System.out.println("Connected: " + sock);
            try {
                var in = new Scanner(sock.getInputStream());
                String message = in.nextLine();
                setServerTextField(message);

            } catch (Exception e) {
                System.err.println(e);

            } finally {

                try {
                    sock.close();
                } catch (IOException e) {

                }

                System.out.println("Closing " + sock);
            }
        }
    }

}
