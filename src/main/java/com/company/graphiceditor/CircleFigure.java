package com.company.graphiceditor;

import javafx.scene.shape.Circle;

public class CircleFigure extends Circle implements Transformable {

    public CircleFigure(double v) {
        super(v);
    }



    public double getHeight() {
        return getRadius()*2;
    }

    public double getWidth() {
        return getRadius()*2;
    }

    @Override
    public void shrinkDown() {
        setRadius(getRadius()-2);
    }
    @Override
    public void expandUp() {
        setRadius(getRadius()+2);
    }

    @Override
    public void shrinkLeft() {
        shrinkDown();
    }

    @Override
    public void expandRight() {
        expandUp();
    }
}
