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
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);

        //Creates the start button
        Button startButton = new Button("Start Game");
        startButton.setStyle("-fx-font-size: 20px; -fx-padding: 10px");

        //Lambda Expresssion for Start Button Click
        startButton.setOnAction(event -> {
            GameController gameController = new GameController();
            gameController.startGame(primaryStage);
        });

        //Adding components to the root layout
        root.getChildren().add(startButton);

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
