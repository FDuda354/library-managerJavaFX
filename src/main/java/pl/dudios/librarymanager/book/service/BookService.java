package pl.dudios.librarymanager.book.service;

import pl.dudios.librarymanager.book.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Objects;

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

        if(!validateBook(book))
            return;

        em.merge(book);

        em.getTransaction().commit();
        em.close();

        successAlert("Książka została zaktualizowana");
    }
    private boolean validateBook(Book book) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        if (book.getTitle().isEmpty() || book.getAuthor().isEmpty() || book.getType() == null || book.getQuantity() < 1 || book.getPublicationDate() == null) {
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

            if(Objects.equals(singleResult.getId(), book.getId()))
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
