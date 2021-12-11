module com.company.graphiceditor {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires java.desktop;


    opens com.company.graphiceditor to javafx.fxml;
    exports com.company.graphiceditor;
}