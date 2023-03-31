package pl.dudios.librarymanager.main.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import pl.dudios.librarymanager.book.model.fx.BookFX;
import pl.dudios.librarymanager.book.service.BookService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static pl.dudios.librarymanager.common.AppAlert.successAlert;

public class BorrowBookController {

    public DatePicker returnDateField;
    public Label TitleField;
    private BookService bookService;
    public Long bookId;
    public Spinner quantitySpinner;

    @FXML
    public void initialize() {
        bookService = new BookService();
    }


    public void setBook(BookFX book) {
        TitleField.setText(book.getTitle());
        bookId = book.getId();
        quantitySpinner.getValueFactory().setValue(1);

    }

    public void borrowBook(ActionEvent actionEvent) {
        LocalDate dueDate = returnDateField.getValue();
        Integer quantity = (Integer) quantitySpinner.getValue();
        long days = ChronoUnit.DAYS.between(LocalDate.now(), dueDate);
        if (bookService.borrowBook(bookId, quantity, dueDate))
            successAlert("Wypożyczono książkę " + TitleField.getText() + " na " + days + " dni");
    }
}
