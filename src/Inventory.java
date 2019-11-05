import java.io.*;
import java.util.ArrayList;

public class Inventory {

    // We need a list for every type of products
    public static ArrayList<Product> products = new ArrayList<Product>();
    public static ArrayList<Phone> phones = new ArrayList<Phone>();


    // Populate all lists in inventory
    public Inventory() {
        try {
            String line;
            BufferedReader in = new BufferedReader(new FileReader(new File("data/products.txt")));
            while ((line = in.readLine()) != null) {
                String[] details = line.split(",");

                // Whenever we add a new type we can will have to add another case
                switch (Type.toType(details[1])) {
                    case PHONE:
                        products.add(new Phone(details[0], Brand.toBrand(details[2]), details[3], Double.parseDouble(details[4])));
                }

            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void updateInventory(ArrayList<Product> products) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(new File("data/products.txt")));
            for (Product product :
                    products) {
                out.write(product.generateCSV());
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Product findProduct(String id) {
        for(Product product: products) {
            if(product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public String getAllProducts() {
        String result = "";
        for (Product product :
                products) {
            result += product.getDetails() + "\n";
        }
        return result;
    }
}
