package service.impl;

import base.service.impl.BaseServiceImpl;
import domain.Activity;
import domain.User;
import domain.enumaration.StateOfActivity;
import repository.impl.UserRepositoryImpl;
import service.UserService;
import service.util.ApplicationContext;

import java.sql.Time;

public class UserServiceImpl extends BaseServiceImpl<User, Long, UserRepositoryImpl>
        implements UserService {

    private final ActivityServiceImpl activityService;

    public UserServiceImpl(UserRepositoryImpl repository, ActivityServiceImpl activityService) {
        super(repository);
        this.activityService = activityService;
    }

    @Override
    public User login() {

        System.out.println("enter userName");

        String userName = ApplicationContext.scannerForString.nextLine();

        System.out.println("enter password");
        String password = ApplicationContext.scannerForString.nextLine();

        User user = findByUserName(userName);

        if (user == null) {
            System.out.println("this userName not exists !!!");
            return user;
        } else if (!user.getPassword().equals(password)) {
            System.out.println("password not correct !!!");
            return null;
        }
        return user;
    }

    @Override
    public User register() {

        System.out.println("enter userName");
        String userName = ApplicationContext.scannerForString.nextLine();

        User user = findByUserName(userName);

        if (user != null) {
            System.out.println("user with userName already exists ");
            return null;
        }
        System.out.println("enter password");

        String password = ApplicationContext.scannerForString.nextLine();

        System.out.println("enter name");
        String name = ApplicationContext.scannerForString.nextLine();

        System.out.println("enter family");
        String family = ApplicationContext.scannerForString.nextLine();

        System.out.println("enter nationalCode  ");
        String nationalCode = ApplicationContext.scannerForString.nextLine();

        User newUser = new User(name, family, nationalCode);
        newUser.setUserName(userName);
        newUser.setPassword(password);

        return save(newUser);
    }


    @Override
    public void showActivity(User user) {
        activityService.showActivity(user.getActivities());
    }

    @Override
    public void addActivity(User user) {

        System.out.println("enter your task ....");
        String task = ApplicationContext.scannerForString.nextLine();

        System.out.println("enter time");

        Time time = Time.valueOf(ApplicationContext.scannerForString.nextLine());

        user.getActivities().add(new Activity(task, time, StateOfActivity.OPEN));

        update(user);
    }

}
