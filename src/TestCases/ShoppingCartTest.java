package TestCases;

import ShoppingCart.Product;
import ShoppingCart.ShopCart;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartTest {


    Product productCaffe = new Product("1", "caffé", 1, 2.5);
    Product productLait = new Product("2", "lait", 2, 1);

    List<Product> products = new ArrayList<Product>();

    double totalCart = 0;

    /**
     * test of the case 1: a shop cart with different products without any additional prouct add
     */
    @Test
    public void shoppingCartNoDoubleProduct(){
        products.clear();
        totalCart = 0;

        products.add(productCaffe);
        products.add(productLait);
        ShopCart cart = new ShopCart(products, totalCart);

        cart.printShopCartResult();

    }

    /**
     * Test case 2: a shop cart with repeated products
     */
    @Test
    public void shoppingCartWithDoubleProduct(){

        products.clear();
        totalCart = 0;

        products.add(productCaffe);
        products.add(productLait);

        ShopCart cart = new ShopCart(products, totalCart);

        cart.addProductToCart(new Product("1", "caffé", 3, 2.5));
        cart.addProductToCart(new Product("3", "fromage", 5, 2.8));
        cart.addProductToCart(new Product("2", "lait", 9, 1));
        cart.printShopCartResult();

    }
}
