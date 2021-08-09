package domain;

import base.BaseEntity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
public class User extends BaseEntity implements Cloneable {


    private String name;

    private String family;

    @Column(name = "national_code")
    private String nationalCode;


    private String userName;

    private String password;


    @OneToMany
    private List<Activities> activities=new LinkedList<>();

    public User() {

    }

    public User(String name, String family, String nationalCode) {

        this.name = name;
        this.family = family;
        this.nationalCode = nationalCode;
    }

    public User(String name, String family, String nationalCode, List<Activities> activities) {
        this.name = name;
        this.family = family;
        this.nationalCode = nationalCode;
        this.activities = activities;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Activities> getActivities() {
        return activities;
    }

    public void setActivities(List<Activities> activities) {
        this.activities = activities;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", userName='" + userName + '\'' +
                 activities;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
