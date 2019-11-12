package controller;

import app.Inventory;
import app.Phone;
import app.Product;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import model.Model;
import model.Model_2;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller_2 implements Initializable {

    Model_2 model = Model_2.getInstance();

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
    TableView inventory;
    @FXML
    TableColumn<Product, String> columnID;
    @FXML
    TableColumn<Product, String> columnBrand;
    @FXML
    TableColumn<Product, String> columnModel;
    @FXML
    TableColumn<Product, Double> columnPrice;


    @FXML
    TextArea cartDisplay;
    @FXML
    TextArea saleDisplay;
    @FXML
    Label amountNotEnough;
    @FXML
    Label amountDue;


    ObservableList<Product> data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateInventoryScreen();

        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        columnModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));


        data = model.getDataList();
        inventory.setEditable(true);

        inventory.setItems(data);
    }

    private void updateInventoryScreen() {
//        inventoryDisplay.setText(model.getInventory().getAllProducts());
        data.removeAll();
        data = model.getDataList();
        inventory.setItems(data);
//        inventory.refresh();
    }

    private void updateCartScreen() {
        cartDisplay.setText(model.getCart().getAllProducts());
    }

    private void updateSaleScreen() {
        saleDisplay.setText(model.getCart().getSummary() + "-----------------------\n" + model.getSale().getSaleDetail());
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
//                updateCartScreen();
            } else {
                System.out.println("Not found");
            }
        }
        itemId.setText("");
    }

    public void purchase(ActionEvent actionEvent) {
        // TODO add all buttons to a pane and disable it instead
        if (!model.getCart().isCartEmpty()) {
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
        try {
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
