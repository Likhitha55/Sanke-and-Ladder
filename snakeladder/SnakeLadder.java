package com.example.snakeladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SnakeLadder extends Application {
    public static final int tileSize=40,width=10,height=10,bottomLine=tileSize*height+50;

    boolean gameStarted=false,playerOneTurn=false,playerTwoTurn=false;

    private Dice dice = new Dice();

    private Player playerOne,playerTwo;
    private Pane content() {
        Pane root = new Pane();
        root.setPrefSize(width * tileSize, height * tileSize + 100);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);

                root.getChildren().add(tile);
            }
        }
        root.setStyle("-fx-background-color:grey;");
        Image img = new Image("C:\\Users\\likhi\\IdeaProjects\\SnakeLadder\\src\\main\\img.png");
        ImageView board = new ImageView();
        board.setImage(img);
        board.setFitWidth(width*tileSize);
        board.setFitHeight(height*tileSize);

        Button playerOneButton = new Button("Player 1");
        Button playerTwoButton = new Button("Player 2");
        Button startButton = new Button("Start");

        playerOneButton.setTranslateX(20);
        playerOneButton.setTranslateY(bottomLine);
        playerTwoButton.setTranslateX(300);
        playerTwoButton.setTranslateY(bottomLine);
        startButton.setTranslateX(170);
        startButton.setTranslateY(bottomLine);

        //Lable

        Label playerOneLabel = new Label("");
        Label playerTwoLabel = new Label("");
        Label startLabel = new Label("Let's begin!");

        playerOneLabel.setTranslateX(20);
        playerOneLabel.setTranslateY(bottomLine-25);
        playerTwoLabel.setTranslateX(300);
        playerTwoLabel.setTranslateY(bottomLine-25);
        startLabel.setTranslateX(170);
        startLabel.setTranslateY(bottomLine-25);
        startLabel.setTextFill(Color.GREENYELLOW);
        playerOneLabel.setTextFill(Color.BLACK);
        playerTwoLabel.setTextFill(Color.WHITE);

        playerOne = new Player(Color.BLACK,tileSize,"Ram");
        playerTwo = new Player(Color.WHITE,tileSize-5,"Sita");

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(playerOne.getCurrPosition()==100 || playerTwo.getCurrPosition()==100){
                    playerOneButton.setDisable(false);
                    playerOne.setCurrPosition(0);
                    playerTwo.setCurrPosition(0);
                }
                playerOne.movePlayer(1);
                playerTwo.movePlayer(1);
                gameStarted = true;
                playerOneTurn=true;
                startLabel.setText("Game Started");
                startButton.setDisable(true);
                playerOneLabel.setText("Your Turn! "+playerOne.getName());
            }
        });

        //Action on Button click
        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerOneTurn){
                        int diceValue = dice.getRolledDiceValue();
                        startLabel.setText("Dice Value: "+diceValue);
                        playerOne.movePlayer(diceValue);
                        if(playerOne.isWinner()==true){
                            startLabel.setText("Winner is "+playerOne.getName()+"!");
                            playerOneButton.setDisable(true);
                            playerTwoButton.setDisable(true);
                            playerOneLabel.setText("");
                            playerTwoLabel.setText("");
                            startButton.setText("Restart Game");
                            startButton.setDisable(false);
                        }else{
                            playerOneTurn = false;
                            playerTwoTurn = true;
                            playerOneButton.setDisable(true);
                            playerTwoButton.setDisable(false);
                            playerOneLabel.setText("");
                            playerTwoButton.setDisable(false);
                            playerTwoLabel.setText("Your Turn! "+playerTwo.getName());
                        }
                    }
                }
            }
        });

        //Action on player two button

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerTwoTurn){
                        int diceValue = dice.getRolledDiceValue();
                        startLabel.setText("Dice Value: "+diceValue);
                        playerTwo.movePlayer(diceValue);
                        if(playerTwo.isWinner()==true){
                            startLabel.setText("Winner is "+playerTwo.getName()+"!");
                            playerTwoTurn = false;
                            playerOneButton.setDisable(true);
                            playerTwoButton.setDisable(true);
                            playerOneLabel.setText("");
                            playerTwoLabel.setText("");
                            startButton.setText("Restart Game");
                            startButton.setDisable(false);

                        }else{
                            playerTwoTurn = false;
                            playerOneTurn = true;
                            playerTwoButton.setDisable(true);
                            playerOneButton.setDisable(false);
                            playerTwoLabel.setText("");
                            playerOneLabel.setText("Your Turn! "+playerOne.getName());
                        }

                    }
                }
            }
        });

        root.getChildren().addAll(
                board,playerOneButton,playerTwoButton,startButton,
                playerOneLabel,playerTwoLabel,startLabel,
                playerOne.getCoin(),playerTwo.getCoin()
        );

        return root;
    }
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(content());
        stage.setTitle("Snake & Ladder");
        stage.setScene(scene);
        stage.show();
        }


    public static void main(String[] args) {
        launch();
    }
}