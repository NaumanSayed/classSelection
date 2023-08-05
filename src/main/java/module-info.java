module com.example.assignment6 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.assignment6 to javafx.fxml;
    exports com.example.assignment6;
}