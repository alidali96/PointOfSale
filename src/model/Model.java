package model;

import app.Cart;
import app.Inventory;
import app.Product;
import app.Sale;

public class Model {

    private static Model model;
    private Inventory inventory;
    private Cart cart;
    private Sale sale;


    public static Model getInstance() {
        if(model == null)
            model = new Model();
        return model;
    }

    private Model() {
        this.inventory = new Inventory();
        this.cart = new Cart();
        this.sale = new Sale();
    }

    public void addProductToCart(Product product) {
        getInventory().removeProduct(product);
        getCart().addToCart(product);
        sale.addProduct(product);
    }

    public void removeProductFromCart(Product product) {
        getInventory().addProduct(product);
        getCart().removeFromCart(product);
        sale.removeProduct(product);
    }

    public Inventory getInventory() {
        return inventory;

    }

    public Cart getCart() {
        return cart;
    }

    public Sale getSale() {
        return sale;
    }

}
