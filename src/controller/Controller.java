package controller;

import model.Model;
import app.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    Model model = Model.getInstance();

    @FXML
    Button addItemButton;
    @FXML
    Button removeItemButton;
    @FXML
    Button purchaseButton;
    @FXML
    Pane paymentPane;

    @FXML
    TextField itemId;
    @FXML
    TextField customerPayment;

    @FXML
    TextArea inventoryDisplay;
    @FXML
    TextArea cartDisplay;
    @FXML
    TextArea saleDisplay;
    @FXML
    Label amountNotEnough;
    @FXML
    Label amountDue;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateInventoryScreen();
    }

    private void updateInventoryScreen() {
        inventoryDisplay.setText(model.getInventory().getAllProducts());
    }

    private void updateCartScreen() {
        cartDisplay.setText(model.getCart().getAllProducts());
    }

    private void updateSaleScreen() {
        saleDisplay.setText(model.getCart().getSummary() + "-----------------------\n" +  model.getSale().getSaleDetail());
    }


    public void addToCart(ActionEvent actionEvent) {
        String search = itemId.getText();
        if (search != null) {
            Product product = model.getInventory().findProduct(search);
            if (product != null) {
                model.addProductToCart(product);
                updateInventoryScreen();
                updateCartScreen();
            } else {
                System.out.println("Not found");
            }
        }
        itemId.setText("");
    }

    public void removeFromCart(ActionEvent actionEvent) {
        String search = itemId.getText();
        if (search != null) {
            Product product = model.getCart().findProduct(search);
            if (product != null) {
                model.removeProductFromCart(product);
                updateInventoryScreen();
                updateCartScreen();
            } else {
                System.out.println("Not found");
            }
        }
        itemId.setText("");
    }

    public void purchase(ActionEvent actionEvent) {
        // TODO add all buttons to a pane and disable it instead
        if(!model.getCart().isCartEmpty()) {
            itemId.setDisable(true);
            addItemButton.setDisable(true);
            removeItemButton.setDisable(true);
            purchaseButton.setDisable(true);

            amountDue.setText(String.format("Total: $%.2f", model.getSale().getTotal()));

            paymentPane.setVisible(true);
        } else {
            System.out.println("Cart is empty");
        }
    }

    public void payAmount(ActionEvent actionEvent) {
        try{
            double payment = Double.parseDouble(customerPayment.getText());
            if (model.getSale().makeTransaction(payment)) {
                saleDisplay.setVisible(true);
                amountNotEnough.setVisible(false);
                paymentPane.setDisable(true);
                updateSaleScreen();
                model.getInventory().updateInventory();
            } else {
                amountNotEnough.setVisible(true);
            }
        } catch (Exception e) {
            System.out.println("Not valid payment");
        }
    }
}
