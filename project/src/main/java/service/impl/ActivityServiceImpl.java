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
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        System.out.println("enter sort based of witch property\n1.time\n2.state\n3.description");
        Stream<Activity> stream = activities.stream();
        Comparator<Activity> comparator = null;
        switch (ApplicationContext.scannerForInteger.nextInt()) {

            case 1 -> comparator = Comparator.comparing(Activity::getTimeTask);

            case 2 -> comparator = Comparator.comparingInt(x -> x.getStateOfActivity().getNumber());

            case 3 -> comparator = (x, y) -> {

                return x.getDescription().length() > y.getDescription().length() ? 1 :
                        x.getDescription().length() < y.getDescription().length() ? -1 :
                                x.getDescription().compareTo(y.getDescription());
            };

            default -> {
                System.out.println("input not valid try again !!!");
                showActivity(activities);
            }

        }


        System.out.println("if you want show Ascending enter asc \nif you want Descending enter desc ");

        String result = ApplicationContext.scannerForString.nextLine();

        switch (result) {

            case "asc":
                showAscendingActivity(stream, comparator);
                break;

            case "desc":
                showDescendingActivity(stream, comparator);
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

            Activity activities = findByID(number);
            System.out.println("status of this activity is : " + activities.getStateOfActivity() + "\n");

            System.out.print("enter status from  ");
            List<StateOfActivity> myList = Arrays.stream(StateOfActivity.values()).filter(x -> x.getNumber() > activities.
                    getStateOfActivity().getNumber()).collect(Collectors.toList());

            if (myList.isEmpty()) {
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

    public void showAscendingActivity(Stream<Activity> stream, Comparator<? super Activity> comparator) {

        System.out.printf("%50s\n", "***************|All Activity|***************");
        stream.sorted(comparator).forEach(System.out::println);
        System.out.printf("%48s \n", "------------------------------------------");
    }

    public void showDescendingActivity(Stream<Activity> stream, Comparator<? super Activity> comparator) {

        System.out.printf("%50s\n", "***************|All Activity|***************");
        stream.sorted(comparator).sorted(Comparator.reverseOrder()).
                forEach(System.out::println);
        System.out.printf("%48s \n", "------------------------------------------");
    }

    @Override
    public List<Activity> getAllActivity(User user) {
        return repository.getAll(user);
    }

}
