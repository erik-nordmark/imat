/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mat;

/**
 *
 * @author Joakim
 */
import java.util.List;
import javax.swing.ImageIcon;
import se.chalmers.ait.dat215.project.*;

public class IMatModel {
    private final IMatDataHandler handler;
    private Category selectedCategory;
    
    public enum Category{
        FAVOURITE, MEAT, FISH, DAIRY, BREAD, VEGETABLE,
        FROZEN, DRY, BEVERAGE, SWEET;
    }
    
    
    public IMatModel(){
        handler = IMatDataHandler.getInstance();
        selectedCategory = Category.MEAT;
    }
    
    /**
     * Set currently selected category to c
     * @param c - The category to switch to
     */
    public void selectCategory(Category c){
        selectedCategory = c;
    }
    
    /**
     * toString for lists of products.
     * DEBUG
     * @param l - List to stringify
     * @return - List as string
     */
    public String listToString(List<Product> l){
        String s = "";
        if (l == null || l.isEmpty()){
            return "No results";
        }
        for (Product p: l){
            s = s + p.getProductId() + ":";
            s = s + p.getName() + "\n";
            s = s + p.getPrice() + p.getUnit() + "\n\n";
        }
        return s;
    }
    /**
     * Search products without arguments
     * @return All products
     */
    public List<Product> search(){
        return handler.getProducts();
    }
    
    /**
     * Search products that contains a string
     * @param s - String to look for
     * @return All products which String s in their name
     */
    public List<Product> search(String s){
        return handler.findProducts(s);
    }
    
    /**
     * Search products that is in a certain category
     * @param c - Category to get
     * @return All products in category c
     */
    public List<Product> search(Category c){
        switch (c){
            case FAVOURITE:
                return handler.favorites();
            case MEAT:
                return handler.getProducts(ProductCategory.MEAT);
            case FISH:
                return handler.getProducts(ProductCategory.FISH);
            case DAIRY:
                return handler.getProducts(ProductCategory.DAIRIES);
            case BREAD:
                return handler.getProducts(ProductCategory.BREAD);
            case VEGETABLE:
                List<Product> veg = handler.getProducts(ProductCategory.VEGETABLE_FRUIT);
                veg.addAll(handler.getProducts(ProductCategory.ROOT_VEGETABLE));
                veg.addAll(handler.getProducts(ProductCategory.CITRUS_FRUIT));
                veg.addAll(handler.getProducts(ProductCategory.POD));
                veg.addAll(handler.getProducts(ProductCategory.HERB));
                veg.addAll(handler.getProducts(ProductCategory.MELONS));
                veg.addAll(handler.getProducts(ProductCategory.BERRY));
                
                return veg;
            case FROZEN:
                return handler.getProducts();
            case DRY:
                List<Product> dry = handler.getProducts(ProductCategory.PASTA);
                dry.addAll(handler.getProducts(ProductCategory.POTATO_RICE));
                dry.addAll(handler.getProducts(ProductCategory.NUTS_AND_SEEDS));
                dry.addAll(handler.getProducts(ProductCategory.FLOUR_SUGAR_SALT));
                
                return dry;
            case BEVERAGE:
                return handler.getProducts(ProductCategory.COLD_DRINKS);
            case SWEET: 
                return handler.getProducts(ProductCategory.SWEET);
        }
        return null;
    }
    
    /**
     * Get the image associated with the specified product.
     * @param p the product
     * @param width the widht of the image
     * @param height the height of the image
     * @return the image of the product
     */
    public ImageIcon getProductImage(Product p, int width, int height){
        return handler.getImageIcon(p, width, height);
    }
    
    public ShoppingCart getShoppingCart() {
        return handler.getShoppingCart();
    }
    
    public void addToCart(ShoppingItem item){
        boolean found = false;
        
        if (!handler.getShoppingCart().getItems().isEmpty()){
            List<ShoppingItem> si = handler.getShoppingCart().getItems();
            for (ShoppingItem s: si) {
                if ( s.getProduct().equals(item.getProduct()) ){
                    s.setAmount(s.getAmount() + item.getAmount());
                    found = true;
                    break;
                }
            }
        }
        
        if (!found){
            handler.getShoppingCart().addItem(item);
        }
        
    }
    
    public void removeFromCart(Product p){
        if (!handler.getShoppingCart().getItems().isEmpty()){
            List<ShoppingItem> si = handler.getShoppingCart().getItems();
            for (ShoppingItem s: si) {
                if ( s.getProduct().equals(p) ){
                    handler.getShoppingCart().removeItem(s);
                    break;
                }
            }
        }
    }
    
    public void changeAmount(ShoppingItem s){
        List<ShoppingItem> l = handler.getShoppingCart().getItems();
        for (ShoppingItem item: l){
            if ( item.getProduct().equals(s.getProduct()) ){
                item.setAmount(s.getAmount());
            }
        }
    }
    
    public void changeAmount(Product p, double amount){
        changeAmount(new ShoppingItem(p,amount));
    }
    
    public void clearCart(){
        handler.getShoppingCart().clear();
    }
    
    public double getTotalPrice(){
        return handler.getShoppingCart().getTotal();
    }
    
    public CreditCard getCreditCard(){
        return handler.getCreditCard();
    }
    
    public Customer getCustomer(){
        return handler.getCustomer();
    }
    
    public List<Order> getOrders(){
        return handler.getOrders();
    }
    
}
