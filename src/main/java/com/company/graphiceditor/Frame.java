package com.company.graphiceditor;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

public class Frame extends Rectangle {


    public Frame(Shape figure, double x, double y) {


        this.setFill(Color.TRANSPARENT);
        this.setStrokeType(StrokeType.OUTSIDE);
        this.setStroke(Color.GRAY);
        this.setOpacity(0.7);
        this.getStrokeDashArray().addAll(10d);
        this.setStrokeWidth(3);


        formIn(figure, x, y);
    }

    public Frame() {
        this(new RectangleFigure(100, 100), 0, 0);
    }

    public void formIn(Shape figure, double x, double y) {
        Class<? extends Shape> figureClass = figure.getClass();
        if (figureClass.equals(CircleFigure.class)) {
            double radius = ((CircleFigure) figure).getRadius();
            this.setWidth(radius * 2);
            this.setHeight(radius * 2);
            this.setLayoutX(x);
            this.setLayoutY(y);
            this.setTranslateX(-radius);
            this.setTranslateY(-radius);
        } else if (figureClass.equals(EllipseFigure.class)) {
            double radiusX = ((EllipseFigure) figure).getRadiusX();
            double radiusY = ((EllipseFigure) figure).getRadiusY();
            this.setWidth(radiusX * 2);
            this.setHeight(radiusY * 2);
            this.setLayoutX(x);
            this.setLayoutY(y);
            this.setTranslateX(-radiusX);
            this.setTranslateY(-radiusY);
        } else if (figureClass.equals(LineFigure.class)) {
            double endX = ((LineFigure) figure).getEndX();
            double startX = ((LineFigure) figure).getStartX();
            double endY = ((LineFigure) figure).getEndY();
            double startY = ((LineFigure) figure).getStartY();
            double strokeWidth = figure.getStrokeWidth();


            double width = endX - startX;
            double height = endY - startY;
            this.setWidth(Math.abs(width) + strokeWidth);
            this.setHeight(Math.abs(height) + strokeWidth);

            double minX = Math.min(startX, endX);
            double minY = Math.min(startY, endY);

            this.setLayoutX(x + minX);
            this.setLayoutY(y + minY);

        } else if (figureClass.equals(RectangleFigure.class)) {
            this.setWidth(((RectangleFigure) figure).getWidth() + 10);
            this.setHeight(((RectangleFigure) figure).getHeight() + 10);
            this.setLayoutX(x);
            this.setLayoutY(y);
            this.setTranslateX(-5);
            this.setTranslateY(-5);
        }
    }

    public void formIn(Shape figure) {
        formIn(figure, figure.getLayoutX(), figure.getLayoutY());
    }

    Frame(Shape figure) {
        this(figure, 0d, 0d);
    }
}
