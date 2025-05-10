import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.geometry.Pos;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage){
        //Creates the root layout
        GameController gameController = new GameController();
        VBox root = gameController.startGame(primaryStage);
   
        //Setting the main scene
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Squid Game Bridge Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
