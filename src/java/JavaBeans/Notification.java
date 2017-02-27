package JavaBeans;

import java.sql.Date;

/**
 *
 * @author Yakov
 */
public class Notification {

    private int number;
    private String about;
    private Date createdDate;
    private String recipient;

    public int getNumber() {
        return number;
    }

    public String getAbout() {
        return about;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

}
