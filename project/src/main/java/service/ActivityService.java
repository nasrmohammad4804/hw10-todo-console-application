package service;

import domain.Activity;
import domain.User;

import java.util.List;

public interface ActivityService {

    void showActivity(List<Activity> activities);

    void updateActivity(User user);

    List<Activity> getAllActivity(User user);
}

