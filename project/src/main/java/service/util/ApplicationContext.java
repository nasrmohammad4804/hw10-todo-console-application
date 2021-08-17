package service.util;

import repository.impl.ActivityRepositoryImpl;
import repository.impl.UserRepositoryImpl;
import service.impl.ActivityServiceImpl;
import service.impl.UserServiceImpl;

import java.util.Scanner;

public class ApplicationContext {

    public static final Scanner scannerForString=new Scanner(System.in);
    public static final Scanner scannerForInteger=new Scanner(System.in);

    private static final UserServiceImpl userService;
    private static final ActivityServiceImpl activityService;

    static {
        activityService=new ActivityServiceImpl(new ActivityRepositoryImpl(HibernateUtil.getEntityManager()));
        userService=new UserServiceImpl(new UserRepositoryImpl(HibernateUtil.getEntityManager()), activityService);
    }

    public static UserServiceImpl getUserService() {
        return userService;
    }

    public static ActivityServiceImpl getActivityService() {
        return activityService;
    }
}
