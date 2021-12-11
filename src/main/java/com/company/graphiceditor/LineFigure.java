package com.company.graphiceditor;

import javafx.scene.shape.Line;

public class LineFigure extends Line implements Transformable {
    public LineFigure(double v, double v1, double v2, double v3) {
        super(v, v1, v2, v3);
    }

    public double getWidth() {
        return Math.abs(getEndX() - getStartX());
    }

    public double getHeight() {
        return Math.abs(getEndY() - getStartY());
    }

    @Override
    public void shrinkDown() {
        setEndY(getEndY() + 2);
    }

    @Override
    public void expandUp() {
        setEndY(getEndY() - 2);
    }

    @Override
    public void shrinkLeft() {
        setEndX(getEndX() - 2);
    }

    @Override
    public void expandRight() {
        setEndX(getEndX() + 2);
    }
}
