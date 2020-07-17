package project.assistant.Model.Beans;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;


@MappedSuperclass
public class BaseBean {

    @Basic
    @Column(name = "ACTIVE",nullable = false)
    private boolean active=true;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name="CREATE_DATE",nullable = false)
    private Date CreateDate=Date.from(Instant.now());

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }
}
