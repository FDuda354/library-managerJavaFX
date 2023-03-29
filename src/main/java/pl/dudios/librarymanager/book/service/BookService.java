package pl.dudios.librarymanager.book.service;

import pl.dudios.librarymanager.book.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

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

}
