package pl.dudios.librarymanager.book.rentals.model.fx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import pl.dudios.librarymanager.book.rentals.model.Rental;

import java.time.LocalDate;


public class RentalFX {

    private Long id;
    private ObjectProperty<LocalDate> rentalDate = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> dueDate = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> returnDate = new SimpleObjectProperty<>();
    private final IntegerProperty quantity = new SimpleIntegerProperty();


    public RentalFX(Rental rental) {
        this.id = rental.getId();
        setRentalDate(rental.getRentalDate());
        setDueDate(rental.getDueDate());
        setReturnDate(rental.getReturnDate());
        setQuantity(rental.getQuantity());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

}
