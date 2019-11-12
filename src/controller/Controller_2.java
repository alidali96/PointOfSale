package controller;

import app.Phone;
import app.Product;
import javafx.beans.property.ReadOnlyObjectPropertyBase;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
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
    TableColumn<Product, String> inventoryID;
    @FXML
    TableColumn<Product, String> inventoryBrand;
    @FXML
    TableColumn<Product, String> inventoryModel;
    @FXML
    TableColumn<Product, Double> inventoryPrice;
    @FXML
    TableColumn<Product, Product> inventoryCondition;
    @FXML
    TableColumn<Product, Product> inventoryDetails;


    @FXML
    TableView cart;
    @FXML
    TableColumn<Product, String> cartID;
    @FXML
    TableColumn<Product, String> cartBrand;
    @FXML
    TableColumn<Product, String> cartModel;
    @FXML
    TableColumn<Product, Double> cartPrice;
    @FXML
    TableColumn<Product, Product> cartCondition;
    @FXML
    TableColumn<Product, Product> cartDetails;

    @FXML
    TextArea saleDisplay;
    @FXML
    Label amountNotEnough;
    @FXML
    Label amountDue;


    ObservableList<Product> inventoryItems;
    ObservableList<Product> cartItems;
    String search;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        updateInventoryScreen();

        inventoryID.setCellValueFactory(new PropertyValueFactory<>("id"));
        inventoryBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        inventoryModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        inventoryPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        // ComboBox
        inventoryCondition.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        inventoryCondition.setCellFactory(param -> new TableCell<Product, Product>() {
            ComboBox condition = new ComboBox();
            ObservableList<String> conditions = FXCollections.observableArrayList(Product.getConditionsList());

            @Override
            public void updateItem(Product product, boolean empty) {
                if (empty) {
                    setGraphic(null);
                    return;
                }

                condition.setItems(conditions);
                condition.setValue(product.getCondition());

                condition.setOnAction(e -> product.setCondition(condition.getValue().toString()));
                setGraphic(condition);
            }

        });


        // Button
        inventoryDetails.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        inventoryDetails.setCellFactory(param -> new TableCell<Product, Product>() {
            Button button = new Button("Details");

            @Override
            protected void updateItem(Product product, boolean empty) {
                super.updateItem(product, empty);

                if (product == null) {
                    setGraphic(null);
                    return;
                }

                button.setOnAction(e -> System.out.println(product));
                setGraphic(button);
            }
        });


        inventoryItems = model.getInventoryItems();
        inventory.setEditable(true);

        inventory.setItems(inventoryItems);


        cartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        cartBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        cartModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        cartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));


        // ComboBox
        cartCondition.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        cartCondition.setCellFactory(param -> new TableCell<Product, Product>() {
            ComboBox condition = new ComboBox();
            ObservableList<String> conditions = FXCollections.observableArrayList(Product.getConditionsList());

            @Override
            public void updateItem(Product product, boolean empty) {
                if (empty) {
                    setGraphic(null);
                    return;
                }

                condition.setItems(conditions);
                condition.setValue(product.getCondition());

                condition.setOnAction(e -> product.setCondition(condition.getValue().toString()));
                setGraphic(condition);
            }

        });


        // Button
        cartDetails.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        cartDetails.setCellFactory(param -> new TableCell<Product, Product>() {
            Button button = new Button("Details");

            @Override
            protected void updateItem(Product product, boolean empty) {
                super.updateItem(product, empty);

                if (empty) {
                    setGraphic(null);
                    return;
                }

                button.setOnAction(e -> System.out.println(product));
                setGraphic(button);
            }
        });


        cartItems = model.getCartItems();
        cart.setEditable(true);

        cart.setItems(cartItems);
    }

    private void updateInventoryScreen() {
        inventoryItems.removeAll();
        inventoryItems = model.getInventoryItems();
        inventory.setItems(inventoryItems);
    }

    private void updateCartScreen() {
        cartItems.removeAll();
        cartItems = model.getCartItems();
        cart.setItems(cartItems);
    }

    private void updateSaleScreen() {
        saleDisplay.setText(model.getCart().getSummary() + "-----------------------\n" + model.getSale().getSaleDetail());
    }


    public void addToCart(ActionEvent actionEvent) {
        if (!itemId.getText().isEmpty()) {
            search = itemId.getText();
        } else {
            if (inventory.getSelectionModel().getSelectedItem() != null)
                search = ((Phone) inventory.getSelectionModel().getSelectedItem()).getId();
        }

        Product product = model.getInventory().findProduct(search);
        if (product != null) {
            model.addProductToCart(product);
            updateInventoryScreen();
            updateCartScreen();
        } else {
            System.out.println("Not found");
        }
        search = null;
        itemId.setText("");
    }

    public void removeFromCart(ActionEvent actionEvent) {
        if (!itemId.getText().isEmpty()) {
            search = itemId.getText();
        } else {
            if (cart.getSelectionModel().getSelectedItem() != null)
                search = ((Phone) cart.getSelectionModel().getSelectedItem()).getId();
        }
        Product product = model.getCart().findProduct(search);
        if (product != null) {
            model.removeProductFromCart(product);
            updateInventoryScreen();
            updateCartScreen();
        } else {
            System.out.println("Not found");
        }
        search = null;
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
