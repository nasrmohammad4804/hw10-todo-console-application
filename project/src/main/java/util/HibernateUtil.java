package util;

import org.hibernate.Session;

import javax.persistence.Persistence;

public class HibernateUtil {

    private  static  final Session SESSION ;

    static {

        SESSION= Persistence.createEntityManagerFactory("default")
                .createEntityManager().unwrap(Session.class);
    }

    public static Session getSESSION() {
        return SESSION;
    }
}
