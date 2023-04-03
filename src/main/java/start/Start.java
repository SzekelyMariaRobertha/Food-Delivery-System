package start;

import bll.*;
import dal.Reader;
import presentation.*;

import java.io.*;
import java.util.*;

/**
 * This class represent the controller of the application
 * Here is the point where the application starts/runs
 *
 * @author Szekely Maria-Robertha
 */

public class Start {

    public static File file;
    public static HashMap<String, MenuItem> menuitemslist;
    public static DeliveryService observable;
    public static EmployeeGUI observer;
    public static HashMap<Order, List<MenuItem>> hashMap;

    public static void getComposedProduct(String name, String base) {
        List<BaseProduct> baseProducts = new ArrayList<>();
        String[] baseP = base.split(";");
        for (String s : baseP) {
            String[] b = s.split(",");
            baseProducts.add(new BaseProduct(b[0], Float.parseFloat(b[1]), Float.parseFloat(b[2]), Float.parseFloat(b[3]), Float.parseFloat(b[4]), Float.parseFloat(b[5]), Float.parseFloat(b[6])));
        }
        String[] mainName = name.split(",");
        MenuItem menuItem = new CompositeProduct(baseProducts, mainName[0]);
        menuitemslist.put(menuItem.getTitle(), menuItem);
    }

    public static void main(String[] args) {

        try {
            menuitemslist = new HashMap<>();
            hashMap = new HashMap<>();

            File file = new File("src/main/resources/products.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            while (line != null) {
                if (line.contains("COMPOSEDBY")) {
                    String[] c = line.split("COMPOSEDBY");
                    String name = c[0];
                    String base = c[1];
                    getComposedProduct(name, base);
                    line = bufferedReader.readLine();
                } else {
                    String[] content = line.split(",");
                    MenuItem menuItem = new BaseProduct(content[0], Float.parseFloat(content[1]), Float.parseFloat(content[2]), Float.parseFloat(content[3]), Float.parseFloat(content[4]), Float.parseFloat(content[5]), Float.parseFloat(content[6]));
                    menuitemslist.put(menuItem.getTitle(), menuItem);
                    line = bufferedReader.readLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        UserLogin userLogin = new UserLogin();
        userLogin.setVisible(true);

        observable = new DeliveryService();
        observer = new EmployeeGUI();

        try {
            Reader reader = new Reader();
            reader.readSerizator();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
