package com.example.snakeladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import javafx.animation.SequentialTransition;

public class Player {
     private Circle coin;
     private int currPosition;
     private String name;

    private static Board board = new Board();
    public Player(Color coinColor,int tileSize,String playerName){
        coin = new Circle(tileSize/2);
        coin.setFill(coinColor);
        currPosition = 0;
//        movePlayer(1);
        name = playerName;
    }
    public void movePlayer(int diceValue){
        if(currPosition+diceValue<=100) {
            currPosition += diceValue;
            TranslateTransition firstMove = translateAnimation();
            TranslateTransition secondMove = null;
            int newPos = board.getSnakeLadderPos(currPosition);
            if(newPos!=currPosition && newPos!=-1){
                currPosition = newPos;
                secondMove = translateAnimation();
            }
            if(secondMove == null){
                firstMove.play();
            }
            else{
                SequentialTransition sequentialTransition = new SequentialTransition(firstMove,
                        new PauseTransition(Duration.millis(200)),secondMove);
                sequentialTransition.play();
            }
        }
//        int x = board.getXCoordinates(currPosition);
//        int y = board.getYCoordinates(currPosition);
//        coin.setTranslateX(x);
//        coin.setTranslateY(y);


    }
    public TranslateTransition translateAnimation(){
        TranslateTransition animate = new TranslateTransition(Duration.millis(1000),coin);
        animate.setToX(board.getXCoordinates(currPosition));
        animate.setToY(board.getYCoordinates(currPosition));
        animate.setAutoReverse(false);
        return animate;
    }
    boolean isWinner(){
        if(currPosition==100){
            return true;
        }else{
            return false;
        }
    }

    public Circle getCoin() {
        return coin;
    }

    public int getCurrPosition() {
        return currPosition;
    }

    public String getName() {
        return name;
    }

    public void setCurrPosition(int currPosition) {
        this.currPosition = currPosition;
    }
}
