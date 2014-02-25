/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import se.chalmers.ait.dat215.project.CartEvent;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingCartListener;
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
        view.addCartListeners(new CartListener());
        view.addViewListeners(new ViewListener());
        model.getDataHandler().getShoppingCart().addShoppingCartListener(new ShoppingCartListener(){

            @Override
            public void shoppingCartChanged(CartEvent ce) {
                ShoppingCart cart = (ShoppingCart)ce.getSource();
                view.setCartItems(cart.getItems());
            }
            
        });
    }
    
    
    /**
     * Class for listening on an event and producing ProductPanels from the selected category.
     */
    private class CategoryListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            CategoryPanel p = (CategoryPanel)e.getSource();
            String category = p.getCategoryName();
            List<ProductPanel> list = model.search(model.stringToCategory(category));
            view.setProducts(list);
            view.addProductListener(new ProductListener());
        }

        @Override
        public void mousePressed(MouseEvent e) {
            ((CategoryPanel)e.getSource()).mousePressed();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            ((CategoryPanel)e.getSource()).mouseReleased();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ((CategoryPanel)e.getSource()).mouseEntered();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            ((CategoryPanel)e.getSource()).mouseExited();
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
            // Lägg till så söktexten syns i en panel ovanför resultaten
            
            List<ProductPanel> list = model.search(searchText);
            view.setProducts(list);
            view.addProductListener(new ProductListener());
        }
        
    }
    
    /**
     * Class for listening on an event and adding the selected product to the Shopping Cart.
     */
    private class ProductListener implements PropertyChangeListener{

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            ProductPanel pp = (ProductPanel)evt.getSource();
            double amount = pp.getAmount();
            Product product = pp.getProduct();
            model.addToCart(new ShoppingItem(product, amount));
        }
        
    }
    
    /**
     * Class for listening on events controlling the operations on the shopping cart.
     */
    private class CartListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // Ändra antal, ta bort, köp och töm
            
        }
        
    }
    
    /**
     * Class for listening on an event and switching view to the selected.
     */
    private class ViewListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // inställningar, historik, hjälp, hem
        }
        
    }
    
}
