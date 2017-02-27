package JavaBeans;

import java.util.Date;

/**
 *
 * @author Yakov
 */
public class Order {

    private int number;
    private Сustomer customer;
    private String advertisingName;
    private Date createdDate;
    private Status status;

    public int getNumber() {
        return number;
    }

    public Сustomer getCustomer() {
        return customer;
    }

    public String getAdvertisingName() {
        return advertisingName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setCustomer(Сustomer customer) {
        this.customer = customer;
    }

    public void setAdvertisingName(String advertisingName) {
        this.advertisingName = advertisingName;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
