package app;

import java.util.ArrayList;

public class Cart {

    public static ArrayList<Product> products;


    public Cart() {
        this.products = new ArrayList<>();
    }


    public void addToCart(Product product) {
        products.add(product);
    }

    public void removeFromCart(Product product) {
        if(products.contains(product))
            products.remove(product);
    }


    public Product findProduct(String id) {
        for(Product product: products) {
            if(product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public String getAllProducts() {
        String result = "";
        for (Product product :
                products) {
            result += product.getDetails() + "\n";
        }
        return result;
    }

    public String getSummary() {
        String result = "";
        for (Product product :
                products) {
            result += product.getSummary() + "\n";
        }
        return result;
    }

    public boolean isCartEmpty() {
        return this.products.isEmpty();
    }
}
