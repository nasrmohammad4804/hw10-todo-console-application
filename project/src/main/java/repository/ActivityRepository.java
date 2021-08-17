package repository;

import base.repository.BaseRepository;
import domain.Activity;
import domain.User;

import java.util.List;

public interface ActivityRepository extends BaseRepository<Activity,Long> {

    List<Activity> getAll(User user);
}
