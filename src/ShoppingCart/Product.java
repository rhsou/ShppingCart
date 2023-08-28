package ShoppingCart;

/**
 * a class representing the product
 */

public class Product {

    public String productId;
    public String productName;
    public int productQuantity;
    public double productPrice;


    public Product(String productId, String productName, int productQuantity, double productPrice)
    {
        this.productId=productId;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
    }
}
