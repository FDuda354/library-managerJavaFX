package pl.dudios.librarymanager.book.service;

import pl.dudios.librarymanager.book.model.Book;
import pl.dudios.librarymanager.book.model.fx.UserBookFX;
import pl.dudios.librarymanager.book.rentals.model.Rental;
import pl.dudios.librarymanager.login.service.LoginService;
import pl.dudios.librarymanager.login.user.model.AppUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
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

        List<Book> books = em.createQuery("SELECT b FROM Book b ORDER BY b.id", Book.class).getResultList();

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

        successAlert("Książka" + book.getTitle() + " została dodana do biblioteki");
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

        Book bookToUpdate = em.find(Book.class, book.getId(), LockModeType.PESSIMISTIC_WRITE); // zastosowanie pesymistycznej blokady

        if (bookToUpdate == null) {
            em.getTransaction().rollback();
            errorAlert("Zmiana danych książki jest niemożliwa - zasób zablokowany przez innego użytkownika");
            return;
        }

        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setQuantity(book.getQuantity());
        bookToUpdate.setType(book.getType());
        bookToUpdate.setPublicationDate(book.getPublicationDate());

        em.getTransaction().commit();
        em.close();

        successAlert("Książka została zaktualizowana");
    }


    public boolean borrowBook(Long bookId, Integer quantity, LocalDate dueDate) {

        if (quantity < 1 || dueDate == null || dueDate.isBefore(LocalDate.now())) {
            errorAlert("Wszystkie pola muszą być wypełnione poprawnie");
            return false;
        }

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        AppUser user = em.find(AppUser.class, LoginService.getUserLogged().getId());

        Book book = em.find(Book.class, bookId, LockModeType.PESSIMISTIC_WRITE);


        if (book == null) {
            em.getTransaction().rollback(); // wycofanie transakcji w przypadku pesymistycznego blokowania zasobu przez innego użytkownika
            errorAlert("Wypożyczenie książki jest niemożliwe - zasób zablokowany przez innego użytkownika");
            return false;
        }

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


    public void returnBook(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        AppUser user = em.find(AppUser.class, LoginService.getUserLogged().getId());
        List<Rental> rentals = user.getRentals();
        Rental rental = rentals.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Didn't find rental with id: " + id));

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

    public List<UserBookFX> getAllBooksByUserId() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        AppUser user = em.find(AppUser.class, LoginService.getUserLogged().getId());

        List<UserBookFX> userBookFXList = user.getRentals().stream()
                .filter(rental -> rental.getReturnDate() == null)
                .map(rental -> new UserBookFX(rental.getBook(), rental))
                .sorted(Comparator.comparing(UserBookFX::getDueDate)).sorted((o1, o2) -> {
                    if (o1.getDueDate().isBefore(o2.getDueDate()))
                        return -1;
                    else if (o1.getDueDate().isAfter(o2.getDueDate()))
                        return 1;
                    else
                        return 0;
                }).collect(Collectors.toList());

        em.getTransaction().commit();
        em.close();
        return userBookFXList;

    }

    public List<UserBookFX> getAllHistoryBooksByUserId() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        AppUser user = em.find(AppUser.class, LoginService.getUserLogged().getId());

        List<UserBookFX> userBookFXList = user.getRentals().stream()
                .filter(rental -> rental.getReturnDate() != null)
                .map(rental -> new UserBookFX(rental.getBook(), rental))
                .sorted(Comparator.comparing(UserBookFX::getReturnDate).reversed()).sorted((o1, o2) -> {
                    if (o1.getReturnDate().isBefore(o2.getReturnDate()))
                        return -1;
                    else if (o1.getReturnDate().isAfter(o2.getReturnDate()))
                        return 1;
                    else
                        return 0;
                }).collect(Collectors.toList());

        em.getTransaction().commit();
        em.close();
        return userBookFXList;
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
}
