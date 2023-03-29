package pl.dudios.librarymanager.login.service;

import org.mindrot.jbcrypt.BCrypt;
import pl.dudios.librarymanager.login.user.model.AppUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


public class LoginService {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public AppUser validateLogin(String loginId, String password) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<AppUser> query = em.createQuery("SELECT u FROM AppUser u WHERE u.loginId = :loginId", AppUser.class);
        query.setParameter("loginId", loginId);

        AppUser user;
        try {
            user = query.getSingleResult();
          if(!BCrypt.checkpw(password, user.getPassword()))
                throw new NoResultException();

        } catch (NoResultException e) {
            return null;
        }

        em.getTransaction().commit();
        em.close();

        return user;
    }

}
