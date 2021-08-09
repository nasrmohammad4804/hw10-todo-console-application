package service.impl;

import base.BaseEntity;
import base.BaseRepository;
import service.BaseService;

public abstract class BaseServiceImpl<E extends BaseEntity,R extends BaseRepository<E>> implements BaseService<E> {

    protected final R repository;

    protected BaseServiceImpl(R repository) {
        this.repository = repository;
    }


    @Override
    public E add(E element) {
        return null;
    }
}
