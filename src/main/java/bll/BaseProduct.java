package bll;

/**
 * BaseProduct is the main entity we'll be using to do different operations on the delivery app
 *
 * @author Szekely Maria-Robertha
 */

public class BaseProduct extends MenuItem {

    private String title;
    private float rating;
    private float calories;
    private float proteins;
    private float fats;
    private float sodium;
    private float price;

    public BaseProduct(String title, float rating, float calories, float proteins, float fats, float sodium, float price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.sodium = sodium;
        this.price = price;

    }

    public String getTitle() {
        return title;
    }

    public float getRating() {
        return rating;
    }

    public float getCalories() {
        return calories;
    }

    public float getProteins() {
        return proteins;
    }

    public float getFats() {
        return fats;
    }

    public float getSodium() {
        return sodium;
    }

    public float computePrice() {
        return price;
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

    public String toString() {
        return this.title + " " + this.rating + " " + this.calories + " " + this.proteins + " " + this.fats + " " + this.sodium + " " + this.price;
    }
}
