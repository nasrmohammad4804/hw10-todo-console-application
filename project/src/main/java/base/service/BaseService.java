package base.service;

import base.domain.BaseEntity;

import java.io.Serializable;

public interface BaseService<E extends BaseEntity<ID>, ID extends Serializable> {

    E save(E element);

    E update(E element);

    E findByID(ID id);

    E findByUserName(String userName);
}