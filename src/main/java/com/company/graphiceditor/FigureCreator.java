package com.company.graphiceditor;

import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;

public class FigureCreator {
    Color strokeColor, fillColor;
    String strokeType;
    double strokeWidth, x, y;

    public FigureCreator(Color fillColor, String strokeType, Color strokeColor, double strokeWidth, double x, double y) {
        reset(fillColor, strokeType, strokeColor, strokeWidth, x, y);
    }

    public void reset(Color fillColor, String strokeType, Color strokeColor, double strokeWidth, double x, double y) {
        setStrokeColor(strokeColor);
        setFillColor(fillColor);
        setStrokeType(strokeType);
        setStrokeWidth(strokeWidth);
        this.x = x;
        this.y = y;
    }

    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public void setStrokeType(String strokeType) {
        this.strokeType = strokeType;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public CircleFigure createCircle() {
        CircleFigure circle = new CircleFigure(25);
        circle.setFill(fillColor);
        circle.setStroke(strokeColor);
        circle.getStrokeDashArray().setAll(strokeDashTypeConverter(strokeType));
        circle.setStrokeWidth(strokeWidth);
        circle.setStrokeType(StrokeType.INSIDE);
        circle.setStrokeLineCap(StrokeLineCap.BUTT);
        circle.setLayoutX(x);
        circle.setLayoutY(y);
        return circle;
    }


    public RectangleFigure createRectangle() {
        RectangleFigure rectangle = new RectangleFigure(50, 40);

        rectangle.setFill(fillColor);
        rectangle.setStroke(strokeColor);
        rectangle.getStrokeDashArray().setAll(strokeDashTypeConverter(strokeType));
        rectangle.setStrokeWidth(strokeWidth);
        rectangle.setStrokeLineCap(StrokeLineCap.BUTT);
        rectangle.setLayoutX(x);
        rectangle.setLayoutY(y);

        return rectangle;
    }


    public LineFigure createLine() {
        LineFigure line = new LineFigure(50, 0, 20, 60);
        line.setFill(fillColor);
        line.getStrokeDashArray().setAll(strokeDashTypeConverter(strokeType));
        line.setStroke(strokeColor);
        line.setStrokeWidth(strokeWidth);
        line.setLayoutX(x);
        line.setLayoutY(y);
        line.setStrokeLineCap(StrokeLineCap.BUTT);

        return line;
    }

    public EllipseFigure createEllipse() {
        EllipseFigure ellipse = new EllipseFigure(30, 15);
        ellipse.setFill(fillColor);
        ellipse.setStroke(strokeColor);
        ellipse.setStrokeWidth(strokeWidth);
        ellipse.setLayoutX(x);
        ellipse.setLayoutY(y);
        ellipse.getStrokeDashArray().setAll(strokeDashTypeConverter(strokeType));
        ellipse.setStrokeLineCap(StrokeLineCap.BUTT);
        return ellipse;
    }


    public Double[] strokeDashTypeConverter(String type) {

        switch (type) {
            case "Dotted" -> {
                return new Double[]{2d};
            }
            case "Dashed" -> {
                return new Double[]{20d, 9d};
            }
            case "Solid" -> {
                return new Double[]{20d, 0d};
            }
            default -> throw new IllegalStateException("Unexpected value: " + strokeType);
        }
    }
}
