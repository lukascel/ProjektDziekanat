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

        String queryString = "INSERT INTO user VALUES(1, 'login', 'password', 'ROLE_STUDENT')";
        Query query = session.createQuery(queryString);
        query.setParameter("login", userName);
        query.setParameter("password", password);
        User registredUser = (User) query.uniqueResult();

        transaction.commit();
        session.close();
        return registredUser;
    }
}
