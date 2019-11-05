import java.io.*;
import java.util.ArrayList;

public class Inventory {

    // We need a list for every type of products
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
                        phones.add(new Phone(details[0], Brand.toBrand(details[2]), details[3], Double.parseDouble(details[4])));
                }

            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateShop(ArrayList<Product> products) {
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

    private static String getAllProducts(ArrayList<Product> products) {
        String result = "";
        for (Product product :
                products) {
            result += product.getDetails() + "\n";
        }
        return result;
    }
}
