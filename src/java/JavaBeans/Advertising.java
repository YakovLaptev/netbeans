package JavaBeans;

/**
 *
 * @author Yakov
 */
public class Advertising {
    private String name;
    private String category;
    private int price;
    private String briefDescription;
    private String fullDescription;
    private String campaignName;

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }
    
    public String getCampaignName() {
        return campaignName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

}
