package ManageBeans;

import Dao.*;
import JavaBeans.*;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginBean {

    private Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
    private String login;
    private String role;
    private List<Notification> notificationsForCustomer;
    private UserDAO userDao = new UserDAO();
    private CustomerDAO cusDao = new CustomerDAO();
    private NotificationDAO notifDao = new NotificationDAO();

    public LoginBean() {
        if (principal != null) {
            try {
                login = principal.getName();
                role = userDao.getByLogin(login).getRole();
            } catch (SQLException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public User getUserByUsername() throws SQLException, Exception {
        if (principal != null) {
            login = principal.getName();
        }
        User user = userDao.getByLogin(login);
        return user;
    }

    public boolean getUserIn() {
        if (principal != null) {
            return true;
        } else {
            return false;
        }
    }

    public List<Notification> getNotificationsForCustomer() {
        notificationsForCustomer = new ArrayList<>();
        try {
            notificationsForCustomer = notifDao.getByCustomerEmail(cusDao.getByUserLogin(login).getEmail());
            return notificationsForCustomer;
        } catch (SQLException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void logout() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        try {
            request.logout();
            session.invalidate();
            FacesContext.getCurrentInstance().getExternalContext().redirect("./");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setNotificationsForCustomer(List<Notification> notificationsForCustomer) {
        this.notificationsForCustomer = notificationsForCustomer;
    }
}
