package project.assistant.Model.Beans;


import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Transient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import project.assistant.MvcController.PatientControler;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "UserBean")
@Table(name = "USER_BEAN")
public class UserBean extends BaseBean {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long id;

    @Length(max = 65)
    @Column(name = "FIRSTNAME")
    @NotEmpty(message = "*Please provide your name")
    private String firstName;


    @Length(max = 65)
    @Column(name = "LASTNAME")
    @NotEmpty(message = "*Please provide your last name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;


    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @Size(max = 15)
    @Column(name = "MOBILE_NUMBER")
    private String mobileNo;

    @Size(max = 100)
    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PASSWORD")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    @Transient
    private String password;

    @Column(name = "PROFILE_IMAGE_ID")
    private Long mainImageId;

    @Basic
    @Column(name="EDUCATIONS")
    private String education;


    @Email(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="Invalid email!")
    @NotEmpty(message = "*Please provide an email")
    @Column(name = "USERNAME", unique = true)
    private String username;


    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<RoleBean> roleBeans;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "CHOOSE_FRIENDS", joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "FRIEND_ID")
    )
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        this.password = bCryptPasswordEncoder.encode(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Set<RoleBean> getRoleBeans() {
        return roleBeans;
    }

    public void setRoleBeans(Set<RoleBean> roleBeans) {
        this.roleBeans = roleBeans;
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

    public Long getMainImageId() {
        return mainImageId;
    }

    public void setMainImageId(Long mainImageId) {
        this.mainImageId = mainImageId;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Set<UserBean> getFriends() {
        return friends;
    }

    public void setFriends(Set<UserBean> friends) {
        this.friends = friends;
    }
}
