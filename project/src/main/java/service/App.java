package service;

import domain.User;
import repository.ActivityRepo;
import repository.UserRepo;

import java.util.Scanner;

public class App {

    UserService userService;
    ActivityService activityService;

    public App() {
        activityService = new ActivityService(new ActivityRepo());
        userService = new UserService(new UserRepo(), activityService);
    }

    public void start() throws CloneNotSupportedException {

        System.out.println("1.register");
        System.out.println("2.login");
        System.out.println("3.exit");

        switch (resultOfInput()) {

            case 1: {
                User user = userService.register();

                if (user == null) {
                    System.out.println("this user already exists ...");
                    start();

                }
                userPanel(user);
            }
            case 2: {
                User user = userService.login();

                if (user == null) {

                    System.out.println("this user not exists with this userName & password ..");
                    start();
                }
                userPanel(user);
            }

            case 3 :
                System.out.println("have nice day ...");
                System.exit(0);
        }


    }

    private void userPanel(User user) throws CloneNotSupportedException {

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
                activityService.changeStatus(user);
                userPanel(user);
                break;


            case 4 : start();
            default:
                System.out.println("your input not valid  try again ...");
                userPanel(user);
        }
    }

    public int resultOfInput() {

        Scanner scanner = new Scanner(System.in);

        int result = 0;

        try {
            result = scanner.nextInt();

        } catch (Exception e) {
            System.out.println("input not valid try again !!!");
            resultOfInput();
        }

        return result;
    }
}
