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
        view.addProductListeners(new ProductListener());
        view.addCartListeners(new CartListener());
        view.addViewListeners(new ViewListener());
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
