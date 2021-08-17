package service.util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class HibernateUtil {

    private static final EntityManager entityManager;

    static {
        entityManager= Persistence.createEntityManagerFactory("default")
                .createEntityManager();
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }
}

