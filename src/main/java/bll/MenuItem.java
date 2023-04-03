package bll;

import java.io.Serializable;

/**
 * This class represents a parent for class BaseProduct and CompositeProduct
 * It contains the methods that must be implemented into its child
 *
 * @author Szekely Maria-Robertha
 * @see BaseProduct
 * @see CompositeProduct
 * <p>
 * It also implements Serializable to make Order to be serialized
 */

public abstract class MenuItem implements Serializable {

    public abstract String getTitle();

    public abstract float getRating();

    public abstract float getCalories();

    public abstract float getProteins();

    public abstract float getFats();

    public abstract float getSodium();

    public abstract float computePrice();

    public abstract void setTitle(String title);

    public abstract void setRating(Float rating);

    public abstract void setCalories(Float calories);

    public abstract void setProteins(Float proteins);

    public abstract void setFats(Float fats);

    public abstract void setSodium(Float sodium);

    public abstract void setPrice(Float price);
}
