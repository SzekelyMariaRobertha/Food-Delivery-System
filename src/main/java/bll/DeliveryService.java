package bll;

import dal.Writer;

import java.util.*;

/**
 * This class uses Design Pattern to notify each time an order has been performed
 *
 * @author Szekely Maria-Robertha
 * @see presentation.EmployeeGUI
 * <p>
 * This class also used Design by contract: preconditions, postconditions and invariants
 * @see "https://john.cs.olemiss.edu/~hcc/softArch/notes/iContract/OW_Berlin99_web.pdf"
 * @see "https://docs.oracle.com/javase/8/docs/technotes/guides/language/assert.html"
 */

/*
 * @invariant map.size() > 0
 */

public class DeliveryService extends Observable {

    public static HashMap<Order, List<MenuItem>> map;

    /**
     * @pre return map.values().size > 0
     */

    /**
     * The purpose of the method is to notify the employee when a new order was created
     * @param map represents the map where the new order should be added
     */

    public void setOrder(HashMap<Order, List<MenuItem>> map) {
        this.map = map;
        wellFormed(); // pre-condition through method wellFormed
        setChanged();
        notifyObservers(map);
        wellFormed(); // post-condition through method wellFormed

        Writer writer = new Writer();
        writer.writeSerizator(map);
    }

    /**
     * @post wellFormed() == true
     */

    /**
     * The purpose of the method is to check if the order meets the requirements
     */
    public void wellFormed() {
        assert (map.values().isEmpty());
    }
}
