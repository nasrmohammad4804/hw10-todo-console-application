package domain;

import base.BaseEntity;
import domain.enumaration.StateOfActivity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Time;

@Entity
public class Activities extends BaseEntity implements Comparable<Activities>{

    @Column(columnDefinition = "text")
    private String description;

   // @Temporal(TemporalType.TIME)
    private Time time;

    @Column(name = "status")
    private StateOfActivity stateOfActivity;

    public Activities(){

    }

    public Activities(String description) {
        this.description = description;
    }

    public Activities(String description , Time time){

        this.description=description;
        this.time=time;
    }

    public Activities(String description, Time time, StateOfActivity stateOfActivity) {
        this.description = description;
        this.time = time;
        this.stateOfActivity = stateOfActivity;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
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
                ", time=" + time +
                ", stateOfActivity=" + stateOfActivity +
                '}';
    }

    @Override
    public int compareTo(Activities o) {

         return this.time.compareTo(o.time);
    }
}
