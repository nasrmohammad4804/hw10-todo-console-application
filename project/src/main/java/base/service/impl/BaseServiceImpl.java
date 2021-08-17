package base.service.impl;

import base.domain.BaseEntity;
import base.repository.impl.BaseRepositoryImpl;
import base.service.BaseService;

import javax.persistence.EntityManager;
import java.io.Serializable;

public abstract class BaseServiceImpl<E extends BaseEntity<ID>, ID extends Serializable,
        Repository extends BaseRepositoryImpl<E, ID>> implements BaseService<E, ID> {

    protected final Repository repository;

    protected final EntityManager entityManager;

    protected BaseServiceImpl(Repository repository) {
        this.repository = repository;
        entityManager = repository.getEntityManager();
    }

    @Override
    public E save(E element) {
        entityManager.getTransaction().begin();
        repository.save(element);
        entityManager.getTransaction().commit();

        return element;
    }

    @Override
    public E update(E element) {
        entityManager.getTransaction().begin();

        E entity = repository.update(element);
        entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public E findByID(ID id) {
        return repository.findByID(id);
    }

    @Override
    public E findByUserName(String userName) {

        return repository.findByUserName(userName);
    }

    public EntityManager entityManager() {
        return entityManager;
    }
}

