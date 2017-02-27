package ManageBeans;

import Dao.CustomerDAO;
import Dao.NotificationDAO;
import JavaBeans.Notification;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Yakov
 */
public class NotifReqBean {

    private FacesMessage msg;
    private List<Notification> notificationsForCustomer;
    private CustomerDAO cusDao = new CustomerDAO();
    private NotificationDAO notifDao = new NotificationDAO();

    public NotifReqBean() {
    }

    public void delete(int number) throws ParseException {
        try {
            notifDao.delete(notifDao.getByNumber(number));
            FacesContext.getCurrentInstance().getExternalContext().redirect("./customerNotifications.xhtml");
        } catch (SQLException ex) {
            Logger.getLogger(UsersReqBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NotifReqBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Notification> getNotificationsForCustomer() {
        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        notificationsForCustomer = new ArrayList<>();
        try {
            notificationsForCustomer = notifDao.getByCustomerEmail(cusDao.getByUserLogin(principal.getName()).getEmail());
        } catch (SQLException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notificationsForCustomer;
    }

    public FacesMessage getMsg() {
        return msg;
    }

    public void setMsg(FacesMessage msg) {
        this.msg = msg;
    }

}
