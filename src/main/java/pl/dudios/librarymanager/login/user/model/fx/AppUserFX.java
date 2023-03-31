package pl.dudios.librarymanager.login.user.model.fx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.dudios.librarymanager.book.rentals.model.Rental;
import pl.dudios.librarymanager.login.user.model.AppUser;
import pl.dudios.librarymanager.login.user.model.Role;

import java.time.LocalDate;

public class AppUserFX {
    private final Long id;
    private final StringProperty loginId = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty surname = new SimpleStringProperty();
    private final ObjectProperty<Role> role = new SimpleObjectProperty<>();
    private final StringProperty password = new SimpleStringProperty();
    private final StringProperty pesel = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> birthDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> joinDate = new SimpleObjectProperty<>();
    private final ObservableList<Rental> rentals = FXCollections.observableArrayList();


    public AppUserFX(AppUser user) {
        this.id = user.getId();
        setLoginId(user.getLoginId());
        setName(user.getName());
        setSurname(user.getSurname());
        setRole(user.getRole());
        setPassword(user.getPassword());
        setPesel(user.getPesel());
        setBirthDate(user.getBirthDate());
        setJoinDate(user.getJoinDate());
        user.setRentals(user.getRentals());
    }

    public Long getId() {
        return id;
    }

    public String getLoginId() {
        return loginId.get();
    }

    public StringProperty loginIdProperty() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId.set(loginId);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public Role getRole() {
        return role.get();
    }

    public ObjectProperty<Role> roleProperty() {
        return role;
    }

    public void setRole(Role role) {
        this.role.set(role);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getPesel() {
        return pesel.get();
    }

    public StringProperty peselProperty() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel.set(pesel);
    }

    public LocalDate getBirthDate() {
        return birthDate.get();
    }

    public ObjectProperty<LocalDate> birthDateProperty() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate.set(birthDate);
    }

    public LocalDate getJoinDate() {
        return joinDate.get();
    }

    public ObjectProperty<LocalDate> joinDateProperty() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate.set(joinDate);
    }

    public ObservableValue<String> firstNameProperty() {
        return name;
    }

    public ObservableValue<Number> idProperty() {
        return new SimpleObjectProperty<>(id);
    }

    public ObservableValue<String> lastNameProperty() {
        return surname;
    }

    public ObservableList<Rental> getRentals() {
        return rentals;
    }
}
