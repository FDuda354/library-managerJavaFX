package pl.dudios.librarymanager.db;

import org.mindrot.jbcrypt.BCrypt;
import pl.dudios.librarymanager.book.model.Book;
import pl.dudios.librarymanager.book.model.BookType;
import pl.dudios.librarymanager.login.user.model.AppUser;
import pl.dudios.librarymanager.login.user.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class DataBase {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    private DataBase() {

    }

    public static void fillBookDataBase() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Book book = new Book();
        book.setTitle("W pustyni i w puszczy");
        book.setAuthor("Henryk Sienkiewicz");
        book.setQuantity(10);
        book.setType(BookType.FICTION);
        book.setPublicationDate(LocalDate.of(2015, 9, 15));
        em.persist(book);

        Book book2 = new Book();
        book2.setTitle("Pan Tadeusz");
        book2.setAuthor("Adam Mickiewicz");
        book2.setQuantity(10);
        book2.setType(BookType.FICTION);
        book2.setPublicationDate(LocalDate.of(2020, 3, 24));
        em.persist(book2);

        Book book3 = new Book();
        book3.setTitle("Dziady");
        book3.setAuthor("Adam Mickiewicz");
        book3.setQuantity(10);
        book3.setType(BookType.FICTION);
        book3.setPublicationDate(LocalDate.of(2009, 7, 30));
        em.persist(book3);

        Book book4 = new Book();
        book4.setTitle("Lalka");
        book4.setAuthor("Bolesław Prus");
        book4.setQuantity(10);
        book4.setType(BookType.FICTION);
        book4.setPublicationDate(LocalDate.of(2012, 3, 20));
        em.persist(book4);

        Book book5 = new Book();
        book5.setTitle("Raport mniejszości");
        book5.setAuthor("Philip K. Dick");
        book5.setQuantity(5);
        book5.setType(BookType.NON_FICTION);
        book5.setPublicationDate(LocalDate.of(1960, 1, 1));
        em.persist(book5);

        Book book6 = new Book();
        book6.setTitle("12 Reguł życia. Praktyczny poradnik chaosu");
        book6.setAuthor("Jordan Peterson");
        book6.setQuantity(8);
        book6.setType(BookType.NON_FICTION);
        book6.setPublicationDate(LocalDate.of(2018, 1, 23));
        em.persist(book6);

        Book book7 = new Book();
        book7.setTitle("Szklany klosz");
        book7.setAuthor("Sylvia Plath");
        book7.setQuantity(3);
        book7.setType(BookType.DRAMA);
        book7.setPublicationDate(LocalDate.of(1963, 1, 14));
        em.persist(book7);

        Book book8 = new Book();
        book8.setTitle("Morderstwo w Orient Expressie");
        book8.setAuthor("Agatha Christie");
        book8.setQuantity(12);
        book8.setType(BookType.MYSTERY);
        book8.setPublicationDate(LocalDate.of(1934, 1, 1));
        em.persist(book8);

        Book book9 = new Book();
        book9.setTitle("Zabójstwo Rogera Ackroyda");
        book9.setAuthor("Agatha Christie");
        book9.setQuantity(7);
        book9.setType(BookType.MYSTERY);
        book9.setPublicationDate(LocalDate.of(1926, 1, 1));
        em.persist(book9);

        Book book10 = new Book();
        book10.setTitle("Hobbit, czyli tam i z powrotem");
        book10.setAuthor("J.R.R. Tolkien");
        book10.setQuantity(9);
        book10.setType(BookType.FANTASY);
        book10.setPublicationDate(LocalDate.of(1937, 9, 21));
        em.persist(book10);

        Book book11 = new Book();
        book11.setTitle("Podróże Guliwera");
        book11.setAuthor("Jonathan Swift");
        book11.setQuantity(6);
        book11.setType(BookType.BIOGRAPHY);
        book11.setPublicationDate(LocalDate.of(1726, 1, 1));
        em.persist(book11);

        Book book12 = new Book();
        book12.setTitle("Zbrodnia i kara");
        book12.setAuthor("Fiodor Dostojewski");
        book12.setQuantity(4);
        book12.setType(BookType.DRAMA);
        book12.setPublicationDate(LocalDate.of(1866, 1, 1));
        em.persist(book12);

        Book book13 = new Book();
        book13.setTitle("Ojciec chrzestny");
        book13.setAuthor("Mario Puzo");
        book13.setQuantity(3);
        book13.setType(BookType.DRAMA);
        book13.setPublicationDate(LocalDate.of(1969, 3, 10));
        em.persist(book13);

        Book book14 = new Book();
        book14.setTitle("Mały Książę");
        book14.setAuthor("Antoine de Saint-Exupéry");
        book14.setQuantity(8);
        book14.setType(BookType.NON_FICTION);
        book14.setPublicationDate(LocalDate.of(1943, 4, 6));
        em.persist(book14);

        Book book15 = new Book();
        book15.setTitle("Władca Pierścieni: Powrót króla");
        book15.setAuthor("J.R.R. Tolkien");
        book15.setQuantity(6);
        book15.setType(BookType.FANTASY);
        book15.setPublicationDate(LocalDate.of(1955, 10, 20));
        em.persist(book15);

        Book book16 = new Book();
        book16.setTitle("1984");
        book16.setAuthor("George Orwell");
        book16.setQuantity(5);
        book16.setType(BookType.HORROR);
        book16.setPublicationDate(LocalDate.of(1949, 6, 8));
        em.persist(book16);

        Book book17 = new Book();
        book17.setTitle("Mistrz i Małgorzata");
        book17.setAuthor("Michaił Bułhakow");
        book17.setQuantity(6);
        book17.setType(BookType.FICTION);
        book17.setPublicationDate(LocalDate.of(1966, 1, 1));
        em.persist(book17);

        Book book18 = new Book();
        book18.setTitle("Władca Pierścieni: Drużyna Pierścienia");
        book18.setAuthor("J.R.R. Tolkien");
        book18.setQuantity(7);
        book18.setType(BookType.FANTASY);
        book18.setPublicationDate(LocalDate.of(1954, 7, 29));
        em.persist(book18);

        Book book19 = new Book();
        book19.setTitle("Harry Potter i Kamień Filozoficzny");
        book19.setAuthor("J.K. Rowling");
        book19.setQuantity(9);
        book19.setType(BookType.FANTASY);
        book19.setPublicationDate(LocalDate.of(1997, 6, 26));
        em.persist(book19);

        Book book20 = new Book();
        book20.setTitle("Metro 2033");
        book20.setAuthor("Dmitry Glukhovsky");
        book20.setQuantity(4);
        book20.setType(BookType.MYSTERY);
        book20.setPublicationDate(LocalDate.of(2005, 6, 1));
        em.persist(book20);

        Book book21 = new Book();
        book21.setTitle("W pustyni i w puszczy");
        book21.setAuthor("Henryk Sienkiewicz");
        book21.setQuantity(10);
        book21.setType(BookType.FICTION);
        book21.setPublicationDate(LocalDate.of(1912, 1, 1));
        em.persist(book21);

        Book book22 = new Book();
        book22.setTitle("Wiedźmin: Ostatnie życzenie");
        book22.setAuthor("Andrzej Sapkowski");
        book22.setQuantity(6);
        book22.setType(BookType.FANTASY);
        book22.setPublicationDate(LocalDate.of(1993, 1, 1));
        em.persist(book22);

        Book book23 = new Book();
        book23.setTitle("Lśnienie");
        book23.setAuthor("Stephen King");
        book23.setQuantity(3);
        book23.setType(BookType.HORROR);
        book23.setPublicationDate(LocalDate.of(1977, 1, 28));
        em.persist(book23);

        Book book24 = new Book();
        book24.setTitle("Mężczyźni, którzy nienawidzą kobiet");
        book24.setAuthor("Stieg Larsson");
        book24.setQuantity(7);
        book24.setType(BookType.THRILLER);
        book24.setPublicationDate(LocalDate.of(2005, 1, 1));
        em.persist(book24);

        Book book25 = new Book();
        book25.setTitle("Metro 2034");
        book25.setAuthor("Dmitry Glukhovsky");
        book25.setQuantity(3);
        book25.setType(BookType.FICTION);
        book25.setPublicationDate(LocalDate.of(2009, 6, 1));
        em.persist(book25);

        Book book26 = new Book();
        book26.setTitle("Mężczyzna z blizną");
        book26.setAuthor("Ernest Hemingway");
        book26.setQuantity(5);
        book26.setType(BookType.FICTION);
        book26.setPublicationDate(LocalDate.of(1940, 1, 1));
        em.persist(book26);

        Book book27 = new Book();
        book27.setTitle("Powrót króla");
        book27.setAuthor("J.R.R. Tolkien");
        book27.setQuantity(6);
        book27.setType(BookType.FANTASY);
        book27.setPublicationDate(LocalDate.of(1955, 10, 20));
        em.persist(book27);

        Book book28 = new Book();
        book28.setTitle("Zbrodnia");
        book28.setAuthor("Marta Guzowska");
        book28.setQuantity(8);
        book28.setType(BookType.THRILLER);
        book28.setPublicationDate(LocalDate.of(2022, 1, 1));
        em.persist(book28);

        Book book29 = new Book();
        book29.setTitle("Pensjonat pod Meduzą");
        book29.setAuthor("Tatiana de Rosnay");
        book29.setQuantity(3);
        book29.setType(BookType.MYSTERY);
        book29.setPublicationDate(LocalDate.of(2019, 1, 1));
        em.persist(book29);

        Book book30 = new Book();
        book30.setTitle("Pan Lodowego Ogrodu. Tom 1");
        book30.setAuthor("Jarosław Grzędowicz");
        book30.setQuantity(4);
        book30.setType(BookType.FANTASY);
        book30.setPublicationDate(LocalDate.of(2007, 1, 1));
        em.persist(book30);

        Book book31 = new Book();
        book31.setTitle("Małżeństwo z rozsądku");
        book31.setAuthor("Jane Austen");
        book31.setQuantity(6);
        book31.setType(BookType.ROMANCE);
        book31.setPublicationDate(LocalDate.of(1813, 1, 1));
        em.persist(book31);

        Book book32 = new Book();
        book32.setTitle("Nielegalni");
        book32.setAuthor("Katarzyna Bonda");
        book32.setQuantity(7);
        book32.setType(BookType.THRILLER);
        book32.setPublicationDate(LocalDate.of(2016, 1, 1));
        em.persist(book32);

        Book book33 = new Book();
        book33.setTitle("Buntowniczka z wyboru");
        book33.setAuthor("Kristin Hannah");
        book33.setQuantity(5);
        book33.setType(BookType.FICTION);
        book33.setPublicationDate(LocalDate.of(2015, 1, 1));
        em.persist(book33);

        Book book34 = new Book();
        book34.setTitle("Pachnidło");
        book34.setAuthor("Patrick Süskind");
        book34.setQuantity(2);
        book34.setType(BookType.FICTION);
        book34.setPublicationDate(LocalDate.of(1985, 1, 1));
        em.persist(book34);

        Book book35 = new Book();
        book35.setTitle("1984");
        book35.setAuthor("George Orwell");
        book35.setQuantity(5);
        book35.setType(BookType.FANTASY);
        book35.setPublicationDate(LocalDate.of(1949, 6, 8));
        em.persist(book35);

        Book book36 = new Book();
        book36.setTitle("Który skrzywdził psa?");
        book36.setAuthor("Camilla Läckberg");
        book36.setQuantity(4);
        book36.setType(BookType.HORROR);
        book36.setPublicationDate(LocalDate.of(2022, 1, 1));
        em.persist(book36);

        Book book37 = new Book();
        book37.setTitle("Księga dżungli");
        book37.setAuthor("Rudyard Kipling");
        book37.setQuantity(6);
        book37.setType(BookType.MYSTERY);
        book37.setPublicationDate(LocalDate.of(1894, 1, 1));
        em.persist(book37);

        Book book38 = new Book();
        book38.setTitle("Pachnidło: Historia mordercy");
        book38.setAuthor("Patrick Süskind");
        book38.setQuantity(3);
        book38.setType(BookType.FICTION);
        book38.setPublicationDate(LocalDate.of(1985, 1, 1));
        em.persist(book38);

        Book book39 = new Book();
        book39.setTitle("Opowieści z Narnii: Lew, czarownica i stara szafa");
        book39.setAuthor("C.S. Lewis");
        book39.setQuantity(7);
        book39.setType(BookType.FANTASY);
        book39.setPublicationDate(LocalDate.of(1950, 1, 1));
        em.persist(book39);

        Book book40 = new Book();
        book40.setTitle("Ender's Game");
        book40.setAuthor("Orson Scott Card");
        book40.setQuantity(2);
        book40.setType(BookType.BIOGRAPHY);
        book40.setPublicationDate(LocalDate.of(1985, 1, 1));
        em.persist(book40);

        Book book41 = new Book();
        book41.setTitle("Ania z Zielonego Wzgórza");
        book41.setAuthor("Lucy Maud Montgomery");
        book41.setQuantity(8);
        book41.setType(BookType.THRILLER);
        book41.setPublicationDate(LocalDate.of(1908, 1, 1));
        em.persist(book41);

        Book book42 = new Book();
        book42.setTitle("Zmartwychwstanie");
        book42.setAuthor("Lew Tołstoj");
        book42.setQuantity(4);
        book42.setType(BookType.FICTION);
        book42.setPublicationDate(LocalDate.of(1899, 1, 1));
        em.persist(book42);

        Book book43 = new Book();
        book43.setTitle("W pierścieniu ognia");
        book43.setAuthor("Suzanne Collins");
        book43.setQuantity(6);
        book43.setType(BookType.FICTION);
        book43.setPublicationDate(LocalDate.of(2010, 1, 1));
        em.persist(book43);

        Book book44 = new Book();
        book44.setTitle("Nineteen Minutes");
        book44.setAuthor("Jodi Picoult");
        book44.setQuantity(5);
        book44.setType(BookType.FICTION);
        book44.setPublicationDate(LocalDate.of(2007, 1, 1));
        em.persist(book44);

        em.getTransaction().commit();
        em.close();
    }

    public static void fillUserDataBase() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        AppUser user1 = new AppUser();
        user1.setName("Jan");
        user1.setSurname("Kowalski");
        user1.setBirthDate(LocalDate.of(1990, 1, 1));
        user1.setPassword(BCrypt.hashpw("1234", BCrypt.gensalt()));
        user1.setJoinDate(LocalDate.now());
        user1.setRole(Role.USER);
        user1.setLoginId("w1234");
        user1.setPesel("12345678901");
        em.persist(user1);

        AppUser user2 = new AppUser();
        user2.setName("Filip");
        user2.setSurname("Duda");
        user2.setBirthDate(LocalDate.of(1999, 1, 1));
        user2.setPassword(BCrypt.hashpw("1234", BCrypt.gensalt()));
        user2.setJoinDate(LocalDate.now());
        user2.setRole(Role.ADMIN);
        user2.setLoginId("w60846");
        user2.setPesel("12345678901");
        em.persist(user2);


        em.getTransaction().commit();
        em.close();
    }
}
