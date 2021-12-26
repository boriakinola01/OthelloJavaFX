package sample;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;


public class Board{

    @FXML
    Pane boardPane;

    private final int SIZE = 8;
    private Piece[][] pieceArray;

    public Board(){
        pieceArray = new Piece[SIZE][SIZE];
        initialisePieces();
    }

    public void initialize(){

        boardPane.setOnMouseClicked(mouseEvent -> {
            System.out.println(mouseEvent.getSceneX()/75);
            System.out.println(mouseEvent.getSceneY()/75);
            placePiece(mouseEvent.getSceneX(), mouseEvent.getSceneY());

        });

//        for(int i =0; i<SIZE; i++){
//            for (int j =0; j< SIZE; j++){
//                pieceArray[i][j] = new Piece((i*75)+37.5, (j*75)+37.5,32);
//                boardPane.getChildren().add(pieceArray[i][j]);
//            }
//        }

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

}
