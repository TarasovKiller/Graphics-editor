package com.company.graphiceditor;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PaneBorder extends Rectangle {
    Pane pane;

    public PaneBorder(Pane pane, double width, double height) {
        this.pane = pane;
        setFill(Color.TRANSPARENT);
        setStroke(Color.RED);
        setStrokeWidth(1);
        setWidth(width);
        setHeight(height);
    }

    public PaneBorder(Pane pane) {
        this(pane, pane.getPrefWidth(), pane.getPrefHeight());
    }
}
