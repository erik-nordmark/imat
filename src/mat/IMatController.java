/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
        view.addCategoriesListeners(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                List<Product> listOfProducts = model.search(IMatModel.Category.MEAT);
                List<ProductPanel> list = new ArrayList();
                for(Product p : listOfProducts){
                    ProductPanel pp = new ProductPanel(p,model.getProductImage(p, 200, 200));
                    list.add(pp);
                }
                view.setProducts(list);
            }
            
        });
        
        view.addSearchListeners(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = view.getSearchText();
                System.out.println(searchText);
                List<Product> listOfProducts = model.search(searchText);
                List<ProductPanel> list = new ArrayList();
                for(Product p : listOfProducts){
                    ProductPanel pp = new ProductPanel(p,model.getProductImage(p, 200, 200));
                    list.add(pp);
                }
                view.setProducts(list);
            }
        });
        
        
        view.addBuyListeners(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = ((ProductPanel)e.getSource()).getAmount();
                Product product = ((ProductPanel)e.getSource()).getProduct();
                model.addToCart(new ShoppingItem(product, amount));
            }
        });
        
        view.addShoppingCartListeners(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        view.addSwitchViewListeners(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    
}
