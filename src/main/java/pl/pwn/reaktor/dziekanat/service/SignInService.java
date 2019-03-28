package pl.pwn.reaktor.dziekanat.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.pwn.reaktor.dziekanat.model.User;
import pl.pwn.reaktor.dziekanat.utils.HibernateUtils;

public class SignInService {

    public User signIn(String userName, String password){
        Session session = HibernateUtils.getSessionFactory()
                .openSession();

        Transaction transaction = session.beginTransaction();

        String queryString = "INSERT INTO user(id, login, password, role, active) VALUES(id, 'username', 'pass', 'ROLE_STUDENT', 1)";
        Query query = session.createQuery(queryString);
        query.setParameter("username", userName);
        query.setParameter("pass", password);
        query.setMaxResults(1);

        User registredUser = (User) query.uniqueResult();

        transaction.commit();
        session.close();
        return registredUser;
    }
}
