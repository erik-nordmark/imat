/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imat;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Joakim
 */
public class IMatController implements Observer{
    //private IMatModel model;
    private IMatView view;
    
    public IMatController(IMatView view){
        this.view = view;
        //view.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
