package pl.pwn.reaktor.dziekanat.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pl.pwn.reaktor.dziekanat.model.Student;
import pl.pwn.reaktor.dziekanat.utils.HibernateUtils;

public class StudentService {
    public void save(Student student){
        Session session = HibernateUtils.getSessionFactory()
                .openSession();

        Transaction transaction = session.beginTransaction();

        session.save(student);
        transaction.commit();
        session.close();
    }

    public void update(Student student) {
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        Transaction transaction = session.beginTransaction();

        session.update(student);

        transaction.commit();
        session.close();
    }
}
