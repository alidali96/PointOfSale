import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Phone {

    private String id;
    private String type;
    private String brand;
    private String model;
    private double price;

    public Phone(String id, String type, String brand, String model, double price) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public String getDetails() {
        return String.format("ID: %s - Type: %s - Brand: %s - Model: %s - Price: $%.2f", id, type, brand, model, price);
    }

    public String getSummary() {
        return String.format("%s %s $%.2f", brand, model, price);
    }

    public String generateCSV() {
        return String.format("%s,%s,%s,%s,%.2f%n", id, type, brand, model, price);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
