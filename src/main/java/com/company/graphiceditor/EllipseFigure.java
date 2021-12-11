package com.company.graphiceditor;

import javafx.scene.shape.Ellipse;

public class EllipseFigure extends Ellipse implements Transformable {

    public EllipseFigure(double v, double v1) {
        super(v, v1);
    }

    public double getHeight() {
        return getRadiusY() * 2;
    }

    public double getWidth() {
        return getRadiusX() * 2;
    }


    @Override
    public void shrinkDown() {
        setRadiusY(getRadiusY() - 2);
    }

    @Override
    public void expandUp() {
        setRadiusY(getRadiusY() + 2);
    }

    @Override
    public void shrinkLeft() {
        setRadiusX(getRadiusX() - 2);
    }

    @Override
    public void expandRight() {
        setRadiusX(getRadiusX() + 2);
    }
}
