package TestCMD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Yakov
 */
public class CMD {

    Process p;
    BufferedReader inputStream;

    public void showAll(String query) throws IOException {
        Process p = Runtime.getRuntime().exec(query);
        inputStream = new BufferedReader(new InputStreamReader(p.getInputStream(), "cp866"));
        String s;
        while ((s = inputStream.readLine()) != null) {
            System.out.println(" " + s);
        }
    }

    public int getOne(String query) throws IOException {
        Process p = Runtime.getRuntime().exec(query);
        inputStream = new BufferedReader(new InputStreamReader(p.getInputStream(), "cp866"));
        String s;
        int a;
        inputStream.readLine();
        s = inputStream.readLine();
        a = Integer.parseInt(s);
        return a;
    }

    public int count(String name) throws IOException {
        String query = "mysql advertisingagency -uroot -proot -e\"SELECT COUNT(*) FROM " + name + ";\"";
        return getOne(query);
    }
}
