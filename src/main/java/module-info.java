module com.group4.rvv {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.group4.rvv to javafx.fxml;
    exports com.group4.rvv;
    requires javafx.graphicsEmpty;
    requires com.google.gson;
    requires java.logging;
}
