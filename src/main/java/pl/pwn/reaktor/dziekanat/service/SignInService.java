package pl.pwn.reaktor.dziekanat.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.pwn.reaktor.dziekanat.model.User;
import pl.pwn.reaktor.dziekanat.utils.HibernateUtils;

import java.io.Serializable;

public class SignInService {

    public long save (User user){
        Session session = HibernateUtils.getSessionFactory()
                .openSession();

        Transaction transaction = session.beginTransaction();

        long id = (Long) session.save(user);

        transaction.commit();
        session.close();
        return id;
    }

    public void update(User user){
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        Transaction transaction = session.beginTransaction();

        session.update(user);

        transaction.commit();
        session.close();
    }
}
