package snake;

import engine.Difficulty;
import engine.GameWorld;

import java.util.Scanner;

import javafx.animation.AnimationTimer;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Pos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import javafx.stage.Stage;

import model.Direction;

/**
 * The main entry point for the snake program. This class handles all graphics
 * not related to the actual game (i.e the start screen and score screen), user
 * input for each screen, etc. Also handles the updating of world on a timed
 * interval.
 *
 * @author  Susanna Dong, Jim Harris
 * @version 1.0
 */
public class SnakeGame extends Application {

    private Stage window;
    private Scene startScene;
    private Scene gameScene;
    private Scene scoreScene;
    private ToggleGroup gameMode;
    private int finalScore;
    private GameWorld world;
    private long lastUpdateTime;

    public static final int SCREEN_WIDTH = 512;
    public static final int TILE_WIDTH = 32;

    @Override
    public void start(Stage stage) {
        finalScore = 0;
        window = stage;
        gameMode = new ToggleGroup();

        setupStartScene();
        window.setScene(startScene);
        window.setResizable(false);
        window.show();
    }

    /**
     * Sets startScene and adds elements to it. startScene is composed of:
     *     1) A title label
     *     2) A group of radio buttons for setting the game mode
     *     3) A button that when pressed will call setupGameScene and call play
     */
    private void setupStartScene() {
        Label title = new Label("Snake Game");
        title.setFont(new Font(20));
        RadioButton rb1 = new RadioButton("Easy");
        RadioButton rb2 = new RadioButton("Normal");
        RadioButton rb3 = new RadioButton("Hard");
        rb1.setUserData(Difficulty.EASY);
        rb2.setUserData(Difficulty.NORMAL);
        rb3.setUserData(Difficulty.HARD);
        rb1.setToggleGroup(gameMode);
        rb2.setToggleGroup(gameMode);
        rb3.setToggleGroup(gameMode);
        rb2.setSelected(true);
        Button playGame = new Button("Start Game!");
        playGame.requestFocus();
        playGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setupGameScene();
                window.setScene(gameScene);
                play();
            }
        });
        Rectangle spacer = new Rectangle(200, 300);
        spacer.setFill(null);
        VBox vbox = new VBox(25);
        HBox rbs = new HBox(rb1, rb2, rb3);
        rbs.setAlignment(Pos.CENTER);
        vbox.getChildren().add(title);
        vbox.getChildren().add(spacer);
        vbox.getChildren().add(rbs);
        vbox.getChildren().add(playGame);
        vbox.setAlignment(Pos.CENTER);
        StackPane stack = new StackPane();
        stack.getChildren().add(vbox);
        startScene = new Scene(stack, SnakeGame.SCREEN_WIDTH,
            SnakeGame.SCREEN_WIDTH);
        window.setScene(startScene);
    }

    /**
     * Sets the gameScene and adds elements to it. gameScene is composed of:
     *     1) A Rectangle for the background
     *     2) All of the elements from world
     * world handles the addition of all the game graphics to the screen with
     * the exception of the background, which you must add to gameScene
     * manually. You will need to set world in this method as well. Also, this
     * method must add an event to gameScene such that when WASD or the arrow
     * keys are pressed the snake will change direction.
     */
    private void setupGameScene() {
        Group gsGroup = new Group();
        gameScene = new Scene(gsGroup, SnakeGame.SCREEN_WIDTH,
            SnakeGame.SCREEN_WIDTH);
        Rectangle background = new Rectangle(0, 0, SnakeGame.SCREEN_WIDTH,
            SnakeGame.SCREEN_WIDTH);
        background.setFill(Color.AQUA);
        gsGroup.getChildren().add(background);
        gameScene.setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.W) {
                    world.setDirection(Direction.UP);
                } else if (e.getCode() == KeyCode.DOWN
                    || e.getCode() == KeyCode.W) {
                    world.setDirection(Direction.DOWN);
                } else if (e.getCode() == KeyCode.LEFT
                    || e.getCode() == KeyCode.A) {
                    world.setDirection(Direction.LEFT);
                } else if (e.getCode() == KeyCode.RIGHT
                    || e.getCode() == KeyCode.D) {
                    world.setDirection(Direction.RIGHT);
                }
            });
        world = new GameWorld(background, gameScene,
            (Difficulty) gameMode.getSelectedToggle().getUserData());
    }

    /**
     * Sets the scoreScene and adds elements to it. scoreScene is composed of:
     *     1) A label that shows the user's score from world.
     *     2) A highscore list of the top 10 scores that is composed of:
     *         a) A ListView of Nodes for player usernames.
     *             - If the player makes it into the top 10, they need to be
     *             able to set their username, so a TextField should be at the
     *             point in the list where they belong. All other fields can
     *             just be labels for existing users.
     *         b) A ListView of Integers for player scores.
     *             - If the player makes it into the top 10, they're score
     *             should be displayed at the proper place in the list.
     *         * Existing high scores can be found in highScores.csv in the
     *         resources folder.
     *     3) A button that when pressed will write the high scores in the list
     *     to highScores.csv in the resources folder in the same format in which
     *     you originally accessed them. The button should also change the scene
     *     for window to startScene.
     */
    private void setupScoreScene() {
        HBox scorelists = new HBox();
        scorelists.setAlignment(Pos.CENTER);
        Label scoreL = new Label(Integer.toString(world.getScore()));
        scoreL.setFont(new Font(32));
        ListView<Node> usernames = new ListView();
        ListView<Integer> scores = new ListView();
        scorelists.getChildren().add(usernames);
        scorelists.getChildren().add(scores);
        Button save = new Button("Save");
        int lowscore = Integer.MAX_VALUE;
        int counter = 0;
        try {
            Scanner scan = new Scanner(
                new File("src/main/resources/highScores.csv"));
            scan.useDelimiter("[,\\v] *");
            while (scan.hasNext()) {
                String name = scan.next();
                int score = Integer.parseInt(scan.next());
                Label label = new Label(name);
                usernames.getItems().add(label);
                scores.getItems().add(score);
                scan.nextLine();
                if (score < lowscore) {
                    lowscore = score;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        }
        TextField newText = new TextField();
        newText.requestFocus();
        if (counter < 10 && world.getScore() <= lowscore) {
            scores.getItems().add(world.getScore());
            usernames.getItems().add(newText);
        } else if (world.getScore() > lowscore) {
            int index = 0;
            for (Integer i : scores.getItems()) {
                if (i < world.getScore()) {
                    index++;
                }
            }
            index = 10 - index;
            scores.getItems().add(index, world.getScore());
            usernames.getItems().add(index, newText);
            scores.getItems().remove(scores.getItems().size() - 1);
            usernames.getItems().remove(usernames.getItems().size() - 1);
        }
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    PrintWriter p = new PrintWriter(
                        new File("src/main/resources/highScores.csv"));
                    int index = 0;
                    for (Node n : usernames.getItems()) {
                        if (n instanceof Label) {
                            p.print(((Label) n).getText());
                        } else if (n instanceof TextField) {
                            p.print(((TextField) n).getText());
                        }
                        p.print(",");
                        p.println(Integer.toString(
                            scores.getItems().get(index)));
                        index++;
                    }
                    p.close();
                } catch (FileNotFoundException e) {
                    System.out.println(e.toString());
                }
                setupStartScene();
            }
        });

        VBox box = new VBox(20);
        Rectangle spacerR = new Rectangle(20, 40);
        spacerR.setFill(null);
        scores.setPrefHeight(scores.getItems().size() * 24);
        usernames.setPrefHeight(usernames.getItems().size() * 24);
        box.getChildren().add(scoreL);
        box.getChildren().add(spacerR);
        box.getChildren().add(scorelists);
        box.getChildren().add(save);
        box.setAlignment(Pos.CENTER);
        StackPane stack = new StackPane();
        stack.getChildren().add(box);
        scoreScene = new Scene(stack, SnakeGame.SCREEN_WIDTH,
            SnakeGame.SCREEN_WIDTH);
        window.setScene(scoreScene);
    }

    /**
     * Starts the game loop. Assumes that the scene for window has been set to
     * gameScene and that world has been properly reset to the starting game
     * state.
     */
    public void play() {
        AnimationTimer timey = new AnimationTimer() {
            @Override
            public void handle(long currentTime) {
                if (System.currentTimeMillis()
                    - lastUpdateTime > world.getDelayTime()) {
                    world.update();
                    // DO NOT MODIFY ABOVE THIS LINE

                    if (world.isGameOver()) {
                        stop();
                        setupScoreScene();
                    }
                    // DO NOT MODIFY BELOW THIS LINE
                    lastUpdateTime = System.currentTimeMillis();
                }
            }
        };
        lastUpdateTime = System.currentTimeMillis();
        timey.start();
    }
}
