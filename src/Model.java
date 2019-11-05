import java.util.ArrayList;

public class Model {

    private static Model model;
    private Inventory inventory;
    private Cart cart;

    private final double TAX = 0.13;
    private double subtotal = 0;
    private double taxTotal = 0;
    private double total = 0;
    private double customerPayment = 0;
    private double change = 0;


    public static Model getInstance() {
        if(model == null)
            model = new Model();
        return model;
    }

    private Model() {
        this.inventory = new Inventory();
        this.cart = new Cart();
    }

    public Inventory getInventory() {
        return inventory;

    }

    public void addProductToCart(Product product) {
        getInventory().removeProduct(product);
        getCart().addToCart(product);
    }

    public void removeProductFromCart(Product product) {
        getInventory().addProduct(product);
        getCart().removeFromCart(product);
    }

//    public Product addToCart(String id) {
//        Product product = getInventory().findProduct(id);
//        if(product != null) {
//            getInventory().removeProduct(product);
//            cart.addToCart(product);
//
//            subtotal += product.getPrice();
//            taxTotal = subtotal * TAX;
//            total = subtotal + taxTotal;
//
//            return product;
//        }
//        return null;
//    }
//
//    public Product removeFromCart(String id) {
//        Product product = cart.get(id);
//        if (product != null) {
//            getInventory().addProduct(product);
//            cart.remove(product);
//
//            subtotal -= product.getPrice();
//            taxTotal = subtotal * TAX;
//            total = subtotal + taxTotal;
//            return product;
//        }
//        return null;
//    }


    public double getTAX() {
        return TAX;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getTaxTotal() {
        return taxTotal;
    }

    public double getTotal() {
        return total;
    }

    public double getCustomerPayment() {
        return customerPayment;
    }

    public double getChange() {
        return change;
    }

    public Cart getCart() {
        return cart;
    }
}
