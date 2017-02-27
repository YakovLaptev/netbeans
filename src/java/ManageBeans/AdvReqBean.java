package ManageBeans;

import JavaBeans.Advertising;
import Dao.AdvertisignDAO;
import Dao.CampaignDAO;
import Dao.CustomerDAO;
import Dao.OrderDAO;
import Dao.StatusDAO;
import JavaBeans.Campaign;
import JavaBeans.Order;
import JavaBeans.Сustomer;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author Yakov
 */
public class AdvReqBean {

    private FacesMessage msg = new FacesMessage();
    private List<Advertising> advertisings;
    private AdvertisignDAO dao;
    private OrderDAO ordDao;
    private StatusDAO statDao;
    private CustomerDAO cusDao;
    private CampaignDAO campDao;
    private List<Advertising> selectedAdvertisings;
    private Advertising selectedAdvertising;
    private List<String> campaignNames;

    public AdvReqBean() {
        this.dao = new AdvertisignDAO();
        this.campDao = new CampaignDAO();
        campaignNames = new ArrayList<>();
        try {
            for (Campaign c : campDao.getAll()) {
                campaignNames.add(c.getName());
            }
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(AdvReqBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete() throws ParseException {
        try {
            dao.delete(selectedAdvertising);
            FacesContext.getCurrentInstance().getExternalContext().redirect("./managerIndex.xhtml");
        } catch (SQLException ex) {
            Logger.getLogger(UsersReqBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AdvReqBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        Advertising adv = advertisings.get(event.getRowIndex());
        if (newValue != null && !newValue.equals(oldValue)) {
            try {
                dao.update(adv);
            } catch (SQLException ex) {
                Logger.getLogger(UsersReqBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setSelectedAdv(String name) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("select", name);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("./advertisingDetails.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(AdvReqBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void makeOrder(String name) {
        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        cusDao = new CustomerDAO();
        ordDao = new OrderDAO();
        statDao = new StatusDAO();
        Order o = new Order();
        try {
            Сustomer cus = cusDao.getByUserLogin(principal.getName());
            String aname = dao.getByName(name).getName();
            o.setAdvertisingName(aname);
            o.setCreatedDate(new java.sql.Date(java.util.Calendar.getInstance().getTimeInMillis()));
            o.setCustomer(cus);
            o.setStatus(statDao.getStatusById(1));
            ordDao.create(o);
            FacesContext.getCurrentInstance().getExternalContext().redirect("./customerOrders.xhtml");
        } catch (SQLException | ParseException | IOException ex) {
            Logger.getLogger(AdvReqBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PostConstruct
    public void allAdvertisings() {
        try {
            advertisings = dao.getAll();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(AdvReqBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public FacesMessage getMsg() {
        return msg;
    }

    public void setMsg(FacesMessage msg) {
        this.msg = msg;
    }

    public List<Advertising> getAdvertisings() {
        return advertisings;
    }

    public AdvertisignDAO getDao() {
        return dao;
    }

    public List<Advertising> getSelectedAdvertisings() {
        return selectedAdvertisings;
    }

    public void setAdvertisings(List<Advertising> advertisings) {
        this.advertisings = advertisings;
    }

    public void setDao(AdvertisignDAO dao) {
        this.dao = dao;
    }

    public void setSelectedAdvertisings(List<Advertising> selectedAdvertisings) {
        this.selectedAdvertisings = selectedAdvertisings;
    }

    public Advertising getSelectedAdvertising() {
        return selectedAdvertising;
    }

    public void setSelectedAdvertising(Advertising selectedAdvertising) {
        this.selectedAdvertising = selectedAdvertising;
    }

    public List<String> getCampaignNames() {
        return campaignNames;
    }

    public void setCampaignNames(List<String> campaignNames) {
        this.campaignNames = campaignNames;
    }

}
