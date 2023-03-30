package pl.dudios.librarymanager.common;

import javafx.scene.control.Alert;

public class AppAlert {

    private AppAlert() {
    }

    public static void errorAlert(String msg) {
        javafx.scene.control.Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static void successAlert(String msg) {
        javafx.scene.control.Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Dodano!");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
