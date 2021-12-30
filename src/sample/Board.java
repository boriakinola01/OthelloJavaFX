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
        boardPane = new Pane();
        p1 = new Player(Color.WHITE);
        p2 = new Player(Color.BLACK);
        pieceArray = new Piece[SIZE][SIZE];
        initialisePieces();
    }

    public void initialize(){

        p1Score.setText(Integer.toString(p1.getScore()));
        p2Score.setText(Integer.toString(p2.getScore()));

        arrayToBoard();

        boardPane.setOnMouseClicked(mouseEvent -> {
            try{
                placePiece(Color.BLUE,  mouseEvent.getX()/75, mouseEvent.getY()/75);
            } catch(Exception e){
            }
        });
    }

    public void placePiece(Color c, double x, double y){
        int newX, newY;
        newX = (int) x;
        newY = (int) y;
        if(pieceArray[newX][newY].getFill() == Color.TRANSPARENT){
            pieceArray[newX][newY].setFill(c);
        } else {
        }

    }

    public void initialisePieces(){
        for(int i =0; i < SIZE; i++){
            for (int j =0; j < SIZE; j++){
                pieceArray[i][j] = new Piece((i*75)+37.5, ((j*75)+37.5),32);
                pieceArray[i][j].setFill(Color.TRANSPARENT);
            }
        }
        p1.setScore(2);
        p2.setScore(2);

        pieceArray[3][3].setFill(p1.getColor());
        pieceArray[4][4].setFill(p1.getColor());
        pieceArray[3][4].setFill(p2.getColor());
        pieceArray[4][3].setFill(p2.getColor());
    }

    public void setNames(String playerOneName, String playerTwoName){
        p1Name.setText(playerOneName);
        p2Name.setText(playerTwoName);
    }


    public void endGame(){

    }

    public void updateScores(){
        int x = 0, y = 0;

        for(int i =0; i < SIZE; i++){
            for (int j =0; j < SIZE; j++){
                if(pieceArray[i][j].getFill() == p1.getColor())
                    x++;

                if(pieceArray[i][j].getFill() == p2.getColor())
                    y++;
            }
        }

        p1.setScore(x);
        p2.setScore(y);
    }

    public void arrayToBoard(){
        this.boardPane.getChildren().removeAll();
        for(int i =0; i < SIZE; i++){
            for (int j =0; j < SIZE; j++){
                this.boardPane.getChildren().add(pieceArray[i][j]);
            }
        }
    }


}