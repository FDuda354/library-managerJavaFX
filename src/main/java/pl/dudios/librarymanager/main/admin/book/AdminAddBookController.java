package pl.dudios.librarymanager.main.admin.book;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import pl.dudios.librarymanager.book.model.Book;
import pl.dudios.librarymanager.book.model.BookType;
import pl.dudios.librarymanager.book.service.BookService;

import java.time.LocalDate;

public class AdminAddBookController {

    private BookService bookService;
    public TextField titleField;
    public TextField authorField;
    public DatePicker publicationDateField;
    public Spinner quantitySpinner;
    public Label loginErrorLabel;
    @FXML
    private ChoiceBox<String> typeBox;

    @FXML
    public void initialize() {
        bookService = new BookService();
        typeBox.getItems().addAll(BookType.getValueList());
    }

    @FXML
    public void addBook() {
        Book book = new Book();
        book.setTitle(titleField.getText().trim());
        book.setAuthor(authorField.getText().trim());
        book.setType(getType(typeBox.getValue()));
        book.setPublicationDate(publicationDateField.getValue());
        book.setQuantity(Integer.valueOf(String.valueOf(quantitySpinner.getValue())));

        if (bookService.saveBook(book))
            clearFields();
    }

    private void clearFields() {
        titleField.setText("");
        authorField.setText("");
        typeBox.getSelectionModel().clearSelection();
        publicationDateField.setValue(null);
        quantitySpinner.getValueFactory().setValue(1);
    }

    private BookType getType(String value) {
        for (BookType type : BookType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
