package pl.pwn.reaktor.dziekanat.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.util.config.ConfigurationException;
import org.hibernate.service.ServiceRegistry;
import pl.pwn.reaktor.dziekanat.model.Student;
import pl.pwn.reaktor.dziekanat.model.Survey;
import pl.pwn.reaktor.dziekanat.model.User;

public class HibernateUtils {
    private static final SessionFactory SESSION_FACTORY;

    private HibernateUtils(){
        throw new IllegalStateException("HibernateUtils is only utility class");
    }

//poniżej blok statyczny - wykonuje się jeszcze przed konstruktorem! Wystarczy ze użyjemy metodę statyczną tej klasy.
    static {
        try{
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml"); //jaki plik dostarczam

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            //dzięki addAnnotationClass mamy automatyczne mapowanie klasy na bazie danych
            //musimy dla każdej Entity dodawać nowy wpis zeby działało.
            //MetadataSources sources = new MetadataSources(serviceRegistry);
            //      .addAnnotationClass(User.class);

            MetadataSources sources = new MetadataSources(serviceRegistry)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Survey.class);

            Metadata metadata = sources.getMetadataBuilder()
                    .build();

            SESSION_FACTORY = metadata.getSessionFactoryBuilder()
                    .build();

        }catch(Exception e) {
            System.err.println("Sesion factory could not be created. \n" + e);
            throw new ExceptionInInitializerError(); //nie uruchamiaj programu w momencie wystąpienia problemu z połączeniem
        }
    }

//metody statyczne - nie muszę tworzyć obiektu żeby je wywołać.
    public static void initSessionFactory(){
        System.out.println("Initialize Hibernate session factory");
    }

    public static SessionFactory getSessionFactory(){
        return SESSION_FACTORY;
    }

    public static void destroySessionFactory(){
        System.out.println("destroy Hibernate session factory!");
        SESSION_FACTORY.close();
    }
}
