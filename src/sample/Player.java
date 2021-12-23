package sample;

import javafx.scene.paint.Color;

public class Player {

    protected Color color;
    protected String name;
    protected int score;

    public Player(String name, Color color){
        this.name = name;
        this.color = color;
    }

    public int getScore(){
        return score;
    }

    public void setScore(int score){
        this.score = score;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

}
