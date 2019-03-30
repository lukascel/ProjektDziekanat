package pl.pwn.reaktor.dziekanat.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.pwn.reaktor.dziekanat.model.User;
import pl.pwn.reaktor.dziekanat.model.dto.StudentDTO;
import pl.pwn.reaktor.dziekanat.utils.HibernateUtils;

import java.util.List;

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

    public List<User> getAllAdmin(){
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("select u from User u where u.role = 'ROLE_ADMIN'");
        List <User> admins = query.list();

        transaction.commit();
        session.close();
        return admins;
    }

    public List<StudentDTO> getAllStudent(){
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        Transaction transaction = session.beginTransaction();

        //zapytanie hql'owe!! (są tez nativequery...JPQL - Java Persistance Query Language zapytania springowe?)
        //podaję też klasę, pakiet z którego korzystam, do pobierania danych z tej klasy DTO.
        // l1 - nabijam tę klasę z bazy danych,
        // 2l - piszę dane które potrzebuję,
        // 3l - skad mam je brać.
        // rozpisanie tego zapytania bez takiego czary-mary, w materiałach od prowadzącego.
        // tak można mapować na dowolną klasę(?)

        String hql = "Select new pl.pwn.reaktor.dziekanat.model.dto.StudentDTO" +
                "(s.id, u.login, u.active, s.firstName, s.lastName, s.address.street, s.address.city)" +
                " from User u inner join u.student s where u.role = 'ROLE_STUDENT'";

        Query query = session.createQuery(hql);
        List<StudentDTO> students = query.list();

        transaction.commit();
        session.close();
        return students;
    }
}
