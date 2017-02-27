package ManageBeans;

import JavaBeans.Advertising;
import Dao.AdvertisignDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Yakov
 */
public class DetailReqBean {

    private FacesMessage msg;
    private HttpSession session;
    private AdvertisignDAO advdao;
    private Advertising selectedAdvertising;

    public DetailReqBean() {
        this.session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        this.advdao = new AdvertisignDAO();
    }

    public Advertising getAdvDetail() {
        try {
            selectedAdvertising = advdao.getByName(session.getAttribute("select").toString());
            return selectedAdvertising;
        } catch (SQLException ex) {
            Logger.getLogger(DetailReqBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
