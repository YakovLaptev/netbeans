package JavaBeans;

import java.util.Date;

/**
 *
 * @author Yakov
 */
public class Campaign {
    private String name;
    private String about;
    private Date startDate;
    private Date endDate;

    public String getName() {
        return name;
    }
 
    public String getAbout() {
        return about;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setAbout(String about) {
        this.about = about;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }   

}
