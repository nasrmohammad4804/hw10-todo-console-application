package base.repository.impl;

import base.domain.BaseEntity;
import base.repository.BaseRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

public abstract class BaseRepositoryImpl<E extends BaseEntity<ID>, ID extends Serializable>
        implements BaseRepository<E, ID> {

    protected final EntityManager entityManager;

    public abstract Class<E> getEntityClass();

    protected BaseRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public E save(E element) {
        entityManager.persist(element);

        return element;
    }

    @Override
    public E findByID(ID id) {
        return entityManager.find(getEntityClass(), id);
    }

    @Override
    public E findByUserName(String userName) {

        try {

            return entityManager.createQuery("select u from " + getEntityClass().getSimpleName() + " as u " +
                    "where u.userName=:myUserName", getEntityClass())
                    .setParameter("myUserName", userName).getSingleResult();

        }catch (Exception e){
            return null;
        }
    }

    @Override
    public E update(E element) {

        return entityManager.merge(element);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}

