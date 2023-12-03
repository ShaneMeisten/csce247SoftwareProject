module project {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens project to javafx.fxml;
    exports project;

    opens Code to javafx.fxml;
    exports Code;
}
