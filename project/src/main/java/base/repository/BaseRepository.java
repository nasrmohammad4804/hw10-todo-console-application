package base.repository;

import base.domain.BaseEntity;

import java.io.Serializable;

public interface BaseRepository<E extends BaseEntity<ID> , ID extends Serializable> {

    E save(E element);

    E update(E element);

    E findByID(ID id);

    E findByUserName(String userName);


}

