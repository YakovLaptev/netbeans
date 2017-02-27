package DAOTest;

import java.io.IOException;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Test;
import Dao.AdvertisignDAO;
import JavaBeans.Advertising;
import TestCMD.CMD;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author Yakov
 */
public class AdvertisingTest {

    @Test
    public void testGetAllSize() throws IOException, ClassNotFoundException, SQLException, ParseException {
        AdvertisignDAO ad = new AdvertisignDAO();
        List<Advertising> list = ad.getAll();
        Assert.assertEquals(3, list.size());
    }

    @Test
    public void testGetAll() throws IOException, ClassNotFoundException, SQLException, ParseException {
        AdvertisignDAO ad = new AdvertisignDAO();
        List<Advertising> list = ad.getAll();
        Assert.assertEquals("CampName1", list.get(0).getCampaignName());
    }

    @Test
    public void testGetByName() throws IOException, ClassNotFoundException, SQLException {
        AdvertisignDAO ad = new AdvertisignDAO();
        Advertising adv = ad.getByName("AdvName1");
        Assert.assertEquals("AdvName1", adv.getName());
    }

    @Test
    public void testCreate() throws IOException, ClassNotFoundException, SQLException {
        CMD cmd = new CMD();
        AdvertisignDAO ad = new AdvertisignDAO();
        Advertising a = new Advertising();
        a.setName("AdvName5");
        a.setCategory("test5");
        a.setPrice(130);
        a.setBriefDescription("test");
        a.setFullDescription("testtest5");
        a.setCampaignName("CampName3");
        ad.create(a);
        Assert.assertEquals(4, cmd.count("advertising"));
    }

    @Test
    public void testDelete() throws IOException, ClassNotFoundException, SQLException {
        CMD cmd = new CMD();
        AdvertisignDAO ad = new AdvertisignDAO();
        Advertising a = new Advertising();
            a.setName("AdvName4");
            a.setCategory("test4");
            a.setPrice(130);
            a.setBriefDescription("test");
            a.setFullDescription("testtest4");
            a.setCampaignName("CampName3");
        ad.delete(a);
        Assert.assertEquals(3, cmd.count("advertising"));
    }
    
    @Test
    public void testUpdate() throws IOException, ClassNotFoundException, SQLException {
        AdvertisignDAO ad = new AdvertisignDAO();
        Advertising a = new Advertising();
            a.setName("AdvName2");
            a.setCategory("test5");
            a.setPrice(150);
            a.setBriefDescription("test");
            a.setFullDescription("testtest5");
            a.setCampaignName("CampName3");
        ad.update(a);
        Advertising b = new Advertising();
            b.setName("AdvName2");
            b.setCategory("test5");
            b.setPrice(150);
            b.setBriefDescription("test");
            b.setFullDescription("testtest5");
            b.setCampaignName("CampName3"); 
        Assert.assertEquals(b.getFullDescription(), ad.getByName(a.getName()).getFullDescription());
    }
}
