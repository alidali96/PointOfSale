import java.util.Date;

public class Sale {

    private final double TAX = 0.13;
    private double subtotal;
    private double taxTotal;
    private double total;
    private double payment;
    private double change;

    public Sale() {
        this.subtotal = 0;
        this.taxTotal = 0;
        this.total = 0;
        this.payment = 0;
        this.change = 0;
    }


    public void addProduct(Product product) {
        subtotal += product.getPrice();
        calculateTotal();
    }

    public void removeProduct(Product product) {
        subtotal -= product.getPrice();
        calculateTotal();
    }

    private void calculateTotal() {
        taxTotal = subtotal * TAX;
        total = subtotal + taxTotal;
    }

    public boolean makeTransaction(double payment) {
        if(payment >= total) {
            this.payment = payment;
            change = payment - total;
            return true;
        }
        return false;
    }


    public String getSaleDetail() {
        return String.format("- Subtotal: $%.2f \n- Tax: $%.2f \n- Total: $%.2f \n- Paid: $%.2f \n- Change: $%.2f \n\nDate: %s \nThank you for shopping by MediaMarkt!", subtotal, taxTotal, total, payment, change, new Date());
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getTaxTotal() {
        return taxTotal;
    }

    public double getTotal() {
        return total;
    }

    public double getPayment() {
        return payment;
    }

    public double getChange() {
        return change;
    }

}
