import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    Model model = Model.getInstance();

    @FXML
    TextField itemId;
    @FXML
    TextArea inventoryDisplay;
    @FXML
    TextArea cartDisplay;

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


    public void addToCart(ActionEvent actionEvent) {
        String search = itemId.getText();
        if(search != null) {
            Product product = model.getInventory().findProduct(search);
            if (product != null) {
                model.addProductToCart(product);
                updateInventoryScreen();
                updateCartScreen();
            } else {
                System.out.println("Not found");
            }
        }
    }

    public void removeFromCart(ActionEvent actionEvent) {
        String search = itemId.getText();
        if(search != null) {
            Product product = model.getCart().findProduct(search);
            if (product != null) {
                model.removeProductFromCart(product);
                updateInventoryScreen();
                updateCartScreen();
            } else {
                System.out.println("Not found");
            }
        }
    }

    public void purchase(ActionEvent actionEvent) {
    }
}
