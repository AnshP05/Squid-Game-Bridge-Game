import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.geometry.*;
import javafx.event.*;
import java.util.Random;

public class GameController {

    // Instance variables
    private Stage primaryStage;
    private GridPane bridgePane;
    private Button[][] tiles;
    private boolean[][] isSafe;
    private Random random;
    private boolean gameOver;
    private Button restartButton;
    Label messageLabel;
    private int currentCol = 0;

    // Image resources
    private Image safeImage = new Image(getClass().getResourceAsStream("Images/foot-steps.png"));
    private Image unsafeImage = new Image(getClass().getResourceAsStream("Images/cracks.png"));


    // Constructor
    public GameController() {
        gameOver = false;
        isSafe = new boolean[2][6];
        tiles = new Button[2][6];
        random = new Random();
        bridgePane = new GridPane();
    }

    // Start the game
    public VBox startGame(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Configuring the bridgePane
        bridgePane.setAlignment(Pos.CENTER);
        bridgePane.setHgap(10);
        bridgePane.setVgap(5);
        bridgePane.getChildren().clear();
        generateBridge();

        restartButton = new Button("Restart Game");
        restartButton.setStyle("-fx-font-size: 16px; -fx-padding: 8px;");
        restartButton.setOnAction(event -> resetGame());

        messageLabel = new Label("");
        messageLabel.setStyle("-fx-font-size: 24px;");

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(bridgePane, messageLabel, restartButton);
        return root;
    }

    // Generate the bridge
    public void generateBridge() {
        for (int col = 0; col < 6; col++) {
            int safeRow = random.nextInt(2);
            isSafe[safeRow][col] = true;
            isSafe[1 - safeRow][col] = false;

            for (int row = 0; row < 2; row++) {
                Button button = new Button("?");
                button.setMinSize(100, 100);
                button.setStyle("-fx-padding: 0; -fx-background-insets: 0; -fx-border-color: black;");
                button.setFocusTraversable(false);


                tiles[row][col] = button;

                int finalRow = row;
                int finalCol = col;
                button.setOnAction(event -> handleTileClick(finalRow, finalCol));

                bridgePane.add(button, col, row);
            }
        }
    }

    // Handle tile click
    public void handleTileClick(int row, int col) {
        if (gameOver)
            return;
        if (col > currentCol)
            return;

        tiles[row][col].setText("");  // Clear the "?" symbol

        if (isSafe[row][col]) {
            // Safe tile - show foot-steps
            ImageView safeView = new ImageView(safeImage);
            safeView.setFitWidth(100);
            safeView.setFitHeight(100);
            safeView.setPreserveRatio(true);
            tiles[row][col].setGraphic(safeView);
            tiles[row][col].setDisable(true);
            tiles[1 - row][col].setDisable(true);
            if (currentCol == col) currentCol++;
            if (col == 5)
                winGame();
        } else {
            // Unsafe tile - show cracks
            ImageView unsafeView = new ImageView(unsafeImage);
            unsafeView.setFitWidth(100);
            unsafeView.setFitHeight(100);
            unsafeView.setPreserveRatio(true);
            tiles[row][col].setGraphic(unsafeView);
            tiles[row][col].setDisable(true);
            tiles[1 - row][col].setDisable(true);
            gameOver();
        }
    }

    // Display win message
    public void winGame() {
        gameOver = true;
        for (int r = 0; r < 2; r++) {
            for (int c = 0; c < 6; c++) {
                tiles[r][c].setDisable(true);
            }
        }
        messageLabel.setText("You Win!");
        messageLabel.setStyle("-fx-font-size: 30px; -fx-text-fill: green;");
    }

    // Display game over message
    public void gameOver() {
        gameOver = true;
        for (int r = 0; r < 2; r++) {
            for (int c = 0; c < 6; c++) {
                tiles[r][c].setDisable(true);
            }
        }
        messageLabel.setText("Game Over");
        messageLabel.setStyle("-fx-font-size: 30px; -fx-text-fill: red;");
    }

    // Reset the game
    public void resetGame() {
        gameOver = false;
        currentCol = 0;
        messageLabel.setText("");
        bridgePane.getChildren().clear();
        generateBridge();
    }
}
