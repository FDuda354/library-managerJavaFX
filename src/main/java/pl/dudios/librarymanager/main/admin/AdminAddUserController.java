package pl.dudios.librarymanager.main.admin;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.mindrot.jbcrypt.BCrypt;
import pl.dudios.librarymanager.login.user.model.AppUser;
import pl.dudios.librarymanager.login.user.model.Role;
import pl.dudios.librarymanager.login.user.service.UserService;

import java.time.LocalDate;
import java.util.Optional;


public class AdminAddUserController {

    UserService userService;
    public TextField loginIdField;
    public TextField nameField;
    public TextField surnameField;
    public ChoiceBox roleBox;
    public TextField passwordField;
    public TextField peselField;
    public DatePicker birthDateField;


    @FXML
    public void initialize() {
        userService = new UserService();
        roleBox.getItems().addAll(Role.values());
    }

    @FXML
    public void addUser() {
        AppUser user = new AppUser();
        user.setLoginId(loginIdField.getText().trim());
        user.setName(nameField.getText().trim());
        user.setSurname(surnameField.getText().trim());
        user.setRole(Optional.ofNullable(roleBox.getValue()).map(String::valueOf).map(Role::valueOf).orElse(null));
        user.setPassword(BCrypt.hashpw(passwordField.getText().trim(), BCrypt.gensalt()));
        user.setPesel(peselField.getText().trim());
        user.setBirthDate(birthDateField.getValue());

        if (userService.saveUser(user))
            clearFields();

    }


    private void clearFields() {
        loginIdField.setText("");
        nameField.setText("");
        surnameField.setText("");
        roleBox.getSelectionModel().clearSelection();
        passwordField.setText("");
        peselField.setText("");
        birthDateField.setValue(null);
    }

}
