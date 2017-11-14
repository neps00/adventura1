/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import logika.Batoh;
import logika.HerniPlan;
import utils.Observer;
import logika.IHra;


/**
 *
 * @author sneps
 */
public class SeznamObrazku implements Observer {

   private IHra hra;
   //private ImageView obsahBatohu;
   //private TilePane panelSObrazkem;
   private TilePane seznamObrazku;
   private Batoh batoh;
   private HerniPlan herniPlan;
   
   public SeznamObrazku(IHra hra) {
     this.hra = hra;   
     this.batoh=batoh;
     this.herniPlan = herniPlan;
     
     hra.getHerniPlan().registerObserver(this);
     //herniPlan.getBatoh().registerObserver(this);
     init();
    }
   
   public void novaGame(IHra novaHra){
    hra.getHerniPlan().removeObserver(this);
    hra = novaHra;
    hra.getHerniPlan().registerObserver(this);
    update();
    } 
   
   public void init(){
       
    TilePane seznamObrazku = new TilePane();
    seznamObrazku.setHgap(8);
    seznamObrazku.setPrefColumns(4);
    for (int i = 0; i < 20; i++) {
        seznamObrazku.getChildren().add(new ImageView(...));
        
    }
    
        
    }
   
   
    
    @Override
    public void update() {
       
    }

    
    
}
