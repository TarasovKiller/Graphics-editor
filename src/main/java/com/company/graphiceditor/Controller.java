package com.company.graphiceditor;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    AnchorPane anchorPane;
    @FXML
    Pane pane;
    @FXML
    ColorPicker strokeColor;
    @FXML
    ChoiceBox<String> strokeType;
    @FXML
    ColorPicker fillColor;
    @FXML
    ToggleButton circleButton;
    @FXML
    ToggleButton lineButton;
    @FXML
    ToggleButton rectangleButton;
    @FXML
    ToggleButton ellipseButton;
    @FXML
    TextField strokeWidth;
    @FXML
    TextField paneWidth;
    @FXML
    TextField paneHeight;
    Frame frame;
    Shape activeFigure;
    String[] types = {"Dotted", "Dashed", "Solid"};
    FigureCreator figureCreator;
    FrameFigure frameFigure;
    ArrayList<String> input;
    PaneBorder paneBorder;

    public static void captureAndSavePane(Pane pane) {
        captureAndSavePane(pane, (int) pane.getPrefWidth(), (int) pane.getPrefHeight());
    }

    public static void captureAndSavePane(Pane pane, int width, int height) {
        FileChooser fileChooser = new FileChooser();
        System.out.println("pane width = " + Double.toString(pane.getPrefWidth()));
        System.out.println("pane height = " + Double.toString(pane.getPrefHeight()));

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files (*.png, *.jpg, *.gif)", "*.png", "*.jpg", "*.gif"));


        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try {

                WritableImage writableImage = new WritableImage(width, height);
                pane.snapshot(null, writableImage);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);

                ImageIO.write(renderedImage, "png", file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        strokeType.getItems().addAll(types);
        strokeType.setValue("Solid");
        input = new ArrayList<>();
        frame = new Frame();
        frameFigure = new FrameFigure();
        paneBorder = new PaneBorder(pane);
        pane.getChildren().add(paneBorder);
        paneWidth.setText(String.valueOf(pane.getPrefWidth()));
        paneHeight.setText(String.valueOf(pane.getPrefHeight()));
        figureCreator = new FigureCreator(fillColor.getValue(), strokeType.getValue(), strokeColor.getValue(), Double.parseDouble(strokeWidth.getText()), 0, 0);
        strokeType.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                strokeTypeAction();
            }
        });
        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                MouseButton click = mouseEvent.getButton();
                double mouseEventX = mouseEvent.getX();
                double mouseEventY = mouseEvent.getY();
                if (click == MouseButton.SECONDARY) {
                    removingFrameFigure();
                    createFrameFigure(mouseEventX, mouseEventY);
                }
                pane.requestFocus();
            }

        });

        pane.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        System.out.println(Arrays.toString(input.toArray()));
                        if (!input.contains(code)) {
                            input.add(code);
                        }
                        if (!frameFigure.isInBorder(pane)) frameFigure.setInBorder(pane);
                        else {

                            if (input.contains("EQUALS") || input.contains("PLUS")) frameFigure.expandUp();
                            if (input.contains("PERIOD")) frameFigure.expandRight();
                        }
                        if (input.contains("COMMA")) frameFigure.shrinkLeft();
                        if (input.contains("MINUS")) frameFigure.shrinkDown();
                        if (input.contains("A") || input.contains("LEFT")) frameFigure.moveLeft();
                        if (input.contains("D") || input.contains("RIGHT")) frameFigure.moveRight();
                        if (input.contains("W") || input.contains("UP")) frameFigure.moveUp();
                        if (input.contains("S") || input.contains("DOWN")) frameFigure.moveDown();


                    }
                });

        pane.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        input.remove(code);
                    }
                });

    }


    private void removingFrameFigure() {
        if (pane.getChildren().contains(frameFigure)) {
            Shape obj = frameFigure.getFigure();
            obj.setTranslateX(frameFigure.getTranslateX());
            obj.setTranslateY(frameFigure.getTranslateY());
            obj.fillProperty().unbind();
            obj.strokeProperty().unbind();
            pane.getChildren().remove(frameFigure);
            pane.getChildren().add(obj);
        }
    }

    private void createFrameFigure(double mouseEventX, double mouseEventY) {
        Shape figure = null;
        figureCreator.reset(fillColor.getValue(),
                strokeType.getValue(), strokeColor.getValue(),
                Double.parseDouble(strokeWidth.getText()), mouseEventX, mouseEventY);
        if (circleButton.isSelected()) {
            figure = figureCreator.createCircle();
        } else if (rectangleButton.isSelected()) {
            figure = figureCreator.createRectangle();
        } else if (lineButton.isSelected()) {
            figure = figureCreator.createLine();
        } else if (ellipseButton.isSelected()) {
            figure = figureCreator.createEllipse();
        }
        assert figure != null;
        figure.fillProperty().bind(fillColor.valueProperty());
        figure.strokeProperty().bind(strokeColor.valueProperty());

        frame.formIn(figure, mouseEventX, mouseEventY);
        frameFigure = new FrameFigure(figure, frame);

        if (frameFigure.isInBorder(pane, mouseEventX, mouseEventY)) {
            pane.getChildren().add(frameFigure);


        }

    }

    public void save() {
        double paneWidth = Double.parseDouble(this.paneWidth.getText());
        double paneHeight = Double.parseDouble(this.paneHeight.getText());
        int width = (int) (Math.min(paneWidth, pane.getPrefWidth()));
        int height = (int) (Math.min(paneHeight, pane.getPrefHeight()));
        removingFrameFigure();
        paneBorder.setVisible(false);
        captureAndSavePane(pane, width, height);
        paneBorder.setVisible(true);

    }

    public void exit() {
        Platform.exit();
    }

    public void instruction() {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Информация");
        info.setHeaderText("Как пользоваться программой");
        info.setContentText("1) Выберите нужный цвет контура и заливки\n" +
                "2) Установите толщину контура и тип контура для фигуры\n" +
                "3) Выберите нужную фигуру из панели 'Палитра'\n" +
                "4) Используйте клавиши '<'  '>'  '-'  '+'  'W'  'S'  'A'  'D' " +
                "чтобы изменить расположение и форму фигуры\n" +
                "5) Выберите размер изображения\n" +
                "6) Нажмите Файл -> Сохранить,чтобы сохранить рисунок\n" +
                "7) Нажмите Файл -> Выход, чтобы закрыть приложение");
        info.show();
    }

    public void setPaneBorderWidth() {
        double width = Math.min(Double.parseDouble(paneWidth.getText()), pane.getPrefWidth());
        paneBorder.setWidth(width);
        paneWidth.setText(String.valueOf(width));

    }

    public void setPaneBorderHeight() {
        double height = Math.min(Double.parseDouble(paneHeight.getText()), pane.getPrefHeight());
        paneBorder.setHeight(height);
        paneHeight.setText(String.valueOf(height));
    }

    public void widthAction() {
        frameFigure.getFigure().setStrokeWidth(Double.parseDouble(strokeWidth.getText()));
    }

    public void strokeTypeAction() {
        Shape figure = frameFigure.getFigure();
        figureCreator.setStrokeWidth(Double.parseDouble(strokeWidth.getText()));
        figure.getStrokeDashArray().clear();
        figure.getStrokeDashArray().addAll(figureCreator.strokeDashTypeConverter(strokeType.getValue()));
    }


}
