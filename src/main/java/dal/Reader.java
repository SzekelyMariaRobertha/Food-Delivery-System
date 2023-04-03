package dal;

import bll.MenuItem;
import bll.Order;
import start.Start;

import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * This class is used for the most part to read from file the users of the application
 * It also helps to deserialize data from a file which contains the orders
 *
 * @author Szekely Maria-Robertha
 */

public class Reader {

    private String line = "init";
    private boolean ok = false;

    public Reader() throws IOException {

    }

    /**
     * This method is getting the file, read it line to line and calls the method toCheck to see if the file contain the introduced data
     *
     * @param username represents the user username provided from the interface
     * @param password represents the user password provided from the interface
     * @return the role of the user (client, admin, employee)
     */

    public String checkUser(String username, String password) {

        String res = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/UP.txt"));
            while (line != null && !ok) {
                line = bufferedReader.readLine();
                if (line != null) {
                    res = toCheck(username, password);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public String toCheck(String username, String password) {
        CaesarCipher caesarCipher = new CaesarCipher();
        String[] credentials;
        credentials = line.split(",");
        if (username.equals(credentials[0]) && (caesarCipher.cipher(password, 1)).equals(credentials[1])) {
            ok = true;
            return credentials[2];
        } else {
            return null;
        }
    }

    /**
     * This method is using a file which contains all orders and deserialize the data from there, put it into a map in order to use the information that the file contain
     * The method is called when the application is run (it loads the information when the application starts)
     */

    public void readSerizator() {

        File file = new File("src\\main\\resources\\DeliveryService.txt");
        if (file.exists()) {
            try (FileInputStream fi = new FileInputStream("src\\main\\resources\\DeliveryService.txt")) {

                ObjectInputStream os = new ObjectInputStream(fi);

                Start.hashMap = (HashMap<Order, List<MenuItem>>) os.readObject();
                //System.out.println(Start.hashMap);

                Start.observer.setOrder(Start.hashMap);
                os.close();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
