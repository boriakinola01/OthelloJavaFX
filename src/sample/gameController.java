package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class gameController {

    @FXML
    Pane boardPane;
    @FXML
    Label p1Score;
    @FXML
    Label p2Score;
    @FXML
    Label p1Name;
    @FXML
    Label p2Name;

    Board board = new Board();
    Player p1 = new Player(Color.BLACK);
    Player p2 = new Player(Color.WHITE);
    int turn;

    private static final int SIZE = 8;

    public gameController(){
        this.boardPane = new Pane();
        initialisePieces(board, p1, p2);
    }

    public void initialize(){
        displayBoard(board);
    }

    public void play(MouseEvent mouseEvent){
        while(board.getNumOfTiles() != SIZE*SIZE){
            if(turn == 1){
                if(board.movesAvailable(p1)){
                    board.placePiece(p1.getColor(), mouseEvent.getX() / 75, mouseEvent.getY() / 75);
                    board.setNumOfTiles(board.getNumOfTiles()+1);
                    updateScores();
                    System.out.println("step 2");
                }
                turn = 2;
            }
            if(turn == 2){
                if(board.movesAvailable(p2)){
                    board.placePiece(p2.getColor(), mouseEvent.getX() / 75, mouseEvent.getY() / 75);
                    board.setNumOfTiles(board.getNumOfTiles()+1);
                    updateScores();
                    System.out.println("step 3");
                }
                turn = 1;
            }
            System.out.println("step 4");
        }
    }

    public void setNames(String playerOneName, String playerTwoName){
        p1Name.setText(playerOneName);
        p2Name.setText(playerTwoName);
    }

    public void updateScores(){
        int x = 0, y = 0;
        for(int i=0; i<board.getSIZE(); i++){
            for(int j=0; j<board.getSIZE(); j++){
                if(board.getPieces()[i][j].getFill() == p1.getColor())
                    x++;

                if(board.getPieces()[i][j].getFill() == p2.getColor())
                    y++;
            }
        }

        p1.setScore(x);
        p2.setScore(y);
    }

    public void restartGame(){
        initialisePieces(this.board, p1, p2);
    }

    public void endGame(){
        board.endGame();
    }

    public void displayBoard(Board b){
        this.boardPane.getChildren().removeAll();
        for(int i =0; i < SIZE; i++){
            for (int j =0; j < SIZE; j++){
                this.boardPane.getChildren().add(b.getPieces()[i][j]);
            }
        }
    }

    public void initialisePieces(Board b, Player p1, Player p2){
        for(int i =0; i < SIZE; i++){
            for (int j =0; j < SIZE; j++){
                b.iniSetPiece(i, j, new Piece((i*75)+37.5, ((j*75)+37.5),32));
                b.setPieces(i, j, Color.TRANSPARENT);
            }
        }
        p1.setScore(2);
        p2.setScore(2);

        b.setPieces(3, 3, p1.getColor());
        b.setPieces(4, 4, p1.getColor());
        b.setPieces(3, 4, p2.getColor());
        b.setPieces(4, 3, p2.getColor());
        b.setNumOfTiles(4);
        turn = 1;
    }

}
