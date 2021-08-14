package service;

import domain.Activities;
import domain.User;
import domain.enumaration.StateOfActivity;
import repository.ActivityRepo;
import service.impl.BaseServiceImpl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ActivityService extends BaseServiceImpl<Activities, ActivityRepo> {

    private final Scanner scannerForString = new Scanner(System.in);
    private final Scanner scannerForInteger = new Scanner(System.in);

    protected ActivityService(ActivityRepo repository) {
        super(repository);

    }

    public void showActivity(List<Activities> activities) {

        if (activities.isEmpty()) {
            System.out.println("dont activity yet\n\n");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("if you want show Ascending enter asc \nif you want Descending enter desc ");
        String result = scanner.nextLine();

        switch (result) {

            case "asc":
                showAscendingActivity(activities);
                break;

            case "desc":
                showDescendingActivity(activities);
                break;
            default:
                System.out.println("your input not valid try again");
                showActivity(activities);
        }

    }

    private void showDescendingActivity(List<Activities> activities) {

        System.out.printf("%50s\n", "***************|All Activity|***************");
        activities.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);

        System.out.printf("%48s \n","------------------------------------------");
    }

    private void showAscendingActivity(List<Activities> activities) {

        System.out.printf("%50s\n", "***************|All Activity|***************");
        activities.stream().sorted().forEach(System.out::println);

        System.out.printf("%48s \n","------------------------------------------");
    }

    public void addActivity(Activities activities) {  // move to userService


        repository.add(activities);

    }

    public void changeStatus(User user) {

        List<Activities> list = repository.getAllActivity(user);

        if (list.isEmpty()) {
            System.out.println("not activity for user\n\n");
            return;
        }

        for (Activities activities : list)
            System.out.println(activities);

        System.out.println("enter activity");
        int number = scannerForInteger.nextInt();

        try {

            Activities activities = list.stream().filter(x -> x.getId() == number).findFirst().get();
            System.out.println("status of this activity is : "+activities.getStateOfActivity()+"\n");

            System.out.print("enter status from  ");
          List<StateOfActivity> myList =  Arrays.stream(StateOfActivity.values()).filter(x -> x.getNumber()>activities.
                    getStateOfActivity().getNumber()).collect(Collectors.toList());

            System.out.println(myList);

            StateOfActivity state = StateOfActivity.valueOf(scannerForString.nextLine());

            if (state.getNumber() > activities.getStateOfActivity().getNumber()) {

                activities.setStateOfActivity(state);
                System.out.println("state change to " + state + "\n\n");
            } else System.out.println("not possible to change status -_- ..");

        } catch (Exception e) {
            System.out.println("this activityNumber not exists !!!");
        }

    }

    public List<Activities> getAllActivity(User user) {

        return repository.getAllActivity(user);
    }
}
