package ShoppingCart;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * the shop cart class
 */
public class ShopCart {

    private final List<Product> cartProducts;
    private double totalCart;

    List<Product> filteredProducts = new ArrayList<Product>();

    JSONObject cartProductsJson = new JSONObject();

    private static FileWriter file;

    /**
     * the constructor
     * @param cartProducts
     * @param totalCart
     */
    public ShopCart(List<Product> cartProducts, double totalCart){
        this.cartProducts = cartProducts;
        this.totalCart = totalCart;
    }

    /**
     * adding a product to the initial products list
     * @param product
     * @return
     */
    public List<Product> addProductToCart(Product product){
        // verify if the product to add is already in the list or not, if yes increase the quantity else add it
        Product productToAdd =
                cartProducts.stream()
                        .filter(prd -> prd.productId.equals(product.productId))
                        .findFirst()
                        .map(prd -> increaseQuantity(prd,product.productQuantity))
                        .orElse(product);

        if(cartProducts.stream().anyMatch(prd -> prd.productId.equals(productToAdd.productId))){

            filteredProducts =
                    cartProducts.stream()
                            .filter(prd -> !prd.productId.equals(productToAdd.productId))
                            .collect(Collectors.toList());

        }

        filteredProducts.add(productToAdd);

        cartProducts.clear();
        cartProducts.addAll(filteredProducts);

        return cartProducts;
    }

    private Product increaseQuantity(Product prd, int quantity) {
        return new Product(prd.productId, prd.productName, prd.productQuantity + quantity, prd.productPrice);
    }


    /**
     * print the details of the shop cart with the total price
     */
    public void printShopCartResult(){
        double totalCart = 0;
        System.out.println("Product            Quantity           Price                Total ");
        System.out.println("--------------------------------------------------------------------------------");
        cartProducts.forEach(this::printRst);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Total                                                          " + this.totalCart);

        try {
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter("C:/temp/cartProducts.json");
            file.write(cartProductsJson.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printRst(Product prd){

        JSONObject jsonProduct = new JSONObject();
        jsonProduct.add("productName",prd.productName);
        jsonProduct.add("productId",prd.productId);
        jsonProduct.add("productQuantity",prd.productQuantity);
        jsonProduct.add("productPrice",prd.productPrice);
        jsonProduct.add("productTotalPrice",prd.productPrice*prd.productQuantity);

        cartProductsJson.add(jsonProduct);

        System.out.println(prd.productName +"               "+ prd.productQuantity +"                " + prd.productPrice +"                     "+ prd.productQuantity*prd.productPrice);
        this.totalCart += prd.productQuantity*prd.productPrice;
    }
}
