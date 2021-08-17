package service.impl;

import base.service.impl.BaseServiceImpl;
import domain.Activity;
import domain.User;
import domain.enumaration.StateOfActivity;
import repository.impl.ActivityRepositoryImpl;
import service.ActivityService;
import service.util.ApplicationContext;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ActivityServiceImpl extends BaseServiceImpl<Activity, Long, ActivityRepositoryImpl>
        implements ActivityService {

    public ActivityServiceImpl(ActivityRepositoryImpl repository) {
        super(repository);
    }

    @Override
    public void showActivity(List<Activity> activities) {
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


    @Override
    public void updateActivity(User user) {

        List<Activity> activityList = getAllActivity(user);

        if (activityList.isEmpty()) {
            System.out.println("not activity for user\n\n");
            return;
        }
        activityList.forEach(System.out::println);

        System.out.println("enter activity id");

        Long number = ApplicationContext.scannerForInteger.nextLong();


        try {

            Activity activities = findByID( number); //activityList.stream().filter(x -> x.getId() == number).findFirst().get();
            System.out.println("status of this activity is : " + activities.getStateOfActivity() + "\n");

            System.out.print("enter status from  ");
            List<StateOfActivity> myList = Arrays.stream(StateOfActivity.values()).filter(x -> x.getNumber() > activities.
                    getStateOfActivity().getNumber()).collect(Collectors.toList());

            if (myList.isEmpty()){
                System.out.println("state is COMPLETED and not have changed !!! \n");
                return;
            }

            System.out.println(myList);

            StateOfActivity state = StateOfActivity.valueOf(ApplicationContext.scannerForString.nextLine());

            if (state.getNumber() > activities.getStateOfActivity().getNumber()) {

                activities.setStateOfActivity(state);
                update(activities);

                System.out.println("state change to " + state + "\n\n");
            } else System.out.println("not possible to change status -_- ..");

        } catch (Exception e) {
            System.out.println("this activityNumber not exists !!!");
        }
    }

    @Override
    public List<Activity> getAllActivity(User user) {
        return repository.getAll(user);
    }

    private void showDescendingActivity(List<Activity> activities) {

        System.out.printf("%50s\n", "***************|All Activity|***************");
        activities.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);

        System.out.printf("%48s \n", "------------------------------------------");
    }

    private void showAscendingActivity(List<Activity> activities) {

        System.out.printf("%50s\n", "***************|All Activity|***************");
        activities.stream().sorted().forEach(System.out::println);

        System.out.printf("%48s \n", "------------------------------------------");
    }
}
