package pl.dudios.librarymanager.book.service;

import pl.dudios.librarymanager.book.model.Book;
import pl.dudios.librarymanager.book.model.fx.UserBookFX;
import pl.dudios.librarymanager.book.rentals.model.Rental;
import pl.dudios.librarymanager.login.service.LoginService;
import pl.dudios.librarymanager.login.user.model.AppUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static pl.dudios.librarymanager.common.AppAlert.errorAlert;
import static pl.dudios.librarymanager.common.AppAlert.successAlert;

public class BookService {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public List<Book> getAllBooks() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<Book> books = em.createQuery("SELECT b FROM Book b", Book.class).getResultList();

        em.getTransaction().commit();
        em.close();

        return books;
    }

    public boolean saveBook(Book book) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        if (!validateBook(book))
            return false;

        em.persist(book);

        em.getTransaction().commit();
        em.close();

        successAlert("Książka została dodana do biblioteki");
        return true;
    }

    public void deleteBookById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Book book = em.find(Book.class, id);
        em.remove(book);

        em.getTransaction().commit();
        em.close();

        successAlert("Książka została usunięta z biblioteki");
    }

    public void updateBook(Book book) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        if (!validateBook(book))
            return;

        em.merge(book);

        em.getTransaction().commit();
        em.close();

        successAlert("Książka została zaktualizowana");
    }

    private boolean validateBook(Book book) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        if (book.getTitle().isEmpty() || book.getAuthor().isEmpty() || book.getType() == null || book.getQuantity() < 0 || book.getPublicationDate() == null) {
            errorAlert("Wszystkie pola muszą być wypełnione poprawnie");
            em.getTransaction().commit();
            em.close();
            return false;
        }
        try {
            Book singleResult = em.createQuery("SELECT b FROM Book b WHERE b.title = :title AND b.author = :author", Book.class)
                    .setParameter("title", book.getTitle())
                    .setParameter("author", book.getAuthor())
                    .getSingleResult();

            if (Objects.equals(singleResult.getId(), book.getId()))
                throw new NoResultException();

            errorAlert("Książka o podanym tytule i autorze już istnieje");
            em.getTransaction().commit();
            em.close();
            return false;
        } catch (NoResultException e) {

            em.getTransaction().commit();
            em.close();
            return true;
        }


    }


    public boolean borrowBook(Long bookId, Integer quantity, LocalDate dueDate) {

        if (quantity < 1 || dueDate == null || dueDate.isBefore(LocalDate.now())) {
            errorAlert("Wszystkie pola muszą być wypełnione poprawnie");
            return false;
        }

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        AppUser user = em.find(AppUser.class, LoginService.getUserLogged().getId());

        Book book = em.find(Book.class, bookId);
        if (book.getQuantity() < quantity) {
            errorAlert("Nie ma tyle książek w bibliotece");
            em.getTransaction().commit();
            em.close();
            return false;
        }

        book.setQuantity(book.getQuantity() - quantity);
        em.merge(book);

        Rental rental = new Rental();
        rental.setBook(book);
        rental.setUser(user);
        rental.setQuantity(quantity);
        rental.setDueDate(dueDate);
        rental.setRentalDate(LocalDate.now());
        em.persist(rental);


        List<Rental> rentals = Optional.ofNullable(user.getRentals()).orElse(new ArrayList<>());
        rentals.add(rental);
        user.setRentals(rentals);

        em.merge(user);


        em.getTransaction().commit();
        em.close();
        return true;
    }

    public List<UserBookFX> getAllBooksByUserId() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        AppUser user = em.find(AppUser.class, LoginService.getUserLogged().getId());

        return user.getRentals().stream()
                .filter(rental -> rental.getReturnDate() == null)
                .map(rental -> new UserBookFX(rental.getBook(), rental))
                .collect(Collectors.toList());

    }

    public void returnBook(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        AppUser user = em.find(AppUser.class, LoginService.getUserLogged().getId());
        List<Rental> rentals = user.getRentals();
        Rental rental = rentals.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Nie znaleziono wypożyczenia"));
        rental.setReturnDate(LocalDate.now());
        rentals.remove(rental);

        user.setRentals(rentals);
        em.merge(user);

        Book book = rental.getBook();
        book.setQuantity(em.find(Book.class, book.getId()).getQuantity() + rental.getQuantity());
        em.merge(book);


        em.getTransaction().commit();
        em.close();
    }

    public List<UserBookFX> getAllHistoryBooksByUserId() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        AppUser user = em.find(AppUser.class, LoginService.getUserLogged().getId());

        return user.getRentals().stream()
                .filter(rental -> rental.getReturnDate() != null)
                .map(rental -> new UserBookFX(rental.getBook(), rental))
                .collect(Collectors.toList());
    }
}
