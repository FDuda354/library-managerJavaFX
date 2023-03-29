package pl.dudios.librarymanager.main.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminMenuController {

    public MenuBar contentPane;

    public void logout(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pl/dudios/librarymanager/login/login-form.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        Stage stage = (Stage) contentPane.getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }


    public void addUser(ActionEvent actionEvent) {
    }

    public void showUsers(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pl/dudios/librarymanager/main/admin/admin-user-view-form.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        Stage stage = (Stage) contentPane.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void addBook(ActionEvent actionEvent) {
    }

    public void showBooks(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pl/dudios/librarymanager/main/admin/admin-main-form.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        Stage stage = (Stage) contentPane.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
