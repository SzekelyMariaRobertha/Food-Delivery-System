package dal;

import bll.MenuItem;
import bll.Order;

import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * This class is used for the most part to write into a file when creating an order
 * It also helps to serialize the orders and write it into a file
 *
 * @author Szekely Maria-Robertha
 */

public class Writer {

    public Writer() {

    }

    /**
     * The purpose of this method is to generate a bill (.txt format) which contains all details of an order
     *
     * @param order represents the order that was performed and must be concreted through a bill
     */

    public void createBill(Order order) {

        String filename = "src\\main\\resources\\" + order.hashCode() + ".txt";
        try (FileWriter fileWriter = new FileWriter(filename, true)) {
            fileWriter.append("HASHCODE: " + order.hashCode() + "\n");
            fileWriter.append("DATE: " + order.getOrderData() + "\n");
            fileWriter.append("TIME: " + order.getOrderTime() + "\n");
            fileWriter.append("CLIENT: " + order.getClientUsername() + "\n");
            fileWriter.append("PRODUCTS: " + "\n");
            for (MenuItem menuItem : order.getMenuItems()) {
                fileWriter.append(menuItem + "\n");
            }
            fileWriter.append("TOTAL PRICE: " + order.getTotalPrice() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The purpose of this method is to write orders into a file using serialization
     *
     * @param map represents the hashtable which contain all orders performed
     */

    public void writeSerizator(HashMap<Order, List<MenuItem>> map) {

        try (FileOutputStream fs = new FileOutputStream("src\\main\\resources\\DeliveryService.txt")) {

            ObjectOutputStream os = new ObjectOutputStream(fs);

            os.writeObject(map);

            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
