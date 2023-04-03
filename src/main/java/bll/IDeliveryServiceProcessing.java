package bll;

import javax.swing.*;
import java.util.List;

/**
 * This interface contains the main operations that can be executed by the administrator and client
 *
 * @author Szekely Maria-Robertha
 * @see Actions
 */

public interface IDeliveryServiceProcessing {

    // administrator
    void importProducts();

    void addProduct(String title, String rating, String calories, String proteins, String fat, String sodium, String price);

    void deleteProduct(JComboBox<String> products);

    void modifyProduct(JComboBox<String> products, String title, String rating, String calories, String proteins, String fat, String sodium, String price);

    void btwSEHour(String start, String end);

    void productsOrderedMoreThan(String number);

    void clientsOrderedMoreThan(String number, String value);

    void specifiedDay(String day);

    void createComposite(CompositeProduct cp);

    // client
    void createOrder(String name, List<MenuItem> toOrder);

    void searchProduct(String filter, int fr);
}
