package project.assistant.Model.Beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ROLE_BEAN")
public class RoleBean extends BaseBean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ROLE_ID")
    private Long id;

    @Column(name="ROLE", unique = true)
    private String role;

    @Basic
    @ManyToMany(mappedBy = "roleBeans")
    @JsonIgnore
    private Set<UserBean> userBeans=new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public Set<UserBean> getUserBeans() {
        return userBeans;
    }

    public void setUserBeans(Set<UserBean> userBeans) {
        this.userBeans = userBeans;
    }
}