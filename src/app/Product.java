package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Product implements ProductDetails {

    private String id;
    private double price;
    private Type type;
    private String condition;

    private static ArrayList<String> conditions = new ArrayList<>();

    public Product(String id, double price, Type type, String condition) {
        this.id = id;
        this.price = price;
        this.type = type;
        this.condition = condition;
        addToConditions(condition);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String getDetails() {
        return null;
    }

    @Override
    public String getSummary() {
        return null;
    }

    @Override
    public String generateCSV() {
        return null;
    }

    private void addToConditions(String condition) {
        if(!conditions.contains(condition))
            conditions.add(condition);
    }

    public static ObservableList getConditionsList() {
        return FXCollections.observableArrayList(conditions);
    }
}
