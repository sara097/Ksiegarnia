package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginController {

    @FXML
    private Button clientBtn;

    @FXML
    private TextField loginTxt;

    @FXML
    private TextField passwordTxt;

    @FXML
    private Button loginBtn;

    @FXML
   void initialize()  {
        loginBtn.setDisable(true);
        loginTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            loginBtn.setDisable(newValue.trim().isEmpty());
        });
    }

    @FXML
    void clientClicked(ActionEvent event) {
        try {
            newScene("klient", "Klient");
            connect("klient", "");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void logInClicked(ActionEvent event) throws IOException {
        try {
            if (loginTxt.getText().equals("owner")) {
                connect("owner", passwordTxt.getText());
                newScene("wlasciciel", "Właściciel");

            } else if (loginTxt.getText().equals("ksiegowy")) {
                connect("ksiegowy", passwordTxt.getText());
                newScene("ksiegowa", "Ksiegowość");

            } else if (loginTxt.getText().equals("pracownik")) {
                connect("pracownik", passwordTxt.getText());
                newScene("pracownik", "Pracownik");

            } else {
                connect(loginTxt.getText(), passwordTxt.getText());
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Wystąpił błąd");
            alert.setContentText("Niepoprawny login lub hasło");

            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public void connect(String login, String password) throws SQLException {

        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Ksiegarnia;user=" + login + ";password=" + password;
        Connection con = null;
        con = DriverManager.getConnection(connectionUrl);
        Statement stmt = con.createStatement();
        System.out.println("connected");

    }

    private void newScene(String fxml, String name) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource((fxml + ".fxml")));
        primaryStage.setTitle(name);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setResizable(false);
        Stage stage = (Stage) clientBtn.getScene().getWindow();
        stage.close();
    }

}
