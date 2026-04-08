module org.example.tareaval {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.tareaval to javafx.fxml;
    exports org.example.tareaval;
}