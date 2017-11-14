/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import GUI.Mapa;
import GUI.MenuLista;
import GUI.SeznamObrazku;
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

/**
 *
 * @author xzenj02
 */
public class Main extends Application {
    //private Object ZadejPrikazTextArea;
    private TextArea centralText;
    private IHra hra;
    
    public void setHra(IHra hra) {
        this.hra = hra;
    }
    
    
    private TextField zadejPrikazTextField;
    private Mapa mapa;
    private MenuLista menuLista;
   // private SeznamObrazku seznamObrazku;
    private Stage stage;
    
    

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
             
        hra = new Hra();
        mapa = new Mapa(hra);
        menuLista = new MenuLista(hra, this);
       // seznamObrazku = new SeznamObrazku(hra);
       
        BorderPane borderPane = new BorderPane();
        
        
        TextArea centralText = new TextArea();
        centralText.setText(hra.vratUvitani());
        centralText.setEditable(false);
        borderPane.setCenter(centralText);
        
        Label zadejPrikazLabel = new Label("Zadej prikaz: ");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        TextField zadejPrikazTextField = new TextField("...");
        zadejPrikazTextField.setOnAction(new EventHandler<ActionEvent>
        () {

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
      
        // FlowPane obrazekFlowPane = new FlowPane ();
       // obrazekFlowPane.setPrefSize(200,200);
       // ImageView obrazekImageView = new ImageView (new Image(Main.class.getResourceAsStream("/zdroje/mapa.png"),300,300,false,true));
       // Circle tecka = new Circle(10, Paint.valueOf("red"));
        //obrazekFlowPane.setAlignment(Pos.CENTER);
        //obrazekFlowPane.getChildren().add(obrazekImageView);
        
        
        FlowPane dolniLista = new FlowPane();
        dolniLista.setAlignment(Pos.CENTER);
        dolniLista.getChildren().addAll(zadejPrikazLabel, zadejPrikazTextField);
        
        borderPane.setLeft(mapa);
        //borderPane.setRight(seznamObrazku); 
        borderPane.setBottom(dolniLista);
        borderPane.setTop(menuLista);
        
        Scene scene = new Scene(borderPane, 750, 450);

        primaryStage.setTitle("Adventura");

        primaryStage.setScene(scene);
        primaryStage.show();
        zadejPrikazTextField.requestFocus();
    }

   
    public TextArea getCentralText() {
        return centralText;
    }

    public Mapa getMapa() {
        return mapa;
    }
    
   
    public Stage getStage() {
        return stage;
    }
    //je to dobre ??? get nad
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
