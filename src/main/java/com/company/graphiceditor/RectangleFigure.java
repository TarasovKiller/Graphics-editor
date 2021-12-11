package com.company.graphiceditor;

import javafx.scene.shape.Rectangle;

public class RectangleFigure extends Rectangle implements Transformable {
    public RectangleFigure(double v, double v1) {
        super(v, v1);
    }

    @Override
    public void shrinkDown() {
        setHeight(getHeight() - 2);
    }

    @Override
    public void expandUp() {
        setHeight(getHeight() + 2);
    }

    @Override
    public void shrinkLeft() {
        setWidth(getWidth() - 2);
    }

    @Override
    public void expandRight() {
        setWidth(getWidth() + 2);
    }
}
