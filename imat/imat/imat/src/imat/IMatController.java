/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Joakim
 */
public class IMatController{
    //private IMatModel model;
    private IMatView view;
    
    public IMatController(IMatView view){
        this.view = view;
        view.addCategoriesListeners(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                //List<IMatProduct> listOfProducts = model.getCategoryProducts(e.getActionCommand());
                //view.setProducts(listOfProducts);
                view.setOutput("Det funkar");
            }
            
        });
    }
    
}
