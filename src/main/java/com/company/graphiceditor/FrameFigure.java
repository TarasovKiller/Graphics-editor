package com.company.graphiceditor;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class FrameFigure extends Group implements Moveable, Transformable {
    private Shape figure;
    private Frame frame;
    public Class<? extends Shape> figureClass;

    public FrameFigure() {
        this(new RectangleFigure(100, 100), new Frame());
    }

    @Override
    public void moveRight() {
        this.setTranslateX(this.getTranslateX() + 2);
    }

    @Override
    public void moveLeft() {
        this.setTranslateX(this.getTranslateX() - 2);
    }

    @Override
    public void moveUp() {
        this.setTranslateY(this.getTranslateY() - 2);
    }

    @Override
    public void moveDown() {
        this.setTranslateY(this.getTranslateY() + 2);
    }

    public FrameFigure(Shape figure, Frame frame) {
        setFrameAndFigure(figure, frame);
    }

    public void setFrameAndFigure(Shape figure, Frame frame) {
        this.frame = frame;
        this.figure = figure;
        this.figureClass = figure.getClass();
        this.getChildren().addAll(figure, frame);


    }

    public boolean isInBorder(Pane pane, double MouseX, double MouseY) {
        double frameFigureWidth = this.getWidth();
        double frameFigureHeight = this.getHeight();
        return ((frameFigureWidth + MouseX) < pane.getPrefWidth()) &&
                (frameFigureHeight + MouseY) < pane.getPrefHeight() &&
                MouseX > 0 && MouseY > 0;
    }

    public boolean isInBorder(Pane pane) {


        double mouseX = frame.getLayoutX() + this.getTranslateX() + frame.getTranslateX();
        double mouseY = frame.getLayoutY() + this.getTranslateY() + frame.getTranslateY();
        return isInBorder(pane, mouseX,
                mouseY);
    }

    public void setInBorder(Pane pane) {
        double frameFigureWidth = this.getWidth();
        double frameFigureHeight = this.getHeight();
        double mouseX = frame.getLayoutX() + this.getTranslateX() + frame.getTranslateX();
        double mouseY = frame.getLayoutY() + this.getTranslateY() + frame.getTranslateY();
        double needToTranslateX = pane.getPrefWidth() + 1 - frameFigureWidth - mouseX;
        double needToTranslateY = pane.getPrefHeight() + 1 - frameFigureHeight - mouseY;


        if ((frameFigureWidth + mouseX) >= pane.getPrefWidth()) {
            this.setTranslateX(getTranslateX() + needToTranslateX);
        }
        if ((frameFigureHeight + mouseY) >= pane.getPrefHeight()) {
            this.setTranslateY(getTranslateY() + needToTranslateY);
        }
        if (mouseX < 0) {
            this.setTranslateX(getTranslateX() + 1 - mouseX);
        }
        if (mouseY < 0) {
            this.setTranslateY(getTranslateY() + 1 - mouseY);
        }
    }

    public double getWidth() {
        return frame.getWidth();
    }

    public double getHeight() {
        return frame.getHeight();
    }

    public void setShape(Shape shape) {
        figure = shape;
        figureClass = shape.getClass();
    }

    @Override
    public void shrinkDown() {
        if (figureClass.equals(CircleFigure.class))
            ((CircleFigure) figure).shrinkDown();
        else if (figureClass.equals(RectangleFigure.class))
            ((RectangleFigure) figure).shrinkDown();
        else if (figureClass.equals(EllipseFigure.class))
            ((EllipseFigure) figure).shrinkDown();
        else if (figureClass.equals(LineFigure.class))
            ((LineFigure) figure).shrinkDown();
        frame.formIn(figure);
    }

    @Override
    public void expandUp() {
        if (figureClass.equals(CircleFigure.class))
            ((CircleFigure) figure).expandUp();
        else if (figureClass.equals(RectangleFigure.class))
            ((RectangleFigure) figure).expandUp();
        else if (figureClass.equals(EllipseFigure.class))
            ((EllipseFigure) figure).expandUp();
        else if (figureClass.equals(LineFigure.class))
            ((LineFigure) figure).expandUp();
        frame.formIn(figure);
    }

    @Override
    public void shrinkLeft() {
        if (figureClass.equals(CircleFigure.class))
            ((CircleFigure) figure).shrinkLeft();
        else if (figureClass.equals(RectangleFigure.class))
            ((RectangleFigure) figure).shrinkLeft();
        else if (figureClass.equals(EllipseFigure.class))
            ((EllipseFigure) figure).shrinkLeft();
        else if (figureClass.equals(LineFigure.class))
            ((LineFigure) figure).shrinkLeft();
        frame.formIn(figure);
    }

    @Override
    public void expandRight() {
        if (figureClass.equals(CircleFigure.class))
            ((CircleFigure) figure).expandRight();
        else if (figureClass.equals(RectangleFigure.class))
            ((RectangleFigure) figure).expandRight();
        else if (figureClass.equals(EllipseFigure.class))
            ((EllipseFigure) figure).expandRight();
        else if (figureClass.equals(LineFigure.class))
            ((LineFigure) figure).expandRight();
        frame.formIn(figure);
    }

    public Shape getFigure() {
        return figure;
    }
}
