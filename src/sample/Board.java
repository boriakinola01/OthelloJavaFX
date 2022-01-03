package sample;

import javafx.scene.paint.Color;

public class Board{



    private final int SIZE = 8;
    public Piece[][] pieces = new Piece[SIZE][SIZE];
    private int numOfTiles;

    public int getSIZE() {
        return SIZE;
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    public void setPieces(int x, int y, Color c){
        this.pieces[x][y].setFill(c);
    }

    public void iniSetPiece(int x, int y, Piece piece){
        this.pieces[x][y] = piece;
    }

    public int getNumOfTiles() {
        return numOfTiles;
    }

    public void setNumOfTiles(int numOfTiles) {
        this.numOfTiles = numOfTiles;
    }

    public boolean placePiece(Color c, double x, double y){
        int newX, newY;
        newX = (int) x;
        newY = (int) y;

        if(checkMove(c, newX, newY) && (getPieces()[newX][newY].getFill() == Color.TRANSPARENT)){
            this.pieces[newX][newY].setFill(c);
            makeMove(c, newX, newY);
            numOfTiles++;
            return true;
        }
        return false;
    }

    public boolean checkMove(Color c, int row, int col){

        for(int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; ++j) {
                boolean check = validMove(c, i, j, row, col);
                if(check)
                    return true;
            }
        }
        return false;
    }

    public boolean validMove(Color c, int dRow, int dCol, int row, int col){
        Color opp = null;
        if(c == Color.BLACK)
            opp = Color.WHITE;
        else if(c == Color.WHITE)
            opp = Color.BLACK;

        if((row+dRow < 0) || (row+dRow > SIZE-1) || (col+dCol < 0) || (col+dCol > SIZE-1))
            return false;

        if(this.pieces[row+dRow][col+dCol].getFill() != opp)
            return false;

        if((row+dRow+dRow < 0) || (row+dRow+dRow > 7) || (col+dCol+dCol < 0) || (col+dCol+dCol > 7))
            return false;


        return lineCheck(c, dRow, dCol, row+dRow+dRow, col+dCol+dCol);

    }

    public boolean lineCheck(Color c, int dRow, int dCol, int row, int col){
        if(this.pieces[row][col].getFill() == c)
            return true;

        if((row+dRow < 0) || (row+dRow > SIZE-1) || (col+dCol < 0) || (col+dCol > SIZE-1))
            return false;

        return lineCheck(c, dRow, dCol, row+dRow, col+dCol);
    }

    public boolean movesAvailable(Player p){

        for(int row = 0; row<SIZE; row++){
            for(int col = 0; col<SIZE; col++){
                if(this.pieces[row][col].getFill() == Color.TRANSPARENT){
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
        Color opp = null;
        int dRow, dCol, x, y;

        if(c == Color.BLACK)
            opp = Color.WHITE;
        else if(c == Color.WHITE)
            opp = Color.BLACK;

        for(dCol = -1; dCol <= 1; dCol++){
            for(dRow = -1; dRow <= 1; dRow++){

                if((row+dRow < 0) || (row+dRow > SIZE-1) ||
                        (col+dCol < 0) || (col+dCol > SIZE-1) ||
                        (dRow == 0 & dCol == 0))
                    continue;

                if(this.pieces[row+dRow][col+dCol].getFill() == opp){

                    x = row+dRow;
                    y = col+dCol;

                    while(true){

                        x += dRow;
                        y += dCol;

                        if(x < 0 || x > SIZE-1 || y <0 || y > SIZE-1)
                            break;

                        if(this.pieces[x][y].getFill() == Color.TRANSPARENT)
                            break;

                        if(this.pieces[x][y].getFill() == c){
                            while(this.pieces[x-=dRow][y-=dCol].getFill() == opp)
                                this.pieces[x][y].setFill(c);
                            break;
                        }
                    }
                }
            }
        }
    }

}