package pl.dudios.librarymanager.login.user.service;

import org.hibernate.Hibernate;
import org.mindrot.jbcrypt.BCrypt;
import pl.dudios.librarymanager.login.user.model.AppUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static pl.dudios.librarymanager.common.AppAlert.errorAlert;
import static pl.dudios.librarymanager.common.AppAlert.successAlert;


public class UserService {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public List<AppUser> getAllUsers() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<AppUser> allUsers = em.createQuery("SELECT u FROM AppUser u", AppUser.class).getResultList();
        allUsers.forEach(e -> {
            Hibernate.initialize(e.getRentals());
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

        successAlert("Usunięto użytkownika");
    }

    public boolean saveUser(AppUser user) {
        if (!validateNewUser(user)) {
            return false;
        }
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        user.setJoinDate(LocalDate.now());
        em.persist(user);

        em.getTransaction().commit();
        em.close();

        successAlert("Dodano użytkownika");
        return true;
    }

    public void updateUser(AppUser user) {
        if (!validateNewUser(user)) {
            return;
        }
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        user.setJoinDate(em.find(AppUser.class, user.getId()).getJoinDate());
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        em.merge(user);

        em.getTransaction().commit();
        em.close();

        successAlert("Zaktualizowano użytkownika");
    }

    private boolean validateNewUser(AppUser user) {
        try {
            if (!user.getLoginId().isEmpty() && !user.getName().isEmpty() && !user.getSurname().isEmpty() && !user.getPassword().isEmpty() && !user.getPesel().isEmpty() && user.getBirthDate() != null) {
                EntityManager em = entityManagerFactory.createEntityManager();
                em.getTransaction().begin();

                try {
                    AppUser singleResult = em.createQuery("SELECT u FROM AppUser u WHERE u.loginId = :loginId", AppUser.class)
                            .setParameter("loginId", user.getLoginId())
                            .getSingleResult();

                    if (Objects.equals(singleResult.getId(), user.getId()))
                        throw new NoResultException();

                    errorAlert("Użytkownik o podanym loginie: " + user.getLoginId() + " już istnieje");
                    return false;

                } catch (NoResultException e) {
                    if (!validatePesel(user.getPesel())) {
                        errorAlert("Niepoprawny numer PESEL");
                        return false;
                    }
                    return true;
                } catch (Exception e) {
                    errorAlert("Coś poszło nie tak!");
                    return false;
                }

            }
            errorAlert("Wypełnij wszystkie pola poprawnie");
            return false;
        } catch (Exception e) {
            errorAlert("Coś poszło nie tak!");
            return false;
        }
    }


    private boolean validatePesel(String pesel) {
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
}
