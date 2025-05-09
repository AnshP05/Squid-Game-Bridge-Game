import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.geometry.*;
import javafx.event.*;
import java.util.Random;

public class GameController {

    //instance variables
    private Stage primaryStage;
    private GridPane bridgePane;
    private Button[][] tiles;
    private boolean[][] isSafe;
    private Random random;
    private boolean gameOver;

    //constructor
    public GameController(){
        gameOver = false;
        isSafe = new boolean[2][6];
        tiles = new Button[2][6];
        random = new Random();
        bridgePane = new GridPane();   
    }

    //methods
    public void startGame(Stage primaryStage){
        this.primaryStage = primaryStage;

        //configuring the bridgePane
        bridgePane.setAlignment(Pos.CENTER);
        bridgePane.setHgap(10);
        bridgePane.setVgap(5);
        bridgePane.getChildren().clear();

        //generating the bridge
    }

    public void generateBridge(){
        
    }
}
