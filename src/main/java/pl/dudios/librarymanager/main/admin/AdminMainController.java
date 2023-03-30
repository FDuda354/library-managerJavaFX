package pl.dudios.librarymanager.main.admin;

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
import pl.dudios.librarymanager.book.model.Book;
import pl.dudios.librarymanager.book.model.fx.BookFX;
import pl.dudios.librarymanager.book.service.BookService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AdminMainController {

    private BookService bookService;
    public TableView<BookFX> contentTable;
    public TableColumn<BookFX, Number> idColumn;
    public TableColumn<BookFX, String> titleColumn;
    public TableColumn<BookFX, String> authorColumn;
    public TableColumn<BookFX, String> genreColumn;
    public TableColumn<BookFX, LocalDate> releaseDateColumn;
    public TableColumn<BookFX, Number> stockColumn;
    public TableColumn<BookFX, Void> editColumn;
    public TableColumn<BookFX, Void> deleteColumn;

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

        editColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Edytuj");

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    editButton.setOnAction(event -> {

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pl/dudios/librarymanager/main/admin/admin-update-book-form.fxml"));
                        Scene scene = null;
                        try {
                            scene = new Scene(fxmlLoader.load(), 1200, 800);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        AdminUpdateBookController controller = fxmlLoader.getController();
                        controller.setBook(getTableView().getItems().get(getIndex()));
                        Stage stage = (Stage) contentTable.getScene().getWindow();

                        stage.setScene(scene);
                        stage.show();


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
                        BookFX book = getTableView().getItems().get(getIndex());
                        bookService.deleteBookById(book.getId());
                        books.remove(book);
                        contentTable.refresh();
                    });
                    setGraphic(deleteButton);
                }
            }
        });

        loadData();

    }

    private void loadData() {
        books.clear();
        List<Book> bookList = bookService.getAllBooks();
        books.setAll(bookList.stream().map(BookFX::new).collect(Collectors.toList()));
        contentTable.setItems(books);
    }


}
