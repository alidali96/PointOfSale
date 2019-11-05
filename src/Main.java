import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static ArrayList<Phone> phones = new ArrayList<Phone>();
    public static ArrayList<Phone> cart  = new ArrayList<Phone>();

    public static void main(String[] args) {

        final double TAX = 0.13;
        double subtotal = 0;
        double taxTotal = 0;
        double total = 0;
        boolean browse = true;
        String customerInput;
        double customerPayment = 0;
        double change = 0;

        getShopItems();

        System.out.println("Welcome to Media Markt!");
        System.out.println("=======================");
        Scanner input = new Scanner(System.in);

        do {
            outputPhones();

            System.out.print("Please select a phone: ");
            customerInput = input.nextLine();

            for (Phone phone :
                    phones) {
                if (phone.getId().equals(customerInput)) {
                    subtotal += phone.getPrice();
                    taxTotal = subtotal * TAX;
                    total = subtotal + taxTotal;
                    System.out.println(String.format(" - Subtotal: $%.2f \n - Tax: $%.2f \n - Total: $%.2f", subtotal, taxTotal, total));
                    cart.add(phone);
                    phones.remove(phone);
                    break;
                }
            }

            System.out.println("Do you want to buy another item? (Y/N)");
            customerInput = input.nextLine();

            if (customerInput.toUpperCase().equals("N")) {
                browse = false;
                do {
                    System.out.print("Your Payment: ");
                    customerPayment = input.nextDouble();
                } while (customerPayment < total);
                change = customerPayment - total;
            }
        } while (browse);

        System.out.println("-------------------------");
        for (Phone phone :
                cart) {
            System.out.println(phone.getSummary());
        }
        System.out.println("-------------------------");

        System.out.println(String.format(" - Subtotal: $%.2f \n - Tax: $%.2f \n - Total: $%.2f \n - Paid: %.2f \n - Change: %.2f", subtotal, taxTotal, total, customerPayment, change));

        System.out.println("Date: " + new Date());
        System.out.println("Thank you for shopping by Media Markt!");

        updateShop();
    }

    private static void getShopItems() {
        try {
            String line;
            BufferedReader in = new BufferedReader(new FileReader(new File("data/products.txt")));
            while((line = in.readLine()) != null) {
                String[] details = line.split(",");
                phones.add(new Phone(details[0], details[1], details[2], details[3], Double.parseDouble(details[4])));
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateShop() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(new File("data/products.txt")));
            for (Phone phone :
                    phones) {
                out.write(phone.generateCSV());
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void outputPhones() {
        for (Phone phone :
                phones) {
            System.out.println(phone.getDetails());
        }
    }
}