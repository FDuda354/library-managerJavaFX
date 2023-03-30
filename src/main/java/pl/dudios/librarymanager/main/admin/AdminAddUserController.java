package pl.dudios.librarymanager.main.admin;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
        String login = loginIdField.getText();
        String name = nameField.getText();
        String surname = surnameField.getText();
        Role role = Optional.ofNullable(roleBox.getValue()).map(String::valueOf).map(Role::valueOf).orElse(null);
        System.out.println(role);
        String password = BCrypt.hashpw(passwordField.getText(), BCrypt.gensalt());
        String pesel = peselField.getText();
        LocalDate birthDate = birthDateField.getValue();

        AppUser user = new AppUser();
        user.setLoginId(login);
        user.setName(name);
        user.setSurname(surname);
        user.setRole(role);
        user.setPassword(password);
        user.setPesel(pesel);
        user.setBirthDate(birthDate);

        if(userService.saveUser(user))
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
