/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
import java.util.*;


/*******************************************************************************
 * Instance třídy Vec představují ...
 *
 * @author    Simona Nepšinská
 *            pro školní rok 2015/2016 LS - cvičenie Štvrtok 11:00
 * @version BlueJ 3.1.0, JDK 8
 * Dátum poslednej zmeny: 22.5.2016 
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private boolean prenositelnost;
    private boolean prozkoumana= false;
    private Map<String,Vec> seznamVeci;
    private String soubor;
   
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor triedy vec 
     *  -jej nazov a ci je prenositelna
     */
    public Vec(String nazev, boolean prenositelnost, String soubor)
    {this.nazev=nazev;
        this.prenositelnost=prenositelnost;
     seznamVeci = new HashMap<String,Vec>();
     this.soubor = soubor;
     

    }
    
    /**
     * Vráti meno vci.
     */
    public String getNazev(){
        return nazev;
    }
    
    /**
     * Zisti, či je vec prenositeľná
     * @return true - ak je prenositeľná
     */
    public boolean jePrenositelna(){
        return prenositelnost;
    }
    
    public Vec getVec(String nazev){
    for(Vec vec: seznamVeci.values()){
        if(vec.getNazev().equals(nazev)){
            return vec;
        }
    }
    return null;
    }
    
  
    
    /**
     * Nastaví príznak preskúmania.
     */
    public void prozkoumano (boolean prozkoumana) {
        this.prozkoumana = prozkoumana;
    }
    
    /**
     * Vloží vec do inej veci.
     */
    public void vlozVec (Vec neco) {
        seznamVeci.put(neco.getNazev(), neco);
    }

    /**
     * Zistí, či sa určité veci nachádzajú v inej veci(truhle).
     */
    public boolean obsahujeVec(String nazev) {
        return prozkoumana && seznamVeci.containsKey(nazev);
    }
    
    /**
     * Vyberie vec z inej veci, najskor preskúma, či tam daná vec je a ak áno
     * tak ju vyberie.
     * 
     * @return null ak tam nie je daná vec
     */
    public Vec vyberVec(String nazev) {
       
        Vec vec = null;
        if (seznamVeci.containsKey(nazev)) {
            vec = seznamVeci.get(nazev);
            if (vec.jePrenositelna()) {
                
                seznamVeci.remove(nazev);
            }
        }
        
        return vec;
    }
    
    /**
     * Vracia popis danej veci, keď ju preskúmavaš
     */
    public String popisProzkoumej() {
        if (seznamVeci.isEmpty()) {
            return "prohlédl jsi si pozorně "+nazev+", je opravdu dobře udělaný";
        }
        String popis = "Prohlédl jsi si pozorně "+nazev+" a uvnitř jsi našel:";
        for (String nazev : seznamVeci.keySet()) {
            popis += " " + nazev;
        }
        return popis;
    }

    /**
     * Vráti nazov veci.
     */
    public String toString() {
        return nazev;
    }
    
    /**
     * Popíše vec.
     */
    public String popisVec(){
        
        if(seznamVeci.isEmpty()){
            return "Prehliadol si si "+nazev+"a je naozaj pekná";
        }
        else{
        String vracenyText= " jsou tu tyto veci: ";
        for(String nazev: seznamVeci.keySet()){
            vracenyText += " " + nazev;
        }
        return vracenyText;
       }
    }
    
}
