/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import main.Main;

/**
 *  Trieda, ktorá vytvára lištu menu.
 *  @author    Simona Nepšinská
 *            pro školní rok 2017/2018 - cvičení UT 9:15
 */
public class MenuLista extends MenuBar{
    
    private IHra hra;
    private Main main;
    private Stage stage;
    
    /**
    * Konstruktor triedy menuLista.
    * 
    */
    
    public MenuLista(IHra hra, Main main){
        this.hra = hra;
        this.main = main;
        this.stage = main.getStage();
        init();
    }
    
    /**
    * Metoda vytvaří menu Adventura a Help. V každém jsou dvě položky. Cez Adventru mužeme spustit novou hru nebo ukončit aktuální hru.
    * Cez Help se nám ukáže nápověda ke hře-přikazy a taky nápověda o programu.
    * 
    */
    
    private void init(){
        
        Menu novySoubor = new Menu("Adventura");
        Menu napoveda = new Menu ("Help");
        
        MenuItem novaHra = new MenuItem("Nova hra");
                
        novaHra.setAccelerator(KeyCombination.keyCombination("Ctrl+H"));
        MenuItem konecHry = new MenuItem("Konec hry");
        
        novySoubor.getItems().addAll(novaHra, konecHry);
        
        MenuItem oProgramu = new MenuItem ("O programu");
        MenuItem napovedaItem = new MenuItem ("Napoveda");
        
        napoveda.getItems().addAll(oProgramu, napovedaItem);
        
        this.getMenus().addAll(novySoubor, napoveda);
        
        konecHry.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        
        novaHra.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
          
                main.start(stage);
            }
        });
        
       /**
    * Zobrazí se okno s informacemi o programu.
    * 
    */  
       oProgramu.setOnAction(new EventHandler<ActionEvent>() {

    
           
           @Override
            public void handle(ActionEvent event) {
                Alert oProgramuAlert = new Alert(Alert.AlertType.INFORMATION);
                oProgramuAlert.setTitle("O programu");
                oProgramuAlert.setHeaderText("Kachní hledání");
                oProgramuAlert.setContentText("Cieľom adventury je nájsť stratenú kachničku a poraziť zlého vlka, ktorý ju uniesol.");
                oProgramuAlert.initOwner(main.getStage());
                
                oProgramuAlert.showAndWait();
            }
        });
       
       napovedaItem.setOnAction(new EventHandler<ActionEvent>() {

    /**
    * Zobrazí se okno s informacemi o nápověde - jak hrát hru.
    * 
    */
           @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                stage.setTitle("Napoveda");
                WebView webView = new WebView();
                
                webView.getEngine().load(Main.class.getResource("/zdroje/napoveda.html").toExternalForm());
                stage.setScene(new Scene(webView, 500, 500));
                stage.show();
                       
                        
                 }
        });
       
    }
    
}