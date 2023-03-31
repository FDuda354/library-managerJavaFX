package pl.dudios.librarymanager.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import pl.dudios.librarymanager.login.service.LoginService;
import pl.dudios.librarymanager.login.user.model.AppUser;
import pl.dudios.librarymanager.login.user.model.Role;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

    LoginService loginService;
    @FXML
    public TextField usernameField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Button loginButton;
    @FXML
    public ImageView loginImage;
    @FXML
    public Label loginErrorLabel;

    public void initialize() {
        loginImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("book.gif"))));
        loginService = new LoginService();
    }

    public void login(ActionEvent actionEvent) throws IOException {

//        AppUser user = loginService.validateLogin(usernameField.getText(), passwordField.getText());
//
//        if (user == null) {
//            loginErrorLabel.setText("Niepoprawny login lub hasło!");
//            return;
//        }

        //AppUser user = loginService.validateLogin("w60846", "1234");
        AppUser user = loginService.validateLogin("admin", "admin");

        loginService.setUserLogged(user);
        String page = user.getRole().equals(Role.ADMIN) ? "admin/admin-main-form.fxml" : "user/user-main-form.fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pl/dudios/librarymanager/main/" + page));

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
}