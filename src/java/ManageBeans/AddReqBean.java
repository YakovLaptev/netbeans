package ManageBeans;

import JavaBeans.Advertising;
import Dao.AdvertisignDAO;
import Dao.CampaignDAO;
import JavaBeans.Campaign;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;

/**
 *
 * @author Yakov
 */
public class AddReqBean {

    private AdvertisignDAO advdao;
    private Advertising newAdv = new Advertising();
    private CampaignDAO camdao;
    private Campaign newCamp = new Campaign();


    public AddReqBean() {
        this.advdao = new AdvertisignDAO();
        this.camdao = new CampaignDAO();
    }

    public void makeAdvertising() {
        try {
            advdao.create(newAdv);
            FacesContext.getCurrentInstance().getExternalContext().redirect("./managerIndex.xhtml");
        } catch (SQLException | IOException ex) {
            Logger.getLogger(AddReqBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void makeCampaign() {
        try {
            camdao.create(newCamp);
            FacesContext.getCurrentInstance().getExternalContext().redirect("./managerIndex.xhtml");
        } catch (SQLException | IOException ex) {
            Logger.getLogger(AddReqBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Advertising getNewAdv() {
        return newAdv;
    }

    public void setNewAdv(Advertising newAdv) {
        this.newAdv = newAdv;
    }

    public Campaign getNewCamp() {
        return newCamp;
    }

    public void setNewCamp(Campaign newCamp) {
        this.newCamp = newCamp;
    }

}
