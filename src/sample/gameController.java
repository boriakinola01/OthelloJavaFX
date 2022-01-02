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
    Player p1 = new Player(Color.WHITE);
    Player p2 = new Player(Color.BLACK);
    private int turn;

    private static final int SIZE = 8;

    public gameController(){
        this.boardPane = new Pane();
        initialisePieces(board, p1, p2);
    }

    public void initialize(){
        displayBoard(board);
        p1Score.setText(Integer.toString(p1.getScore()));
        p2Score.setText(Integer.toString(p2.getScore()));
    }

    public void play(MouseEvent mouseEvent){
        if(board.getNumOfTiles() >= SIZE*SIZE){
            endGame();
        }
        if(!board.movesAvailable(p1) && !board.movesAvailable(p2)){
            endGame();
        }

        if(getTurn() == 1){
            if(board.movesAvailable(p1)){
                playPlayer(p1, mouseEvent);
                System.out.println("step 2");
            }
            setTurn(2);
        } else if(getTurn() == 2){
            if(board.movesAvailable(p2)){
                playPlayer(p2, mouseEvent);
                System.out.println("step 3");
            }
            setTurn(1);
        }
        System.out.println("step 4");

    }

    public void playPlayer(Player p, MouseEvent e){
        board.placePiece(p.getColor(), e.getX() / 75, e.getY() / 75);
        board.setNumOfTiles(board.getNumOfTiles()+1);
        updateScores();
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
        p1Score.setText(Integer.toString(p1.getScore()));
        p2Score.setText(Integer.toString(p2.getScore()));
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
        setTurn(1);
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
}
