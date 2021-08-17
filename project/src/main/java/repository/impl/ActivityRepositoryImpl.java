package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import domain.Activity;
import domain.User;
import repository.ActivityRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class ActivityRepositoryImpl extends BaseRepositoryImpl<Activity,Long>
        implements ActivityRepository {

    public ActivityRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Activity> getEntityClass() {
        return Activity.class;
    }

    @Override
    public List<Activity> getAll(User user) {
        return   entityManager.find(User.class,user.getId())
                .getActivities();
    }
}
