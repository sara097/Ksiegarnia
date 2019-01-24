package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class Main crates Stage and launches application.
 *
 * @author Sara Strzałka
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Sets scene name and size and shows scene.
     *
     * @param primaryStage Stage.
     * @throws Exception Exception.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("LoginWindow.fxml"));
        primaryStage.setTitle("Zaloguj się");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setResizable(false);

    }

    /**
     *Launches the application.
     *
     * @param args Initial arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
