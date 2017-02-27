package ManageBeans;

import JavaBeans.Сustomer;
import Dao.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

/**
 *
 * @author Yakov
 */
public class CustomersReqBean {

    private FacesMessage msg;
    private CustomerDAO cusdao;
    private List<Сustomer> customers;

    public CustomersReqBean() {
        this.cusdao = new CustomerDAO();
    }

    @PostConstruct
    public void allCustomers() {
        try {
            customers = cusdao.getAll();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(CustomersReqBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public FacesMessage getMsg() {
        return msg;
    }

    public List<Сustomer> getCustomers() {
        return customers;
    }
}
