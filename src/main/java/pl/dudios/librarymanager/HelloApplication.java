package pl.dudios.librarymanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //DataBase.fillBookDataBase();
        //DataBase.fillUserDataBase();
        //jpackage --input target --name library --main-jar library-manager-1.jar --main-class pl.dudios.librarymanager.HelloApplication --type dmg --icon bookicon.icns
        //jpackage --input target --name library --main-jar library-manager-1.jar --main-class pl.dudios.librarymanager.HelloApplication --type exe --icon bookicon.ico


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