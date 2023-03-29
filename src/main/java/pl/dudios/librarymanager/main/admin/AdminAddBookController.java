package pl.dudios.librarymanager.main.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import pl.dudios.librarymanager.book.model.BookType;

import java.time.LocalDate;

public class AdminAddBookController {
    public TextField titleField;
    public TextField authorField;
    public DatePicker publicationDateField;
    public Spinner quantitySpinner;
    public Label loginErrorLabel;
    @FXML
    private ChoiceBox<String> typeBox;

    @FXML
    public void initialize() {
        typeBox.getItems().addAll(BookType.getValueList());
    }

    @FXML
    public void addBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        BookType type = getType(typeBox.getValue());
        LocalDate publicationDate = publicationDateField.getValue();
        Integer quantity = Integer.valueOf(String.valueOf(quantitySpinner.getValue()));

        System.out.println(type);

        // TODO: utworzyć nowy obiekt książki z wprowadzonymi danymi i dodać go do bazy danych


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Dodano książkę");
        alert.setHeaderText(null);
        alert.setContentText("Książka została dodana do biblioteki");
        alert.showAndWait();


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
