package domain;

import base.domain.BaseEntity;
import domain.enumaration.StateOfActivity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Time;

@Entity
public class Activity extends BaseEntity<Long> implements Comparable<Activity> {

    private static final String STATUS="status";

    @Column(columnDefinition = "text")
    private String description;


    private Time timeTask;

    @Enumerated(EnumType.STRING)
    @Column(name = STATUS)
    private StateOfActivity stateOfActivity;


    public Activity(){

    }

    public Activity(String description) {
        this.description = description;
    }


    public Activity(String description, Time timeTask, StateOfActivity stateOfActivity) {
        this.description = description;
        this.timeTask = timeTask;
        this.stateOfActivity = stateOfActivity;
    }

    public Time getTimeTask() {
        return timeTask;
    }

    public void setTimeTask(Time time) {
        this.timeTask = time;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StateOfActivity getStateOfActivity() {
        return stateOfActivity;
    }

    public void setStateOfActivity(StateOfActivity stateOfActivity) {
        this.stateOfActivity = stateOfActivity;
    }

    @Override
    public String toString() {
        return
                "id=" + getId() +
                        ", description='" + description + '\'' +
                        ", time=" + timeTask +
                        ", stateOfActivity=" + stateOfActivity;
    }

    @Override
    public int compareTo(Activity o) {

        return this.timeTask.compareTo(o.timeTask);
    }
}
