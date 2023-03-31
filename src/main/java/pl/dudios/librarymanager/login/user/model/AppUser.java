package pl.dudios.librarymanager.login.user.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.dudios.librarymanager.book.rentals.model.Rental;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loginId;
    private String name;
    private String surname;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String password;
    private String pesel;
    private LocalDate birthDate;
    private LocalDate joinDate;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Rental> rentals;

    public AppUser() {
    }

    public AppUser(Long id, String loginId, String name, String surname, Role role, String password, String pesel, LocalDate birthDate, LocalDate joinDate,  List<Rental> rentals) {
        this.id = id;
        this.loginId = loginId;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.password = password;
        this.pesel = pesel;
        this.birthDate = birthDate;
        this.joinDate = joinDate;
        this.rentals = rentals;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }
//tODO: to delete
//    public ObservableList<OverdueFeeFX> getOverdueFees() {
//        ObservableList<OverdueFeeFX> overdueFeesFXList = FXCollections.observableArrayList();
//
//        overdueFees.forEach(overdueFee -> overdueFeesFXList.add(new OverdueFeeFX(overdueFee)));
//
//        return overdueFeesFXList;
//    }
//
//    public void setOverdueFees(List<OverdueFee> overdueFees) {
//        this.overdueFees = overdueFees;
//    }


    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
}
