package ManageBeans;

import Dao.CustomerDAO;
import Dao.NotificationDAO;
import JavaBeans.Order;
import Dao.OrderDAO;
import Dao.StatusDAO;
import JavaBeans.Notification;
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

/**
 *
 * @author Yakov
 */
public class OrdReqBean {

    private FacesMessage msg;
    private List<Order> orders;
    private OrderDAO dao;
    private StatusDAO statusDao;
    private CustomerDAO cusDao = new CustomerDAO();
    private NotificationDAO notifDao;
    private List<Order> selectedOrders;
    private List<Order> ordersForCustomer;

    public OrdReqBean() {
        this.dao = new OrderDAO();
    }

    public void changeStatus(Order o, int id) throws IOException {
        notifDao = new NotificationDAO();
        statusDao = new StatusDAO();
        Notification notif = new Notification();
        try {
            o.getStatus().setId(id);
            dao.changeStatus(o);
            if (id == 5) {
                dao.delete(o);
            }
            notif.setCreatedDate(new java.sql.Date(java.util.Calendar.getInstance().getTimeInMillis()));
            notif.setRecipient(o.getCustomer().getEmail());
            notif.setAbout("Статус заказа №" + o.getNumber() + " был изменен на " + statusDao.getStatusById(id).getName());
            notifDao.create(notif);
            FacesContext.getCurrentInstance().getExternalContext().redirect("./managerOrders.xhtml");
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(OrdReqBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Order> getOrdersForCustomer() {
        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        ordersForCustomer = new ArrayList<>();
        try {
            ordersForCustomer = dao.getByCustomer((cusDao.getByUserLogin(principal.getName())));
        } catch (SQLException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(OrdReqBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ordersForCustomer;
    }
    
    @PostConstruct
    public void allOrders() {
        try {
            orders = dao.getAll();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(OrdReqBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setStatus(Order o, int id) throws IOException {
        try {
            o.getStatus().setId(id);
            dao.changeStatus(o);
            if (id == 5) {
                dao.delete(o);
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect("./customerOrders.xhtml");
        } catch (SQLException ex) {
            Logger.getLogger(OrdReqBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public FacesMessage getMsg() {
        return msg;
    }

    public void setMsg(FacesMessage msg) {
        this.msg = msg;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public OrderDAO getDao() {
        return dao;
    }

    public List<Order> getSelectedOrders() {
        return selectedOrders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setDao(OrderDAO dao) {
        this.dao = dao;
    }

    public void setSelectedOrders(List<Order> selectedOrders) {
        this.selectedOrders = selectedOrders;
    }

}
