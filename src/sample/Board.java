package sample;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.Objects;

public class Board{


    @FXML
    Pane boardPane;
    @FXML
    Label p1Name;
    @FXML
    Label p2Name;
    @FXML
    Label p1Score;
    @FXML
    Label p2Score;

    private Player p1;
    private Player p2;

    private final int SIZE = 8;
    private Piece[][] pieceArray;

    public Board(){
        p1 = new Player(Color.WHITE);
        p2 = new Player(Color.BLACK);
        pieceArray = new Piece[SIZE][SIZE];
        initialisePieces();
        resetGame();
    }

    public void initialize(){

        p1Score.setText(Integer.toString(p1.getScore()));
        p2Score.setText(Integer.toString(p2.getScore()));

        updateScore();

        boardPane.setOnMouseClicked(mouseEvent -> {
            try{
                placePiece(mouseEvent.getX(), mouseEvent.getY());
            } catch(Exception e){
            }
        });
    }

    public void placePiece(double x, double y){
        int newX, newY;
        newX = (int) x/75;
        newY = (int) y/75;
        boardPane.getChildren().add(pieceArray[newX][newY]);

    }

    public void initialisePieces(){
        for(int i =0; i < SIZE; i++){
            for (int j =0; j < SIZE; j++){
                pieceArray[i][j] = new Piece((i*75)+37.5, ((j*75)+37.5),32);
            }
        }
    }

    public void setNames(String playerOneName, String playerTwoName){
        p1Name.setText(playerOneName);
        p2Name.setText(playerTwoName);
    }

    public void resetGame(){

    }

    public void updateScore(){

    }

}