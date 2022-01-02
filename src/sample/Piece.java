package sample;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class Piece extends Circle {

    private int x;
    private int y;

    public Piece(double v, double v1, double v2) {
        super(v, v1, v2);

        setOnMouseClicked(event -> {
           x = (int)event.getX();
           y = (int)event.getY();
        });
    }

    public int getX() {
        return x;
    }

    public int getY(){
        return y;
    }
}
