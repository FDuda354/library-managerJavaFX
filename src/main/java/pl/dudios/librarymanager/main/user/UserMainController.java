package pl.dudios.librarymanager.main.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pl.dudios.librarymanager.book.model.fx.BookFX;
import pl.dudios.librarymanager.book.service.BookService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class UserMainController {

    private BookService bookService;
    public TableView<BookFX> contentTable;
    public TableColumn<BookFX, Number> idColumn;
    public TableColumn<BookFX, String> titleColumn;
    public TableColumn<BookFX, String> authorColumn;
    public TableColumn<BookFX, String> genreColumn;
    public TableColumn<BookFX, LocalDate> releaseDateColumn;
    public TableColumn<BookFX, Number> stockColumn;
    public TableColumn<BookFX, Void> borrowColumn;

    private final ObservableList<BookFX> books = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        bookService = new BookService();
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        genreColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        releaseDateColumn.setCellValueFactory(cellData -> cellData.getValue().releaseDateProperty());
        stockColumn.setCellValueFactory(cellData -> cellData.getValue().stockProperty());

        borrowColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Wypożycz");

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    editButton.setOnAction(event -> {

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pl/dudios/librarymanager/main/user/user-borrow-book-form.fxml"));
                        Scene scene = null;
                        try {
                            scene = new Scene(fxmlLoader.load(), 1200, 800);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        BorrowBookController controller = fxmlLoader.getController();
                        controller.setBook(getTableView().getItems().get(getIndex()));
                        Stage stage = (Stage) contentTable.getScene().getWindow();

                        stage.setScene(scene);
                        stage.show();


                    });
                    setGraphic(editButton);
                }
            }
        });

        loadData();

    }

    private void loadData() {
        books.clear();
        books.setAll(bookService.getAllBooks().stream().filter(book -> book.getQuantity() > 0).map(BookFX::new).collect(Collectors.toList()));
        contentTable.setItems(books);
    }

}
