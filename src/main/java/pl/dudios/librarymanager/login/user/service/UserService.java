package pl.dudios.librarymanager.login.user.service;

import javafx.scene.control.Alert;
import org.hibernate.Hibernate;
import pl.dudios.librarymanager.login.user.model.AppUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


public class UserService {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public List<AppUser> getAllUsers() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<AppUser> allUsers = em.createQuery("SELECT u FROM AppUser u", AppUser.class).getResultList();
        allUsers.forEach(e -> {
            Hibernate.initialize(e.getBorrowedBooks());
            Hibernate.initialize(e.getOverdueFees());
        });

        em.getTransaction().commit();
        em.close();

        return allUsers;
    }


    public void deleteUserById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        AppUser user = em.find(AppUser.class, id);
        em.remove(user);

        em.getTransaction().commit();
        em.close();
    }

    public static void peselAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText(null);
        alert.setContentText("Niepoprawny numer PESEL");
        alert.showAndWait();
    }

    public static void userAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText("dsadsadasdasdasdasd");
        alert.setContentText("Niepoprawne dane");
        alert.showAndWait();
    }

    public static void alertUserSucces() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Dodano Użytkownika");
        alert.setHeaderText(null);
        alert.setContentText("Użytkownik został dodany do bazy");
        alert.showAndWait();
    }

    public static boolean validatePesel(String pesel) {
        if (pesel == null || pesel.length() != 11) {
            return false;
        }
        int[] weights = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3, 1};
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            char c = pesel.charAt(i);
            if (!Character.isDigit(c)) {
                return false;
            }
            int digit = Character.getNumericValue(c);
            sum += digit * weights[i];
        }
        return sum % 10 == 0;
    }

    public static boolean validateUser(AppUser user) {
        return !user.getLoginId().isEmpty() && !user.getName().isEmpty() && !user.getSurname().isEmpty() && !user.getPassword().isEmpty() && !user.getPesel().isEmpty() && user.getBirthDate() != null;
    }
}
