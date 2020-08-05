package example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application implements ParentController {

    Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../xml/startMakingCat.fxml"));
        StartMakingCatController sMCC = new StartMakingCatController();
        sMCC.setParentController(this);
        loader.setController(sMCC);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Tamagotchi");
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();
    }

    public void createMainScreen() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../xml/mainScreen.fxml"));
            Parent root = loader.load();
            ((ControllerFXML) loader.getController()).setParentController(this);
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {
        launch(args);
    }


    public Stage getStage() {
        return primaryStage;
    }
}
