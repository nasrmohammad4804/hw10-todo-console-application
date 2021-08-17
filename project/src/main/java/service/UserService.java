package service;

import base.service.BaseService;
import domain.User;

public interface UserService extends BaseService<User,Long> {

    User login();

    User register();

    void showActivity(User user);

    void addActivity(User user);


}
