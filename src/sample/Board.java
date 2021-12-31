package sample;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

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

    private int numOfTiles;

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
                playGame(mouseEvent.getX()/75, mouseEvent.getY()/75);
            } catch(Exception ignored){
            }
        });
    }

    public void playGame(double row, double col){
        while(numOfTiles != SIZE*SIZE){
            if(movesAvailable(p1)){
                placePiece(p1.getColor(), row, col);
            }

            if(movesAvailable(p2)){
                placePiece(p2.getColor(), row, col);
            }

            if(!movesAvailable(p1) && !movesAvailable(p2))
                endGame();
        }
    }

    public void placePiece(Color c, double x, double y){
        int newX, newY;
        newX = (int) x;
        newY = (int) y;
        if(checkMove(c, newX, newY)){
            if(pieceArray[newX][newY].getFill() == Color.TRANSPARENT){
                pieceArray[newX][newY].setFill(c);
            }
        }

        makeMove(c, newX, newY);
        updateScores();
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
        numOfTiles = 4;
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

    public boolean checkMove(Color c, double row, double col){

        for(int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; ++j) {

                boolean check = validMove(c, i, j, (int)row, (int)col);
                if(check)
                    return true;
            }
        }
        return false;
    }

    public boolean validMove(Color c, int dRow, int dCol, int row, int col){
        Color opp = Color.TRANSPARENT;
        if(c == Color.BLACK)
            opp = Color.WHITE;
        else if(c == Color.WHITE)
            opp = Color.BLACK;

        if((row+dRow < 0) || (row+dRow > SIZE-1) || (col+dCol < 0) || (col+dCol > SIZE-1))
            return false;

        if(pieceArray[row+dRow][col+dCol].getFill() != opp)
            return false;

        if((row+dRow+dRow < 0) || (row+dRow+dRow > 7) || (col+dCol+dCol < 0) || (col+dCol+dCol > 7))
            return false;


        return lineCheck(c, dRow, dCol, row+dRow+dRow, col+dCol+dCol);

    }

    public boolean lineCheck(Color c, int dRow, int dCol, int row, int col){
        if(pieceArray[row][col].getFill() == c)
            return true;

        if((row+dRow < 0) || (row+dRow > SIZE-1) || (col+dCol < 0) || (col+dCol > SIZE-1))
            return false;

        return lineCheck(c, dRow, dCol, row+dRow, col+dCol);
    }

    public boolean movesAvailable(Player p){

        for(int row = 0; row<SIZE; row++){
            for(int col = 0; col<SIZE; col++){
                if(pieceArray[row][col].getFill() == Color.TRANSPARENT){
                    for(int i = -1; i <= 1; i++){
                        for (int j = -1; j <= 1; ++j) {
                            boolean check = validMove(p.getColor(), i, j, row, col);
                            if(check)
                                return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public void makeMove(Color c, int row, int col){
        Color opp = Color.TRANSPARENT;
        int dRow, dCol, x, y;

        if(c == Color.BLACK)
            opp = Color.WHITE;
        else if(c == Color.WHITE)
            opp = Color.BLACK;

        for(dCol = -1; dCol <= 1; dCol++){
            for(dRow = -1; dRow <= 1; dRow++){

                if((row+dRow < 0) || (row+dRow > SIZE) ||
                        (col+dCol < 0) || (col+dCol > SIZE) ||
                        (dRow == 0 & dCol == 0))
                    continue;

                if(pieceArray[row+dRow][col+dCol].getFill() == opp){

                    x = row+dRow;
                    y = col+dCol;

                    while(true){

                        x += dRow;
                        y += dCol;

                        if(x < 0 || x > SIZE-1 || y <0 || y > SIZE-1)
                            break;

                        if(pieceArray[x][y].getFill() == Color.TRANSPARENT)
                            break;

                        if(pieceArray[x][y].getFill() == c){
                            while(pieceArray[x-=dRow][y-=dCol].getFill() == opp)
                                pieceArray[x][y].setFill(c);
                            break;
                        }
                    }
                }
            }
        }
    }

}