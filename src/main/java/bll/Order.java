package bll;

import java.io.Serializable;
import java.util.List;

/**
 * This class represents the main entity we'll be using to create orders or reports
 * It contains the methods that must be implemented into its child
 *
 * @author Szekely Maria-Robertha
 * @see Actions
 * <p>
 * It also implements Serializable to make Order to be serialized
 */

public class Order implements Serializable {

    private String clientUsername;
    private String orderData;
    private String orderTime;
    private List<MenuItem> menuItems;
    private float totalPrice;

    public Order(String clientUsername, String orderData, String orderTime, List<MenuItem> menuItems) {
        this.clientUsername = clientUsername;
        this.orderData = orderData;
        this.orderTime = orderTime;
        this.menuItems = menuItems;
        this.totalPrice = computePrice();
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    @Override
    public int hashCode() {
        return orderData.hashCode() + orderTime.hashCode();
    }

    public String toString() {
        return this.clientUsername + " " + this.orderData + " " + this.orderTime + " " + this.menuItems;
    }

    public float computePrice() {
        float price = 0;
        for (MenuItem menuItem : menuItems) {
            price += menuItem.computePrice();
        }
        return price;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public String getOrderData() {
        return orderData;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public float getTotalPrice() {
        return totalPrice;
    }
}
