/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import logika.Hra;
import logika.IHra;
import main.Main;
import utils.Observer;

/**
 *  Trieda, ktorá zobrazuje mapu a presúvanie hráča po nej prostredníctvom tečky.
 *  @author    Simona Nepšinská
 *            pro školní rok 2017/2018 - cvičení UT 9:15
 */
public class Mapa extends AnchorPane implements Observer{

    private IHra hra;
    private Circle tecka;
    
    /**
    * Konstruktor pre triedu mapa.
    * 
    */
       
    public Mapa(IHra hra) {
     this.hra = hra;   
     hra.getHerniPlan().registerObserver(this);
     init();
    }
    /**
    * Metoda zobrazí obrázok - mapu k naší adventure a taky tecku, která ukazuje umístnění hráče.
    * 
    */
    public void init(){
        ImageView obrazekImageView = new ImageView (new Image(Main.class.getResourceAsStream("/zdroje/mapa.png"),300,300,false,true));
        
        tecka = new Circle(10, Paint.valueOf("red"));
        this.getChildren().addAll(obrazekImageView,tecka);
        update();
        
    }
    
    /**
    * Metoda zabezpečuje reset hry, když se otevře nová hra.
    * 
    */
    
    public void newGame(IHra novaHra){
    hra.getHerniPlan().removeObserver(this);
    hra = novaHra;
    hra.getHerniPlan().registerObserver(this);
    update();
    }
    
    
    /**
    * Metoda aktualizuje umístnění tečky.
    * 
    */
    @Override
    public void update() {
        this.setTopAnchor(tecka, hra.getHerniPlan().getAktualniProstor().getPosTop());
        this.setLeftAnchor(tecka, hra.getHerniPlan().getAktualniProstor().getPosLeft());
    }
    
}
