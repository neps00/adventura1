/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import logika.HerniPlan;
import logika.Vec;
import utils.Observer;


/**
 * Trieda umožňuje zobrazenie obrázkov, ktoré sú v batohu.
 * @author    Simona Nepšinská
 *            pro školní rok 2017/2018 - cvičení UT 9:15
 */

public class SeznamObrazku implements Observer{
    
    private HerniPlan plan;
    ListView<Object> list;
    ObservableList<Object> data;
    
    private TextArea centralText;

    /*
    * Konstruktor pro panel kapsy.
    */
    public SeznamObrazku(HerniPlan plan,TextArea text) {
       this.plan = plan;
       plan.registerObserver(this);
       centralText = text;
        init();
    }

    /*
    * Metoda vytvoří list pro věci v batohu. Pričom odstranovanie vecí funguje na princípe dvojkliku na danú vec(obrázok) myšou.
    */
    private void init() {
        list = new ListView<>();
        data = FXCollections.observableArrayList();
        list.setItems(data);
        list.setPrefWidth(200);
        
        list.setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent click)
            {
                if (click.getClickCount() == 2) 
                {
                    int index = list.getSelectionModel().getSelectedIndex();
                    
                    Map<String, Vec> seznam;
                    seznam = plan.getBatoh().vratBatoh();
                    
                    String nazev = "";
                    int pomocna = 0;
                    for (String x : seznam.keySet()) 
                    {
                       if(pomocna == index)
                       {
                           nazev = x;
                       }
                       pomocna++;
                    }
                    
                    String vstupniPrikaz = "odhod "+nazev;
                    String odpovedHry = plan.getHra().zpracujPrikaz("odhod "+nazev);

                
                    centralText.appendText("\n" + vstupniPrikaz + "\n");
                    centralText.appendText("\n" + odpovedHry + "\n");
               
                    plan.notifyObservers();
                }
            }
        });
        
        
        
        update();
    }
    
    /*
    * Metoda vrací list.
    */
    public ListView<Object> getList() {
        return list;
    }
    
    /*
    * Metoda aktualizuje list věcí v batohu. Zobrazuje obrázky věcí, které má hráč u sebe.
    */
    @Override 
    public void update() 
    {        
        Map<String, Vec> seznam;
        seznam = plan.getBatoh().vratBatoh();
        data.clear();
        for (String x : seznam.keySet()) 
        {
        Vec pomocna = seznam.get(x);
        ImageView obrazek = new ImageView(new Image(main.Main.class.getResourceAsStream("/zdroje/"+pomocna.getObrazek()), 100, 100, false, false));
        data.add(obrazek);
        }
    }
    
    /**
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     * @param plan
     */
    public void nastaveniHernihoPlanu (HerniPlan plan){
        this.plan = plan;
        plan.registerObserver(this);
        this.update();
    }



}
