package pl.dudios.librarymanager.main.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pl.dudios.librarymanager.login.user.model.AppUser;
import pl.dudios.librarymanager.login.user.model.fx.AppUserFX;
import pl.dudios.librarymanager.login.user.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AdminUserViewController {

    private UserService userService;
    public TableView<AppUserFX> contentTable;
    public TableColumn<AppUserFX, Number> idColumn;
    public TableColumn<AppUserFX, String> loginIdColumn;
    public TableColumn<AppUserFX, String> firstNameColumn;
    public TableColumn<AppUserFX, String> lastNameColumn;
    public TableColumn<AppUserFX, String> peselColumn;
    public TableColumn<AppUserFX, LocalDate> birthDateColumn;
    public TableColumn<AppUserFX, LocalDate> joinDateColumn;
    public TableColumn<AppUserFX, Void> editColumn;
    public TableColumn<AppUserFX, Void> deleteColumn;
    private final ObservableList<AppUserFX> users = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        userService = new UserService();
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        loginIdColumn.setCellValueFactory(cellData -> cellData.getValue().loginIdProperty());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        peselColumn.setCellValueFactory(cellData -> cellData.getValue().peselProperty());
        birthDateColumn.setCellValueFactory(cellData -> cellData.getValue().birthDateProperty());
        joinDateColumn.setCellValueFactory(cellData -> cellData.getValue().joinDateProperty());

        editColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Edytuj");

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    editButton.setOnAction(event -> {
                        AppUserFX user = getTableView().getItems().get(getIndex());
                        // Kod do edycji książki
                    });
                    setGraphic(editButton);
                }
            }
        });
        deleteColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Usuń");

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    deleteButton.setOnAction(event -> {
                        AppUserFX user = getTableView().getItems().get(getIndex());
                        userService.deleteUserById(user.getId());
                        users.remove(user);
                        contentTable.refresh();
                    });
                    setGraphic(deleteButton);
                }
            }
        });

        loadData();

    }

    private void loadData() {
        users.clear();
        List<AppUser> userList = userService.getAllUsers();
        users.setAll(userList.stream().map(AppUserFX::new).collect(Collectors.toList()));
        contentTable.setItems(users);
    }
}
