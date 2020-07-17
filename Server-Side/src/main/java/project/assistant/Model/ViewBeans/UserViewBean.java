package project.assistant.Model.ViewBeans;




import project.assistant.Model.Beans.Gender;
import project.assistant.Model.Beans.RoleBean;
import project.assistant.Model.Beans.UserBean;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class UserViewBean {

    private Long id;

    private String firstName;

    private String lastName;

    private Gender gender;

    private Date dateOfBirth;

    private String mobileNo;

    private String address;

    private String username;

    private String education;

    private Long mainImageId;

    private Set<RoleBean> roleBeans;

    private Set<UserBean> friends;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<RoleBean> getRoleBeans() {
        return roleBeans;
    }

    public void setRoleBeans(Set<RoleBean> roleBeans) {
        this.roleBeans = roleBeans;
    }

    public Set<UserBean> getFriends() {
        return friends;
    }

    public void setFriends(Set<UserBean> friends) {
        this.friends = friends;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Long getMainImageId() {
        return mainImageId;
    }

    public void setMainImageId(Long mainImageId) {
        this.mainImageId = mainImageId;
    }
}
