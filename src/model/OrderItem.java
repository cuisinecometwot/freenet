package model;

public class OrderItem {
    private int subCost;
    private int quantity;
    private Product product;

    public OrderItem(Product product, int quantity)
    {
        this.product = product;
        this.quantity = quantity;
    }
    public Product productObject () {
        return product;
    }

    public int getCost () {
        subCost = quantity * product.getCost();
        return subCost;
    }

    public int getQuantity() {
        return this.quantity;
    }
    public void setQuantity (int quantity) {
        this.quantity = quantity;
    }
}
