package ehu.isad;

import ehu.isad.controller.ui.TxanponKud;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage stage;

    private TxanponKud txanponKud;

    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        pantailaKargatu();
        stage.setTitle("Azterketa 2017-18 Urtarrila");
        stage.setScene(scene);
        stage.show();
    }

    private void pantailaKargatu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Txanponak.fxml"));
        Parent UI = loader.load();
        scene = new Scene(UI);
        txanponKud = loader.getController();
        txanponKud.setMainApp(this);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
