package pl.dudios.librarymanager.main.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pl.dudios.librarymanager.login.user.model.AppUser;
import pl.dudios.librarymanager.login.user.model.Role;
import pl.dudios.librarymanager.login.user.model.fx.AppUserFX;
import pl.dudios.librarymanager.login.user.service.UserService;

public class AdminUpdateUserController {

    private UserService userService;
    private Long userId;
    @FXML
    private TextField loginIdField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private ChoiceBox<String> roleBox;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField peselField;
    @FXML
    private DatePicker birthDateField;

    public void setUser(AppUserFX user) {
        userService = new UserService();
        userId = user.getId();
        loginIdField.setText(user.getLoginId());
        nameField.setText(user.getName());
        surnameField.setText(user.getSurname());
        roleBox.setValue(user.getRole().name());
        passwordField.setText("");
        peselField.setText(user.getPesel());
        birthDateField.setValue(user.getBirthDate());
    }

    public void updateUser(ActionEvent actionEvent) {

        AppUser user = new AppUser();
        user.setId(userId);
        user.setLoginId(loginIdField.getText().trim());
        user.setName(nameField.getText().trim());
        user.setSurname(surnameField.getText().trim());
        user.setRole(Role.valueOf(roleBox.getValue()));
        user.setPassword(passwordField.getText().trim());
        user.setPesel(peselField.getText().trim());
        user.setBirthDate(birthDateField.getValue());

        userService.updateUser(user);

    }
}
