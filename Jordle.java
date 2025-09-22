import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.animation.RotateTransition;
import javafx.util.Duration;

/**
 * The main class for the Jordle game application.
 * This class extends JavaFX Application to create a word-guessing game.
 * @author Manav Patel
 * @version 11.0.19
 */
public class Jordle extends Application {

    /**
     * The main entry point for the JavaFX application.
     * This method is called after the application is launched.
     *
     * @param stage the primary stage for this application
     */
    @Override
    public void start(final Stage stage) {
        welcomeScreen(stage);
    }

    /**
     * Displays the welcome screen of the game.
     *
     * @param stage the primary stage for this application
     */
    public void welcomeScreen(final Stage stage) {
        stage.setTitle("Jordle");
        Image image = new Image("jordleImage.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(400);
        imageView.setFitWidth(600);

        Text text = new Text("Jordle");
        text.setX(250.0);
        text.setY(170.0);
        text.setFill(Color.RED);
        text.setFont(Font.font("Arial", 45));

        Button button = new Button("Play");
        button.setLayoutX(270);
        button.setLayoutY(180);
        button.setFont(Font.font("Arial", 15));
        button.setOnAction(e -> gameScreen(stage));

        Pane pane = new Pane();
        pane.getChildren().addAll(imageView, text, button);

        Scene scene = new Scene(pane, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Displays the main game screen.
     *
     * @param stage the primary stage for this application
     */
    public void gameScreen(final Stage stage) {
        Backend backend = new Backend();
        String targetWord = backend.getTarget().toUpperCase();
        Text text = textHelper("Jordle", 45, "Black", 400, 50);
        TilePane tilePane = tilePaneHelper();
        StackPane[][] stackPane = new StackPane[6][5];
        Label label1 = labelHelper(24, 300, 550, "Try guessing a word!");
        Button button2 = buttonHelper("Instructions", 19, 700, 500);
        button2.setOnAction(e -> instructions());
        Button button1 = buttonHelper("restart", 17, 600, 500);
        button1.setOnAction(e -> {
            gameScreen(stage);
            backend.reset();
        });

        Button button3 = buttonHelper("Dark Mode", 19, 435, 500);
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 5; col++) {
                stackPane[row][col] = new StackPane();
                Rectangle rectangle = new Rectangle(50, 50);
                rectangle.setStroke(Color.BLACK);
                rectangle.setFill(Color.WHITE);
                Label label = new Label();
                label.setFont(Font.font("Arial", 24));
                stackPane[row][col].getChildren().addAll(rectangle, label);
                tilePane.getChildren().add(stackPane[row][col]);
            }
        }
        final int[] currentRow = {0};
        final int[] currentCol = {0};
        final boolean[] rowCompleted = {false};
        final boolean[] proceed = {false};

        Pane pane = new Pane(tilePane, text, label1, button1, button2, button3);
        Scene scene = new Scene(pane, 1000, 700);
        button3.setOnAction(e -> {
            if (pane.getStyle().contains("black")) {
                pane.setStyle("-fx-background-color: white;");
                label1.setTextFill(Color.BLACK);
                button3.setText("Dark Mode");
                text.setFill(Color.BLACK);
            } else {
                pane.setStyle("-fx-background-color: black;");
                label1.setTextFill(Color.WHITE);
                button3.setText("Light Mode");
                text.setFill(Color.WHITE);
            }
        });
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent keyEvent) {
                if (!proceed[0]) {
                    if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
                        if (currentCol[0] > 0 && ((Rectangle) stackPane[currentRow[0]][4]
                                .getChildren().get(0)).getFill() == Color.WHITE) {
                            currentCol[0]--;
                            ((Label) stackPane[currentRow[0]][currentCol[0]]
                                    .getChildren().get(1)).setText("");
                        }
                    } else if (keyEvent.getText().matches("[a-zA-Z]")) {
                        if (currentCol[0] < 5) {
                            ((Label) stackPane[currentRow[0]][currentCol[0]]
                                    .getChildren().get(1)).setText(keyEvent.getText().toUpperCase());
                            currentCol[0]++;
                        }
                        if (currentCol[0] >= 5) {
                            rowCompleted[0] = true;
                            if (currentRow[0] < 5 && (((Rectangle) stackPane[currentRow[0]][currentCol[0] - 1]
                                    .getChildren().get(0)).getFill() == Color.GRAY
                                    || ((Rectangle) stackPane[currentRow[0]][currentCol[0] - 1]
                                    .getChildren().get(0)).getFill() == Color.GREEN
                                    || ((Rectangle) stackPane[currentRow[0]][currentCol[0] - 1]
                                    .getChildren().get(0)).getFill() == Color.YELLOW)) {
                                currentRow[0]++;
                                currentCol[0] = 0;
                                if (currentCol[0] < 5) {
                                    ((Label) stackPane[currentRow[0]][currentCol[0]]
                                            .getChildren().get(1)).setText(keyEvent.getText().toUpperCase());
                                    currentCol[0]++;
                                }
                            }
                        }
                    }
                    if (keyEvent.getCode() == KeyCode.ENTER) {
                        String[] guessedWord = new String[5];
                        for (int i = 0; i < 5; i++) {
                            guessedWord[i] = ((Label) stackPane[currentRow[0]][i]
                                    .getChildren().get(1)).getText();
                        }
                        String guess = String.join("", guessedWord);
                        try {
                            String correctWord = backend.check(guess);
                            String[] correctWordArray = correctWord.split("");
                            for (int l = 0; l < 5; l++) {
                                if (correctWordArray[l].equals("g")) {
                                    Rectangle rect = (((Rectangle) stackPane[currentRow[0]][l]
                                            .getChildren().get(0)));
                                    flip(rect, 2);
                                }
                                if (correctWordArray[l].equals("i")) {
                                    Rectangle rect = (((Rectangle) stackPane[currentRow[0]][l]
                                            .getChildren().get(0)));
                                    flip(rect, 0);
                                }
                                if (correctWordArray[l].equals("y")) {
                                    Rectangle rect = (((Rectangle) stackPane[currentRow[0]][l]
                                            .getChildren().get(0)));
                                    flip(rect, 1);
                                }
                            }
                            if (correctWord.equals("ggggg")) {
                                ImageView imageView7 = imageViewHelper("confetti.jpeg", 1000, 700);
                                pane.getChildren().removeAll(tilePane,
                                        text, label1, button1, button2, button3);
                                pane.getChildren().addAll(imageView7, tilePane, text,
                                        label1, button1, button2, button3);
                                label1.setText("Congratulations! You've guessed the word!");
                                mediaplayerHelper();
                                Stage stage1 = new Stage();
                                int attempts = currentRow[0] + 1;
                                Text text3 = textHelper("It took you " + attempts
                                        +
                                        " Attempts great job", 24, "White", 50, 225);
                                ImageView imageView6 = imageViewHelper("JordlePic.jpeg", 450, 450);
                                Pane pane2 = new Pane(imageView6, text3);
                                Scene scene2 = new Scene(pane2);
                                stageHelper(stage1, scene2, 450, 450);
                                proceed[0] = true;
                            }
                            if (currentRow[0] > 4 && currentCol[0] > 3 && !correctWord.equals("ggggg")) {
                                ImageView imageView5 = imageViewHelper("Nice.jpeg", 1000, 700);
                                pane.getChildren().removeAll(tilePane,
                                        text, label1, button1, button2, button3);
                                pane.getChildren().addAll(imageView5, tilePane,
                                        text, label1, button1, button2, button3);
                                label1.setTextFill(Color.WHITE);
                                label1.setText("Game over. The word was " + backend.getTarget());
                                proceed[0] = true;
                            }
                        } catch (InvalidGuessException i) {
                            alertHelper(i);
                        }
                    }
                }
            }
        });

        stage.setScene(scene);
        stage.show();
    }
    /**
     * Creates and configures an ImageView with the specified image and dimensions.
     *
     * @param name   the name or path of the image file
     * @param width  the desired width of the ImageView
     * @param height the desired height of the ImageView
     * @return       the configured ImageView
     */
    public ImageView imageViewHelper(final String name, final int width, final int height) {
        Image image = new Image(name);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }

    /**
     * Applies a flip animation to a Rectangle and changes its color based on the given number.
     *
     * @param rect the Rectangle to animate
     * @param num  determines the final color (0: Gray, 1: Yellow, 2: Green)
     */
    public void flip(final Rectangle rect, final int num) {
        RotateTransition flips = new RotateTransition(Duration.seconds(0.5), rect);
        flips.setAxis(Point3D.ZERO.add(0, 1, 0));
        flips.setFromAngle(0);
        flips.setToAngle(180);
        flips.setCycleCount(1);
        if (num == 0) {
            flips.setOnFinished(e -> rect.setFill(Color.GRAY));
        } else if (num == 1) {
            flips.setOnFinished(e -> rect.setFill(Color.YELLOW));
        } else if (num == 2) {
            flips.setOnFinished(e -> rect.setFill(Color.GREEN));
        }
        flips.play();
    }
    /**
     * Creates and displays an alert dialog for error messages.
     * @param exception The exception to display in the alert
     */
    public void alertHelper(Exception exception) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid Guess please enter a 5 Letter word");
        alert.setContentText(exception.getMessage());
        alert.showAndWait();
    }
    /**
     * Configures and displays a new Stage.
     * @param stage The Stage to configure
     * @param scene The Scene to set for the Stage
     * @param height The height of the Stage
     * @param width The width of the Stage
     */
    public void stageHelper(Stage stage, Scene scene, int height, int width) {
        stage.setScene(scene);
        stage.setHeight(height);
        stage.setWidth(width);
        stage.show();
    }
    /**
     * Creates a configured Button.
     * @param name The text to display on the button
     * @param size The font size for the button text
     * @param layoutx The X coordinate for the button's position
     * @param layouty The Y coordinate for the button's position
     * @return The created Button object
     */
    public Button buttonHelper(String name, int size, int layoutx, int layouty) {
        Button button = new Button(name);
        button.setFont(Font.font("Arial", size));
        button.setLayoutX(layoutx);
        button.setLayoutY(layouty);
        return button;
    }
    /**
     * Creates a configured Label.
     * @param size The font size for the label text
     * @param layoutx The X coordinate for the label's position
     * @param layouty The Y coordinate for the label's position
     * @param text The text to display on the label
     * @return The created Label object
     */
    public Label labelHelper(int size, int layoutx, int layouty, String text) {
        Label label = new Label();
        label.setFont(Font.font("Arial", size));
        label.setLayoutX(layoutx);
        label.setLayoutY(layouty);
        label.setText(text);
        return label;
    }
    /**
     * Creates a configured Text object.
     * @param name The text content
     * @param size The font size for the text
     * @param color The color of the text ("Black" or "White")
     * @param setx The X coordinate for the text's position
     * @param sety The Y coordinate for the text's position
     * @return The created Text object
     */
    public Text textHelper(String name, int size, String color, int setx, int sety) {
        Text text = new Text(name);
        text.setFont(Font.font("Arial", size));
        if (color.equals("Black")) {
            text.setFill(Color.BLACK);
        } else if (color.equals("White")) {
            text.setFill(Color.WHITE);
        }
        text.setX(setx);
        text.setY(sety);
        return text;
    }
    /**
     * Plays a media file.
     */
    public void mediaplayerHelper() {
        Media media = new Media(getClass()
                .getResource("tada-fanfare-a-6313.mp3").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
    /**
     * Creates and configures a TilePane.
     * @return The configured TilePane object
     */
    public TilePane tilePaneHelper() {
        TilePane tilePane = new TilePane();
        tilePane.setHgap(5);
        tilePane.setVgap(5);
        tilePane.setLayoutX(350);
        tilePane.setLayoutY(150);
        return tilePane;
    }

    /**
     * Displays the instructions for the game.
     */
    public void instructions() {
        Stage stage = new Stage();
        Text title = new Text("How To play Jordle");
        title.setFill(Color.BLACK);
        title.setX(100);
        title.setY(100);
        title.setFont(Font.font("Times New Roman", 20));

        Text instruction = new Text("So Jordle is exactly like wordle where you would have to "
                +
                "guess a 5 letter word and \n"
                + "then you will get hints like yellow means the letter is in the word"
                +
                " but its not in the correct spot \n"
                + "and green means its in the correct spot you will have 6 tries to guess the word \n"
                + "Good Luck!!!!");
        instruction.setFill(Color.BLACK);
        instruction.setX(100);
        instruction.setY(150);

        Pane pane = new Pane();
        pane.getChildren().addAll(title, instruction);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setHeight(400);
        stage.setWidth(650);
        stage.show();
    }

    /**
     * The main method to launch the application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        launch();
    }
}