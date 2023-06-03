package com.example.snakeladder;
import java.net.StandardSocketOptions;
import java.util.ArrayList;
import javafx.util.Pair;

public class Board {
    ArrayList<Pair<Integer,Integer>> coordinatesAtNumber;
    ArrayList<Integer> snakeLadderPosition;

    public Board(){
        coordinatesAtNumber = new ArrayList<>();
        fillCoordinates();
        fillSnakesLadders();
    }
    private void fillCoordinates(){
        coordinatesAtNumber.add(new Pair<>(0,0));
        for (int i = 0; i < SnakeLadder.height; i++) {
            for (int j = 0; j < SnakeLadder.width; j++) {
                int xChord=0;
                if(i%2==0){
                     xChord=j*SnakeLadder.tileSize+SnakeLadder.tileSize/2;
                }
                else{
                     xChord=SnakeLadder.tileSize*SnakeLadder.height-j*SnakeLadder.tileSize-SnakeLadder.tileSize/2;
                }
                int yChord=SnakeLadder.tileSize*SnakeLadder.height-i*SnakeLadder.tileSize-SnakeLadder.tileSize/2;
                coordinatesAtNumber.add(new Pair<>(xChord,yChord));

            }
        }
    }
    private void fillSnakesLadders(){
        snakeLadderPosition = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            snakeLadderPosition.add(i);
        }
        snakeLadderPosition.set(4,25);
        snakeLadderPosition.set(13,46);
        snakeLadderPosition.set(27,5);
        snakeLadderPosition.set(33,49);
        snakeLadderPosition.set(40,3);
        snakeLadderPosition.set(42,63);
        snakeLadderPosition.set(43,18);
        snakeLadderPosition.set(50,69);
        snakeLadderPosition.set(54,31);
        snakeLadderPosition.set(62,81);
        snakeLadderPosition.set(66,45);
        snakeLadderPosition.set(74,92);
        snakeLadderPosition.set(76,58);
        snakeLadderPosition.set(89,53);
        snakeLadderPosition.set(99,41);

    }
    int getXCoordinates(int currPosition){
        if(currPosition>=1 && currPosition<=100){
            return coordinatesAtNumber.get(currPosition).getKey();
        }
        else{
            return -1;
        }
    }
    int getYCoordinates(int currPosition){
        if(currPosition>=1 && currPosition<=100){
            return coordinatesAtNumber.get(currPosition).getValue();
        }
        return -1;
    }
    int getSnakeLadderPos(int currentPosition){
        if(currentPosition>0 && currentPosition<=100){
            return snakeLadderPosition.get(currentPosition);
        }
        return -1;
    }

    public static void main(String[] args) {
        Board board = new Board();
        for (int i = 0; i < board.coordinatesAtNumber.size(); i++) {
            System.out.println(i+" -> X:"+board.coordinatesAtNumber.get(i).getKey()+
                    " Y:"+board.coordinatesAtNumber.get(i).getValue());
        }
    }
}

