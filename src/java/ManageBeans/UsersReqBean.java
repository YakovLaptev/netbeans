package ManageBeans;

import JavaBeans.*;
import Dao.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author Yakov
 */
public class UsersReqBean {

    private FacesMessage msg;
    private UserDAO dao;
    private List<String> roles;
    private List<User> users;
    private User selecteduser;

    public UsersReqBean() {
        this.dao = new UserDAO();
        String[] rs = new String[3];
        rs[0] = "Admin";
        rs[1] = "Manager";
        rs[2] = "Customer";
        roles = Arrays.asList(rs);
    }

    public void delete() throws ParseException {
        try {
            dao.delete(selecteduser);
            FacesContext.getCurrentInstance().getExternalContext().redirect("./adminUsers.xhtml");
        } catch (SQLException | IOException ex) {
            Logger.getLogger(UsersReqBean.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }


    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        User user = users.get(event.getRowIndex());
        if(newValue != null && !newValue.equals(oldValue)) {
            user.setRole(event.getNewValue().toString());
            try {
                dao.changeRole(user);
            } catch (SQLException ex) {
                Logger.getLogger(UsersReqBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @PostConstruct
    public void allUsers() {
        try {
            setUsers(dao.getAll());
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(UsersReqBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public FacesMessage getMsg() {
        return msg;
    }

    public List<User> getUsers() {
        return users;
    }

    public User getSelecteduser() {
        return selecteduser;
    }

    public void setMsg(FacesMessage msg) {
        this.msg = msg;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setSelecteduser(User selecteduser) {
        this.selecteduser = selecteduser;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }



}
