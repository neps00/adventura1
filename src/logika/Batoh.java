/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import java.util.*;


/*******************************************************************************
 * Instance třídy Batoh představují ...
 * Trieda Batoh tvori pre hráčov register jednotlivých vecí, ktoré počas hry 
 * sa rozhodli zobrať so sebou.
 *
 * @author    Simona Nepšinská
 *            pro školní rok 2015/2016 LS - cvičenie Štvrtok 11:00
 * @version BlueJ 3.1.0, JDK 8
 * Dátum poslednej zmeny: 22.5.2016 
 */
public class Batoh
{
    //== Datové atributy (statické i instancí)======================================
    /**Prvý príkaz vytvára zoznam vecí, ktoré budú v batohu.
     * Druhý príkaz určuje maximálnu hodnotu, ktorá môže byť v batohu.
     * 
     */
    private Map<String, Vec>seznamVeci;
    private static final int MAXIMALNI_POCET=3;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public Batoh()
    {seznamVeci=new HashMap<String, Vec>();
    }
    /**
     * Táto metoda testuje, či je ešte miesto v batohu, ak áno vráti true a ak nie vráti false.
     */
    public boolean vejdeSeDoBatohu(){
        if(seznamVeci.size()<MAXIMALNI_POCET){
            return true;
        }
        
        return false;
    }
    /**
     * Metoda pridáva vec do batohu, s tým, že aj vypíše, či sa vložila, ak tam je miesto,
     * ak nie je, tak vypíše opak.
     */
    public String pridajVec(Vec vec){
        if(vejdeSeDoBatohu()){
        seznamVeci.put(vec.getNazev(),vec);
        return "Pridal si vec do batohu.";
        }
        return "Bohužial, túto vec si nemôžeš vziať, lebo v batohu už nie je miesto.";
    }
    /**
     * Metoda odeberá vec z batohu, ak tam je. Ak v batohu nie je vráti false.
     */
    public boolean odeberVec(String nazev){
       
        if(seznamVeci.containsKey(nazev)){
        
        seznamVeci.remove(nazev);
        return true;
    
       }
       return false;
    
    }
    
    /**
     * Získame vec.
     */
    public Vec getVec(String nazevVeci){
    return seznamVeci.get(nazevVeci);
    }   
    
    /**
     * Metoda vypíše obsah batohu.
     */
    public String obsahBatohu(){
    String vypis="V batohu je: ";
    for (String nazevVeci:seznamVeci.keySet()){
     vypis+= nazevVeci + ".";
    }
    return vypis;
    }
    
    /**
    *  Metoda pro zjištění, zda je daná věc v batohu(pokud není překročena kapacita batohu).
    *  @param  predmet  Parametrem je věc, na kterou se ptáme.
    */   
    public boolean obsahujeVec(String nazevVeci) {
        if (seznamVeci.containsKey(nazevVeci)) {
            return true;
        }
        return false;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================


    //== Soukromé metody (instancí i třídy) ========================================

}
