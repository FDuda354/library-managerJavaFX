package pl.dudios.librarymanager.book.model.fx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import pl.dudios.librarymanager.book.model.Book;
import pl.dudios.librarymanager.book.rentals.model.Rental;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class UserBookFX {

    private final Long id;
    private final Long rentalId;
    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty author = new SimpleStringProperty();
    private final IntegerProperty quantity = new SimpleIntegerProperty();
    private ObjectProperty<LocalDate> rentalDate = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> dueDate = new SimpleObjectProperty<>(); //date, when book should be returned
    private ObjectProperty<LocalDate> returnDate = new SimpleObjectProperty<>(); //date, when book was returned (null if not returned)

    public UserBookFX(Book book, Rental rental) {
        this.id = book.getId();
        this.rentalId = rental.getId();
        setTitle(book.getTitle());
        setAuthor(book.getAuthor());
        setQuantity(rental.getQuantity());
        setRentalDate(rental.getRentalDate());
        setDueDate(rental.getDueDate());
        setReturnDate(rental.getReturnDate());

    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getAuthor() {
        return author.get();
    }

    public StringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public LocalDate getRentalDate() {
        return rentalDate.get();
    }

    public ObjectProperty<LocalDate> rentalDateProperty() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate.set(rentalDate);
    }

    public LocalDate getDueDate() {
        return dueDate.get();
    }

    public ObjectProperty<LocalDate> dueDateProperty() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate.set(dueDate);
    }

    public LocalDate getReturnDate() {
        return returnDate.get();
    }

    public ObjectProperty<LocalDate> returnDateProperty() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate.set(returnDate);
    }

    public ObservableValue<Number> idProperty() {
        return new SimpleObjectProperty<>(id);
    }

    public ObservableValue<Number> daysOverdueProperty() {
        long days = Math.max(ChronoUnit.DAYS.between(dueDate.get(), LocalDate.now()), 0);
        return new SimpleObjectProperty<>(days);
    }

    public Long getRentalId() {
        return rentalId;
    }
}
