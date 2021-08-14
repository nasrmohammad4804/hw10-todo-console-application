package service;

import domain.Activities;
import domain.User;
import domain.enumaration.StateOfActivity;
import org.hibernate.Session;
import repository.UserRepo;
import service.impl.BaseServiceImpl;
import util.HibernateUtil;

import java.sql.Time;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class UserService extends BaseServiceImpl<User, UserRepo> {

    private final Scanner scannerForString = new Scanner(System.in);
    private final Scanner scannerForInteger = new Scanner(System.in);

    private final ActivityService activityService;


    protected UserService(UserRepo repository, ActivityService activityService) {
        super(repository);
        this.activityService = activityService;
    }

    public User login() {

        System.out.println("enter userName");
        String userName = scannerForString.nextLine();

        System.out.println("enter password");
        String password = scannerForString.nextLine();

        List<User> list = repository.getAll();

        for (User user : list)
            if (user.getUserName().equals(userName))
                if (user.getPassword().equals(password))
                    return user;

        return null;
    }

    public User register() {

        System.out.println("enter firstName");

        String firstName = scannerForString.nextLine();

        System.out.println("enter family");

        String family = scannerForString.nextLine();

        System.out.println("enter nationalCode");

        String nationalCode = scannerForString.nextLine();

        System.out.println("enter userName");
        String userName = scannerForString.nextLine();

        System.out.println("enter password");
        String password = scannerForString.nextLine();

        AtomicReference<User> user = null;

        repository.getAll().forEach(x -> {
            if (x.getUserName().equals(userName) && x.getPassword().equals(password)) {
                assert false;
                user.set(x);
            }
        });

        try {
            assert false;
            if (user.get() != null)
                return null;


        } catch (Exception e) {

            User myUser = new User(firstName, family, nationalCode);
            myUser.setUserName(userName);
            myUser.setPassword(password);

            Session session = HibernateUtil.getSESSION();
            session.getTransaction().begin();
            repository.add(myUser);

            session.getTransaction().commit();
            return myUser;
        }

        return null;

    }

    public void showActivity(User user) {

        for (User us : repository.getAll())
            if (user.equals(us))
                activityService.showActivity(user.getActivities());
    }

    public void addActivity(User user) {


        System.out.println("enter your task ..");
        String task = scannerForString.nextLine();

        System.out.println("enter time ");

        Time time = Time.valueOf(scannerForString.nextLine());


        Activities activity = new Activities(task, time, StateOfActivity.OPEN);

        Session session = HibernateUtil.getSESSION();

        session.getTransaction().begin();

        user.getActivities().add(activity);

        repository.add(user);
        activityService.addActivity(activity);
        session.getTransaction().commit();

    }

}
