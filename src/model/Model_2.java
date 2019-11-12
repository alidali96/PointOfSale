package model;

import app.Cart;
import app.Inventory;
import app.Product;
import app.Sale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model_2 {

    private static Model_2 model;
    private Inventory inventory;
    private Cart cart;
    private Sale sale;


    public static Model_2 getInstance() {
        if(model == null)
            model = new Model_2();
        return model;
    }

    private Model_2() {
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

//
    public ObservableList getDataList() {
        return FXCollections.observableArrayList(Inventory.products);
    }

}
