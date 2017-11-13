/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.GridLayout;
import javax.swing.BorderFactory;

import utils.Observer;
import logika.IHra;
import logika.Batoh;

/**
 *
 * @author sneps
 */
public class SeznamObrazku implements Observer {

   private IHra hra;
   private JPanel obsahBatohu;
   private JPanel panelSObrazkem;
   
   public SeznamObrazku(IHra hra) {
     this.hra = hra;   
     hra.getHerniPlan().registerObserver(this);
     init();
    }
   
   public void novaGame(IHra novaHra){
    hra.getHerniPlan().removeObserver(this);
    hra = novaHra;
    hra.getHerniPlan().registerObserver(this);
    update();
    } 
   
   public void init(){
              
     // obsahBatohu= new JPanel (new GridLayout (2,11));
     // panelSObrazkem.add(obsahBatohu);
    //  obsahBatohu.setBorder(BorderFactory.createTitledBorder("Obsah batohu"));
      
      
        
    }
   
   
    
    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
