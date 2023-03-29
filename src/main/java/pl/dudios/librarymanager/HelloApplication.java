package pl.dudios.librarymanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import pl.dudios.librarymanager.db.DataBase;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //DataBase.fillBookDataBase();
        //DataBase.fillUserDataBase();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login/login-form.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.setTitle("Library Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}