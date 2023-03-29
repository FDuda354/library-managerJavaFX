package pl.dudios.librarymanager.login.user.model;

import pl.dudios.librarymanager.book.model.Book;
import pl.dudios.librarymanager.book.model.OverdueFee;

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

    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<OverdueFee> overdueFees;
    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Book> borrowedBooks;

    public AppUser() {
    }

    public AppUser(Long id, String loginId, String name, String surname, Role role, String password, String pesel, LocalDate birthDate, LocalDate joinDate, List<OverdueFee> overdueFees, List<Book> borrowedBooks) {
        this.id = id;
        this.loginId = loginId;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.password = password;
        this.pesel = pesel;
        this.birthDate = birthDate;
        this.joinDate = joinDate;
        this.overdueFees = overdueFees;
        this.borrowedBooks = borrowedBooks;
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

    public List<OverdueFee> getOverdueFees() {
        return overdueFees;
    }

    public void setOverdueFees(List<OverdueFee> overdueFees) {
        this.overdueFees = overdueFees;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
