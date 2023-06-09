package pl.dudios.librarymanager.main.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pl.dudios.librarymanager.book.model.fx.UserBookFX;
import pl.dudios.librarymanager.book.service.BookService;

import java.time.LocalDate;

public class UserBookController {

    private BookService bookService;
    public TableView<UserBookFX> booksTable;
    public TableColumn<UserBookFX, Number> quantityColumn;
    public TableColumn<UserBookFX, LocalDate> dueDateColumn; //data, kiedy książka musi być zwrócona,
    public TableColumn<UserBookFX, Number> daysOverdueColumn;
    public TableColumn<UserBookFX, Number> idColumn;
    public TableColumn<UserBookFX, String> titleColumn;
    public TableColumn<UserBookFX, String> authorColumn;
    public TableColumn<UserBookFX, Void> returnColumn;

    private final ObservableList<UserBookFX> books = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        bookService = new BookService();
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty());
        dueDateColumn.setCellValueFactory(cellData -> cellData.getValue().dueDateProperty());
        daysOverdueColumn.setCellValueFactory(cellData -> cellData.getValue().daysOverdueProperty());
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        returnColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Oddaj");

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    editButton.setOnAction(event -> {
                        UserBookFX book = getTableView().getItems().get(getIndex());
                        bookService.returnBook(book.getRentalId());
                        loadData();


                    });
                    setGraphic(editButton);
                }
            }
        });

        loadData();

    }

    private void loadData() {
        books.clear();
        books.setAll(bookService.getAllBooksByUserId());
        booksTable.setItems(books);
    }
}
