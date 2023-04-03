package bll;

import java.util.List;

/**
 * CompositeProduct is the second main entity we'll be using to do different operations on the delivery app composed of base products
 *
 * @author Szekely Maria-Robertha
 * @see BaseProduct
 * <p>
 * The getters of this class compute the actual value of rating, calories, etc. based on the list of baseproducts\
 */

public class CompositeProduct extends MenuItem {

    private List<BaseProduct> baseProducts;

    private String title;
    private float rating;
    private float calories;
    private float proteins;
    private float fats;
    private float sodium;
    private float price;

    public CompositeProduct(List<BaseProduct> baseProducts, String title) {
        this.baseProducts = baseProducts;
        this.title = title;
        this.rating = getRating();
        this.calories = getCalories();
        this.proteins = getProteins();
        this.fats = getFats();
        this.sodium = getSodium();
        this.price = computePrice();
    }

    public String getTitle() {
        return title;
    }

    public float getRating() {
        float r = 0;
        for (BaseProduct b : baseProducts) {
            r += b.getRating();
        }
        return r / baseProducts.size();
    }

    public float getCalories() {
        float c = 0;
        for (BaseProduct b : baseProducts) {
            c += b.getCalories();
        }
        return c;
    }

    public float getProteins() {
        float p = 0;
        for (BaseProduct b : baseProducts) {
            p += b.getProteins();
        }
        return p;
    }

    public float getFats() {
        float f = 0;
        for (BaseProduct b : baseProducts) {
            f += b.getFats();
        }
        return f;
    }

    public float getSodium() {
        float s = 0;
        for (BaseProduct b : baseProducts) {
            s += b.getSodium();
        }
        return s;
    }

    public float computePrice() {
        float pr = 0;
        for (BaseProduct b : baseProducts) {
            pr += b.computePrice();
        }
        return pr;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setRating(Float rating) {
        this.rating = rating;
    }

    @Override
    public void setCalories(Float calories) {
        this.calories = calories;
    }

    @Override
    public void setProteins(Float proteins) {
        this.proteins = proteins;
    }

    @Override
    public void setFats(Float fats) {
        this.fats = fats;
    }

    @Override
    public void setSodium(Float sodium) {
        this.sodium = sodium;
    }

    @Override
    public void setPrice(Float price) {
        this.price = price;
    }

    public List<BaseProduct> getBaseProducts() {
        return baseProducts;
    }

    public String toString() {
        StringBuilder s = null;
        for (BaseProduct b : baseProducts) {
            s.append(b.getTitle());
        }
        return s.toString();
    }
}
