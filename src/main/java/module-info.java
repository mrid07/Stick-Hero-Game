module com.example.ap_final {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.example.ap_final to javafx.fxml;
    exports com.example.ap_final;
}