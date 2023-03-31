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

        /*
        jpackage --input target --name library --main-jar library-manager-1.jar --main-class pl.dudios.librarymanager.HelloApplication --type dmg --icon bookicon.icns --module-path /Users/dudzi/Library/Java/JavaVirtualMachines/javafx-sdk-18/lib --add-modules javafx.controls,javafx.fxml,org.controlsfx.controls,net.synedra.validatorfx,org.kordamp.ikonli.javafx,org.kordamp.bootstrapfx.core,org.hibernate,org.postgresql,org.mindrot.jbcrypt,org.projectlombok.lombok-->
        jpackage --input target --name library --main-jar library-manager-1.jar --main-class pl.dudios.librarymanager.HelloApplication --type dmg --icon bookicon.icns
        jpackage --input target --name library --main-jar library-manager-1.jar --main-class pl.dudios.librarymanager.HelloApplication --type exe --icon bookicon.ico
            jpackage --type dmg \
    --input ./target/classes \
    --name library \
    --main-jar library-manager-1.jar  \
    --main-class HelloApplication \
    --java-options "--module-path /ścieżka/do/Twojego/projektu/lib:/ścieżka/do/javafx/sdk/lib" \
    --java-options "--add-modules javafx.controls,javafx.fxml,java.persistence" \
    --java-options "--add-opens javafx.base/com.sun.javafx.runtime=ALL-UNNAMED" \
    --java-options "--add-opens javafx.controls/com.sun.javafx.scene.control.behavior=ALL-UNNAMED" \
    --java-options "--add-opens javafx.controls/com.sun.javafx.scene.control=ALL-UNNAMED" \
    --java-options "--add-opens javafx.graphics/com.sun.javafx.stage=ALL-UNNAMED" \
    --java-options "--add-opens javafx.base/com.sun.javafx.binding=ALL-UNNAMED" \
    --java-options "--add-opens javafx.base/com.sun.javafx.event=ALL-UNNAMED" \
    --java-options "--add-opens javafx.graphics/com.sun.javafx.tk.quantum=ALL-UNNAMED" \
    --java-options "--add-opens javafx.graphics/com.sun.javafx.scene=ALL-UNNAMED" \
    --java-options "--add-opens javafx.graphics/com.sun.javafx.text=ALL-UNNAMED" \
    --java-options "--add-opens javafx.graphics/com.sun.javafx.iio=ALL-UNNAMED"

         */


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