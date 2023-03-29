package pl.dudios.librarymanager.book.model.fx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import pl.dudios.librarymanager.book.model.Book;
import pl.dudios.librarymanager.book.model.BookType;

import java.time.LocalDate;

public class BookFX {
    private final Long id;
    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty author = new SimpleStringProperty();
    private final IntegerProperty quantity = new SimpleIntegerProperty();
    private final ObjectProperty<BookType> type = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> publicationDate = new SimpleObjectProperty<>();

    public BookFX(Book book) {
        this.id = book.getId();
        setTitle(book.getTitle());
        setAuthor(book.getAuthor());
        setQuantity(book.getQuantity());
        setType(book.getType());
        setPublicationDate(book.getPublicationDate());
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

    public BookType getType() {
        return type.get();
    }

    public ObjectProperty<String> typeProperty() {
        return new SimpleObjectProperty<>(type.getValue().getValue());
    }

    public void setType(BookType type) {
        this.type.set(type);
    }

    public LocalDate getPublicationDate() {
        return publicationDate.get();
    }

    public ObjectProperty<LocalDate> publicationDateProperty() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate.set(publicationDate);
    }

    public ObservableValue<LocalDate> releaseDateProperty() {
        return publicationDate;
    }

    public ObservableValue<Number> stockProperty() {
        return quantity;
    }

    public ObservableValue<Number> idProperty() {
        return new SimpleObjectProperty<>(id);
    }
}
