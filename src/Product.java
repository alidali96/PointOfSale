public class Product implements ProductDetails {

    private String id;
    private double price;
    private Type type;

    public Product(String id, double price, Type type) {
        this.id = id;
        this.price = price;
        this.type = type;
    }

//    public abstract String getDetails();
//
//    public abstract String getSummary();
//
//    public abstract String generateCSV();


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
}
