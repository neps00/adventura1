/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import GUI.Mapa;
import GUI.MenuLista;
import GUI.PanelVeci;
import GUI.SeznamObrazku;
import GUI.PanelVychodu;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logika.*;
import uiText.TextoveRozhrani;
import javafx.scene.input.MouseEvent;

/**
 *@author    Simona Nepšinská
 *            pro školní rok 2017/2018 - cvičení UT 9:15
 */
public class Main extends Application{
    
    private TextArea centralText;
    private IHra hra;
    
    private TextField zadejPrikazTextField;
    private Mapa mapa;
    private MenuLista menuLista;
    private Stage stage;
    
    private SeznamObrazku seznamObrazku;
    private PanelVychodu panelVychodu;
    private PanelVeci panelVeci;
    
 /**
 * Metoda pro nastavení hry.
 * 
 */
      public void setHra(IHra hra) {
        this.hra = hra;
    }
    
 /**
 * Metoda vytváří mapu,menu,hru a stage. Vytváří textové pole, kde se vypisuje úvodní text hry, 
 * label na zadávání příkazu.
 */   

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
             
        hra = new Hra();
        mapa = new Mapa(hra);
        menuLista = new MenuLista(hra, this);
        //Hlavný borderPane      
        BorderPane borderPane = new BorderPane();
        // Borderpane pro východy a věci v místnosti
        BorderPane borderPane2 = new BorderPane();
        // Borderpane pro batoh / přechody a mapu
        BorderPane borderPane3 = new BorderPane();
         
        //Text s priebehom hry.
        centralText = new TextArea();
        centralText.setText(hra.vratUvitani());
        centralText.setEditable(false);
        borderPane.setCenter(centralText);
        
        //Label s textom Zadej prikaz
        Label zadejPrikazLabel = new Label("Zadej prikaz: ");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        //Text area, do ktorej píšeme príkazy
        zadejPrikazTextField = new TextField("...");
        zadejPrikazTextField.setOnAction(new EventHandler<ActionEvent>
        () {
            
             /*
            * Zpracování příkazů, odpověď hry, vrácení centrálního textu.
            */
            @Override
            public void handle(ActionEvent event) {
                String vstupniPrikaz = zadejPrikazTextField.getText();
                String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);
                
                centralText.appendText("\n" + vstupniPrikaz + "\n");
                centralText.appendText("\n" + odpovedHry + "\n ");
                
                zadejPrikazTextField.setText("");
                
                if(hra.konecHry()) {
                    zadejPrikazTextField.setEditable(false);
                    centralText.appendText(hra.vratEpilog());
                }    
            }
        });
      
        /**
        * Definujeme umístnění menu, seznamu obrázku.
        * 
        */    
        //Dolná lišta s elementami.
        FlowPane dolniLista = new FlowPane();
        dolniLista.setAlignment(Pos.CENTER);
        dolniLista.getChildren().addAll(zadejPrikazLabel, zadejPrikazTextField);
        
        borderPane.setBottom(dolniLista);
        borderPane.setTop(menuLista);
           
        
        // panel Veci     
        panelVeci = new PanelVeci(hra.getHerniPlan(),centralText);
        borderPane2.setLeft(panelVeci.getList());
        // panel Východů
        panelVychodu = new PanelVychodu(hra.getHerniPlan(),centralText,zadejPrikazTextField);
        
        
        borderPane2.setRight(panelVychodu.getList());
        
        borderPane.setRight(borderPane2);
        // panel Batohu
        seznamObrazku = new SeznamObrazku(hra.getHerniPlan(),centralText);
        borderPane3.setLeft(seznamObrazku.getList());
        
        borderPane3.setTop(mapa);
        borderPane.setLeft(borderPane3);
       
        Scene scene = new Scene(borderPane, 750, 450);
        primaryStage.setTitle("Adventura");

        primaryStage.setScene(scene);
        primaryStage.show();
        zadejPrikazTextField.requestFocus();
    }

    /**
    * Metoda vrací text.
    * 
    */
    public TextArea getCentralText() {
        return centralText;
    }

    
    /**
    * Metoda vrací mapu.
    * 
    */
    public Mapa getMapa() {
        return mapa;
    }
    
    /**
    * Metoda vrací stage.
    * 
    */
    public Stage getStage() {
        return stage;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        }
        else{
            if (args[0].equals("-txt")) {
                IHra hra = new Hra();
                TextoveRozhrani textHra = new TextoveRozhrani(hra);
                textHra.hraj();
            }
            else{
                System.out.println("Neplatny parametr");
                System.exit(1);
            }
        }
    }

}
