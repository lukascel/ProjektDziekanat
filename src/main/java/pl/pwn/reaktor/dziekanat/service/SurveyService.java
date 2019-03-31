package pl.pwn.reaktor.dziekanat.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.pwn.reaktor.dziekanat.model.Survey;
import pl.pwn.reaktor.dziekanat.model.User;
import pl.pwn.reaktor.dziekanat.utils.HibernateUtils;

import java.util.List;

public class SurveyService {

    public void save(Survey survey){
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        Transaction transaction = session.beginTransaction();

        session.save(survey);

        transaction.commit();
        session.close();
    }

    public List <Survey> getSurveyByStudent(int studentId){
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "select s from Survey s where studentId=:id";
        Query query = session.createQuery(sql);
        query.setParameter("id", studentId);
        List <Survey> surveys = query.list();

        transaction.commit();
        session.close();
        return surveys;
    }

    public void delete (Survey survey){
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(survey);

        transaction.commit();
        session.close();
    }

}
