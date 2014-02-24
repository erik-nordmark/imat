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
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import se.chalmers.ait.dat215.project.*;

public class IMatModel {
    private final IMatDataHandler handler;
    private Category selectedCategory;
    
    public enum Category{
        FAVOURITE, MEAT, FISH, DAIRY, BREAD, VEGETABLE,
        FRUIT, DRY, BEVERAGE, SWEET;
    }
    
    
    public IMatModel(){
        this(Category.MEAT);
    }
    
    public IMatModel(Category c){
        handler = IMatDataHandler.getInstance();
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
    public List<ProductPanel> search(){
        return productsToPanels(handler.getProducts());
    }
    
    /**
     * Search products that contains a string
     * @param s - String to look for
     * @return All products which String s in their name
     */
    public List<ProductPanel> search(String s){
        return productsToPanels(handler.findProducts(s));
    }
    
    /**
     * Search products that is in a certain category
     * @param c - Category to get
     * @return All products in category c
     */
    public List<ProductPanel> search(Category c){
    	List<Product> p = new ArrayList<>();
        switch (c){
            case FAVOURITE:
                p.addAll(handler.favorites());
                break;
            case MEAT:
            	p.addAll(handler.getProducts(ProductCategory.MEAT));
            	break;
            case FISH:
            	p.addAll(handler.getProducts(ProductCategory.FISH));
            	break;
            case DAIRY:
            	p.addAll(handler.getProducts(ProductCategory.DAIRIES));
            	break;
            case BREAD:
            	p.addAll(handler.getProducts(ProductCategory.BREAD));
            	break;
            case VEGETABLE:
                p.addAll(handler.getProducts(ProductCategory.ROOT_VEGETABLE));
                p.addAll(handler.getProducts(ProductCategory.POD));
                p.addAll(handler.getProducts(ProductCategory.HERB));
                p.addAll(handler.getProducts(ProductCategory.CABBAGE));
                break;
                
            case FRUIT:
            	p.addAll(handler.getProducts(ProductCategory.FRUIT));
            	p.addAll(handler.getProducts(ProductCategory.EXOTIC_FRUIT));
            	p.addAll(handler.getProducts(ProductCategory.VEGETABLE_FRUIT));
            	p.addAll(handler.getProducts(ProductCategory.CITRUS_FRUIT));
            	p.addAll(handler.getProducts(ProductCategory.MELONS));
                p.addAll(handler.getProducts(ProductCategory.BERRY));
                break;
            	
            case DRY:
                p.addAll(handler.getProducts(ProductCategory.PASTA));
                p.addAll(handler.getProducts(ProductCategory.POTATO_RICE));
                p.addAll(handler.getProducts(ProductCategory.NUTS_AND_SEEDS));
                p.addAll(handler.getProducts(ProductCategory.FLOUR_SUGAR_SALT));
                break;
            case BEVERAGE:
            	p.addAll(handler.getProducts(ProductCategory.COLD_DRINKS));
            	p.addAll(handler.getProducts(ProductCategory.HOT_DRINKS));
            	break;
            case SWEET: 
            	p.addAll(handler.getProducts(ProductCategory.SWEET));
            	break;
            	
        }
        
        return productsToPanels(p);
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
    /**
     * Change amount of a specified Product p
     * @param p The product
     * @param amount Amount of p to change to
     */
    public void changeAmount(Product p, double amount){
        changeAmount(new ShoppingItem(p,amount));
    }
    
    public IMatDataHandler getDataHandler(){
    	return handler;
    }
    
    /**
     * Private method to make converting List<Product> to List<ProductPanel> easier.
     * @param p Product List to convert
     * @return all Products in ProductPanel form
     */
    private List<ProductPanel> productsToPanels(List<Product> p){
        List<ProductPanel> panels = new ArrayList<>();
        for (Product product : p){
            panels.add(new ProductPanel(product, getProductImage(product, 200, 200)));
        }
        
        return panels;
    }
    
}
