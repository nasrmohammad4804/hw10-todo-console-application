package service;

import domain.User;
import service.impl.ActivityServiceImpl;
import service.impl.UserServiceImpl;
import service.util.ApplicationContext;

public class App {

    private UserServiceImpl userService;
    private ActivityServiceImpl activityService;

    public App() {

        userService = ApplicationContext.getUserService();
        activityService = ApplicationContext.getActivityService();
    }

    public void start() {

        System.out.println("1.register");
        System.out.println("2.login");
        System.out.println("3.exit");

        User user;
        switch (resultOfInput()) {

            case 1 -> {
                user = userService.register();

                if (user != null) {
                    messageForUserEntered(user);
                    userPanel(user);
                }

                start();
            }
            case 2 -> {
                user = userService.login();
                if (user != null) {
                    messageForUserEntered(user);
                    userPanel(user);
                }

                start();
            }

            case 3 -> {
                System.out.println("have nice day ...");
                System.exit(0);
            }
        }
    }

    private void messageForUserEntered(User user) {

        System.out.println("welcome " + user.getName() + "  " + user.getFamily() + ")))\n");
    }

    private void userPanel(User user) {

        System.out.println("1.showAllActivity");
        System.out.println("2.add activity");
        System.out.println("3.change status ");
        System.out.println("4.back");

        switch (resultOfInput()) {

            case 1:
                userService.showActivity(user);
                userPanel(user);
                break;

            case 2:
                userService.addActivity(user);
                userPanel(user);
                break;


            case 3:
                activityService.updateActivity(user);
                userPanel(user);
                break;


            case 4:
                start();
                break;

            default:
                System.out.println("your input not valid  try again ...");
                userPanel(user);
        }
    }

    public int resultOfInput() {

        int result = 0;

        try {
            result = ApplicationContext.scannerForInteger.nextInt();

        } catch (Exception e) {
            System.out.println("input not valid try again !!!");
            resultOfInput();
        }

        return result;
    }
}
