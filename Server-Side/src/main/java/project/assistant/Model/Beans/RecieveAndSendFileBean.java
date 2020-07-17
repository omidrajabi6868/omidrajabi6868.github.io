package project.assistant.Model.Beans;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by omid on 9/9/2016.
 */
@Entity(name = "RecieveAndSendFileBean")
@Table(name = "RECIEVE_SEND_FILE")
public class RecieveAndSendFileBean extends BaseBean{
    @Id
    @Column(name="SEND_FILE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    @Column(name = "FK_FILE_ID")
    private String fileIds;

    @Basic
    @Column(name = "FK_RECEVER_USER_ID")
    private String recieversFileIds;

    @Basic
    @Column(name = "FK_SENDER_USER_ID")
    private Long senderFileId;


    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name="SEND_DATE")
    private Date sendDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileIds() {
        return fileIds;
    }

    public void setFileIds(String fileIds) {
        this.fileIds = fileIds;
    }

    public String getRecieversFileIds() {
        return recieversFileIds;
    }

    public void setRecieversFileIds(String recieversFileIds) {
        this.recieversFileIds = recieversFileIds;
    }

    public Long getSenderFileId() {
        return senderFileId;
    }

    public void setSenderFileId(Long senderFileId) {
        this.senderFileId = senderFileId;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

}

