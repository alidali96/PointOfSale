import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main extends Application {
    public static ArrayList<Phone> phones = new ArrayList<Phone>();
    public static ArrayList<Phone> cart  = new ArrayList<Phone>();

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Point Of Sale");
        primaryStage.setResizable(false);
        primaryStage.show();
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