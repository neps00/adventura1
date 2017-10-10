package uiText;


import java.util.Scanner;
import logika.IHra;
import java.io.*;
/**
 *  Class TextoveRozhrani
 * 
 *  Toto je uživatelského rozhraní aplikace Adventura
 *  Tato třída vytváří instanci třídy Hra, která představuje logiku aplikace.
 *  Čte vstup zadaný uživatelem a předává tento řetězec logice a vypisuje odpověď logiky na konzoli.
 *  
 *  
 *
 * @author    Simona Nepšinská
 *            pro školní rok 2015/2016 LS - cvičenie Štvrtok 11:00
 * @version BlueJ 3.1.0, JDK 8
 * Dátum poslednej zmeny: 22.5.2016 
 */

public class TextoveRozhrani {
    private IHra hra;

    /**
     *  Vytváří hru.
     */
    public TextoveRozhrani(IHra hra) {
        this.hra = hra;
    }

    /**
     *  Hlavní metoda hry. Vypíše úvodní text a pak opakuje čtení a zpracování
     *  příkazu od hráče do konce hry (dokud metoda konecHry() z logiky nevrátí
     *  hodnotu true). Nakonec vypíše text epilogu.
     */
    public void hraj() {
        System.out.println(hra.vratUvitani());

        // základní cyklus programu - opakovaně se čtou příkazy a poté
        // se provádějí do konce hry.

        while (!hra.konecHry()) {
            String radek = prectiString();
            System.out.println(hra.zpracujPrikaz(radek));
        }

        System.out.println(hra.vratEpilog());
    }
    
    public void hrajZeSouboru(String nazevSouboru) {
        //u try kulate zavorky jako
        //musime pridat close a este catch na to-lebo sa moze vyhodit exception
        try(BufferedReader ctecka = new BufferedReader (new FileReader(nazevSouboru)))
        {
         //buf. obaluje a bol tu predtym...
        // základní cyklus programu - opakovaně se čtou příkazy a poté
        // se provádějí do konce hry.
        System.out.println(hra.vratUvitani());
        
        String radek = ctecka.readLine();
        while (!hra.konecHry() && radek != null) {
            System.out.println("*"+radek+"*");
            System.out.println(hra.zpracujPrikaz(radek));
            radek = ctecka.readLine();
        }
        
        System.out.println(hra.vratEpilog());
        }
        catch(FileNotFoundException e){
            System.out.println("Soubor nenalezen"+ e);
        }
        catch(IOException e){ // v kazdem bloku je to jine e-cko
           System.out.println("Problém se vstupem"+ e); 
        }
        
        
    }

    /**
     *  Metoda přečte příkaz z příkazového řádku
     *
     *@return    Vrací přečtený příkaz jako instanci třídy String
     */
    private String prectiString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        return scanner.nextLine();
    }

}
