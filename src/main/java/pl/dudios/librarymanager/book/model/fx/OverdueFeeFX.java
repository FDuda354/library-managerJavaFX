package pl.dudios.librarymanager.book.model.fx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import pl.dudios.librarymanager.book.model.OverdueFee;
import pl.dudios.librarymanager.book.rentals.model.fx.RentalFX;
import pl.dudios.librarymanager.login.user.model.fx.AppUserFX;

import java.time.LocalDateTime;

public class OverdueFeeFX {

    private final Long id;
    private final IntegerProperty daysOverdue = new SimpleIntegerProperty();
    private final IntegerProperty fee = new SimpleIntegerProperty();
    private final ObjectProperty<RentalFX> rental = new SimpleObjectProperty<>();
    private final ObjectProperty<AppUserFX> user = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDateTime> dueDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDateTime> returnDate = new SimpleObjectProperty<>();

    public OverdueFeeFX(OverdueFee overdueFee) {
        this.id = overdueFee.getId();
        setDaysOverdue(overdueFee.getDaysOverdue());
        setFee(overdueFee.getFee().intValue());
        setRental(new RentalFX(overdueFee.getRental()));
        setUser(new AppUserFX(overdueFee.getUser()));
        setDueDate(overdueFee.getDueDate());
        setReturnDate(overdueFee.getReturnDate());
    }

    public Long getId() {
        return id;
    }

    public int getDaysOverdue() {
        return daysOverdue.get();
    }

    public IntegerProperty daysOverdueProperty() {
        return daysOverdue;
    }

    public void setDaysOverdue(int daysOverdue) {
        this.daysOverdue.set(daysOverdue);
    }

    public int getFee() {
        return fee.get();
    }

    public IntegerProperty feeProperty() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee.set(fee);
    }


    public AppUserFX getUser() {
        return user.get();
    }

    public ObjectProperty<AppUserFX> userProperty() {
        return user;
    }

    public void setUser(AppUserFX user) {
        this.user.set(user);
    }

    public LocalDateTime getDueDate() {
        return dueDate.get();
    }

    public ObjectProperty<LocalDateTime> dueDateProperty() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate.set(dueDate);
    }

    public LocalDateTime getReturnDate() {
        return returnDate.get();
    }

    public ObjectProperty<LocalDateTime> returnDateProperty() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate.set(returnDate);
    }

    public RentalFX getRental() {
        return rental.get();
    }

    public ObjectProperty<RentalFX> rentalProperty() {
        return rental;
    }

    public void setRental(RentalFX rental) {
        this.rental.set(rental);
    }
}
