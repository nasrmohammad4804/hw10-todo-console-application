package domain;

import base.BaseEntity;
import domain.enumaration.StateOfActivity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Time;

@Entity
public class Activities extends BaseEntity implements Comparable<Activities>{

    private static final String STATUS="status";

    @Column(columnDefinition = "text")
    private String description;


    private Time timeTask;

    @Column(name = STATUS)
    private StateOfActivity stateOfActivity;


    public Activities(){

    }

    public Activities(String description) {
        this.description = description;
    }


    public Activities(String description, Time timeTask, StateOfActivity stateOfActivity) {
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
        return "Activities{" +
                "id=" + getId() +
                ", description='" + description + '\'' +
                ", time=" + timeTask +
                ", stateOfActivity=" + stateOfActivity +
                '}';
    }

    @Override
    public int compareTo(Activities o) {

         return this.timeTask.compareTo(o.timeTask);
    }
}
