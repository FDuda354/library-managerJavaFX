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
        changeScene(new FXMLLoader(getClass().getResource("/pl/dudios/librarymanager/login/login-form.fxml")));
    }

    public void getAddUserPage(ActionEvent actionEvent) throws IOException {
        changeScene(new FXMLLoader(getClass().getResource("/pl/dudios/librarymanager/main/admin/admin-add-user-form.fxml")));
    }

    public void getUserPage(ActionEvent actionEvent) throws IOException {
        changeScene(new FXMLLoader(getClass().getResource("/pl/dudios/librarymanager/main/admin/admin-user-view-form.fxml")));
    }

    public void getAddBookPage(ActionEvent actionEvent) throws IOException {
        changeScene(new FXMLLoader(getClass().getResource("/pl/dudios/librarymanager/main/admin/admin-add-book-form.fxml")));
    }

    public void getBookPage(ActionEvent actionEvent) throws IOException {
        changeScene(new FXMLLoader(getClass().getResource("/pl/dudios/librarymanager/main/admin/admin-main-form.fxml")));
    }

    public void getUserBookPage(ActionEvent actionEvent) throws IOException {
        changeScene(new FXMLLoader(getClass().getResource("/pl/dudios/librarymanager/main/user/user-main-form.fxml")));
    }

    public void getUserBorrowBookPage(ActionEvent actionEvent) throws IOException {
        changeScene(new FXMLLoader(getClass().getResource("/pl/dudios/librarymanager/main/user/user-books-view.fxml")));
    }
    public void getUserHistoryPage(ActionEvent actionEvent) throws IOException {
        changeScene(new FXMLLoader(getClass().getResource("/pl/dudios/librarymanager/main/user/user-books-history-view.fxml")));
    }
    private void changeScene(FXMLLoader fxmlLoader) throws IOException {
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        Stage stage = (Stage) contentPane.getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }



}
