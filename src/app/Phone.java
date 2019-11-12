package app;

public class Phone extends Product implements ProductDetails {

    private Type type;
    private Brand brand;
    private String model;

    public Phone(String id, Brand brand, String model, String condition, double price) {
        super(id, price, Type.PHONE, condition);
        this.brand = brand;
        this.model = model;
        this.type = Type.PHONE;
    }

    @Override
    public String getDetails() {
        return String.format("ID: %s - Type: %s - Brand: %s - Model.Model: %s - Price: $%.2f", getId(), type, brand, model, getPrice());
    }

    @Override
    public String getSummary() {
        return String.format("%s %s $%.2f", brand, model, getPrice());
    }

    @Override
    public String generateCSV() {
        return String.format("%s,%s,%s,%s,%.2f%n", getId(), type, brand, model, getPrice());
    }


    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return String.format("ID: %s - Type: %s - Brand: %s - Model.Model: %s - Price: $%.2f", getId(), type, brand, model, getPrice());
    }

}
