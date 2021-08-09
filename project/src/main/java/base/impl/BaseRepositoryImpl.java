package base.impl;

import base.BaseEntity;
import base.BaseRepository;
import org.hibernate.Session;
import util.HibernateUtil;

public abstract class BaseRepositoryImpl<E extends BaseEntity> extends BaseEntity implements BaseRepository<E> {

    protected  Session session=HibernateUtil.getSESSION();

    @Override
    public E add(E element) {

        session.saveOrUpdate(element);
        return element;
    }
}
