/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;

/**
 *
 * @author Joakim
 */
public class IMatController{
    private final IMatModel model;
    private final IMatView view;
    
    public IMatController(final IMatView view, final IMatModel model){
        this.view = view;
        this.model = model;
        view.addCategoriesListeners(new CategoryListener());
        
        view.addSearchListeners(new SearchListener());
        
        view.addBuyListeners(new ProductListener());
        
        view.addShoppingCartListeners(new CartListener());
        
        view.addSwitchViewListeners(new ViewListener());
    }
    
    
    /**
     * Class for listening on an event and producing ProductPanels from the selected category.
     */
    private class CategoryListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //CategoryPanel p = (CategoryPanel)e.getSource();
            //String category = p.getCategoryName();
            //List<ProductPanel> list = model.search(model.getCategory(category));
            //view.setProducts(list);
        }
        
    }
    
    
    /**
     * Class for listening on an event and producing ProductPanels from the search text currently
     * provided by the view.
     */
    private class SearchListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String searchText = view.getSearchText();
            System.out.println(searchText);     //Ta bort senare --> endast för test!
            List<ProductPanel> list = model.search(searchText);
            view.setProducts(list);
        }
        
    }
    
    /**
     * Class for listening on an event and adding the selected product to the Shopping Cart.
     */
    private class ProductListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ProductPanel p = ((ProductPanel)e.getSource());
            double amount = p.getAmount();
            Product product = p.getProduct();
            model.addToCart(new ShoppingItem(product, amount));
        }
        
    }
    
    /**
     * Class for listening on events controlling the 
     */
    private class CartListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // Ändra antal, ta bort, köp och töm
        }
        
    }
    
    /**
     * 
     */
    private class ViewListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // inställningar, historik, hjälp, hem
        }
        
    }
    
}
