package repository;

import base.impl.BaseRepositoryImpl;
import domain.Activities;
import domain.User;

import java.util.List;

public class ActivityRepo extends BaseRepositoryImpl<Activities> {

    public List<Activities> getAllActivity(User user){

      User myUser =  session.find(User.class,user.getId());

      return myUser.getActivities();
    }
}
