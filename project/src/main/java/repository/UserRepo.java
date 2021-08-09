package repository;

import base.impl.BaseRepositoryImpl;
import domain.User;

import java.util.List;

public class UserRepo extends BaseRepositoryImpl<User> {


    public List<User> getAll() {

        return session.createQuery("from User ",User.class).getResultList();
    }
}
