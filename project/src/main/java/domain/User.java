package domain;

import base.BaseEntity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {User.USER_NAME,User.NATIONAL_CODE}))
public class User extends BaseEntity implements Cloneable {

    public static final String NATIONAL_CODE ="national_code";
    public static final String USER_NAME ="username";
    public static final String USER_ID="user_id";

    private String name;

    private String family;

    @Column(name = NATIONAL_CODE)
    private String nationalCode;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;


    @OneToMany
    @JoinColumn(name= USER_ID)
    private List<Activities> activities=new LinkedList<>();

    public User() {

    }

    public User(String name, String family, String nationalCode) {

        this.name = name;
        this.family = family;
        this.nationalCode = nationalCode;
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
