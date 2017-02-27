package ManageBeans;

import JavaBeans.Сustomer;
import Dao.*;
import JavaBeans.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Yakov
 */
public class NewUserReqBean {

    private FacesMessage msg;
    private UserDAO dao;
    private CustomerDAO cusdao;
    private Сustomer newCustomer;

    public NewUserReqBean() {
        this.dao = new UserDAO();
        this.cusdao = new CustomerDAO();
        newCustomer = new Сustomer();
        newCustomer.setUser(new User());
    }
    
    public void addNew() {
        User u = newCustomer.getUser();
        newCustomer.setRegistrationDate(new java.sql.Date(java.util.Calendar.getInstance().getTimeInMillis()));
        u.setRole("Customer");
        try {
            dao.create(u);
            cusdao.create(newCustomer);
            FacesContext.getCurrentInstance().getExternalContext().redirect("./");
        } catch (SQLException | IOException ex) {
            Logger.getLogger(NewUserReqBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public FacesMessage getMsg() {
        return msg;
    }

    public Сustomer getNewCustomer() {
        return newCustomer;
    }

    public void setMsg(FacesMessage msg) {
        this.msg = msg;
    }

    public void setNewCustomer(Сustomer newCustomer) {
        this.newCustomer = newCustomer;
    }

}
