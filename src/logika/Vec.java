/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
import java.util.*;


/*******************************************************************************
 * Instance třídy Vec představují veci, které se v hre používají nebo jenom vyskytují.
 *
 * @author    Simona Nepšinská
 *            pro školní rok 2017/2018 - cvičení UT 9:15
 * 
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private boolean prenositelnost;
    private boolean prozkoumana= false;
    private Map<String,Vec> veci;
    private String obrazek;
   
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor triedy vec 
     *  -jej nazov, ci je prenositelna a obrázok
     */
    public Vec(String nazev, boolean prenositelnost, String obrazek)
    {this.nazev=nazev;
        this.prenositelnost=prenositelnost;
     veci = new HashMap<String,Vec>();
     this.obrazek = obrazek;
     

    }
    
    /**
     * Metoda vrácia meno vci.
     */
    public String getNazev(){
        return nazev;
    }
    /**
     * Metoda vracia obrázek.
     * 
     * 
     */
    public String getObrazek(){
        return obrazek;
    }
    
    /**
     * Zisti, či je vec prenositeľná
     * @return true - ak je prenositeľná
     *          false - ak nie je prenositeľná
     */
    public boolean jePrenositelna(){
        return prenositelnost;
    }
    
    /**
     * Metoda vrací seznam věcí, pokud v seznamu jsou nějaké věci. Pokud ne, vrací null.
     * 
     */
    public Vec getVec(String nazev){
    for(Vec vec: veci.values()){
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
    public void vlozVec(Vec neco) {
        veci.put(neco.getNazev(), neco);
    }

    /**
     * Zistí, či sa určité veci nachádzajú v inej veci(truhle).
     */
    public boolean obsahujeVec(String nazev) {
        return prozkoumana && veci.containsKey(nazev);
    }
    
    /**
     * Vyberie vec z inej veci, najskor preskúma, či tam daná vec je a ak áno
     * tak ju vyberie.
     * 
     * @return null ak tam nie je daná vec
     */
    public Vec vyberVec(String nazev) {
       
        Vec vec = null;
        if (veci.containsKey(nazev)) {
            vec = veci.get(nazev);
            if (vec.jePrenositelna()) {
                
                veci.remove(nazev);
            }
        }
        
        return vec;
    }
    
    /**
     * Vracia popis danej veci, keď ju preskúmavaš
     */
    public String popisProzkoumej() {
        if (veci.isEmpty()) {
            return "prohlédl jsi si pozorně "+nazev+", je opravdu dobře udělaný";
        }
        String popis = "Prohlédl jsi si pozorně "+nazev+" a uvnitř jsi našel:";
        for (String nazev : veci.keySet()) {
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
        
        if(veci.isEmpty()){
            return "Prehliadol si si "+nazev+" a sú(je) naozaj pekná,-é,-ý";
        }
        else{
        String vracenyText= " jsou tu tyto veci: ";
        for(String nazev: veci.keySet()){
            vracenyText += " " + nazev;
        }
        return vracenyText;
       }
    }
    
    /**
     * Metoda vracia zoznam veci prostredníctvom mapy.
     */
    public Map getSeznamVeci()
    {
        return veci;
    }
    
}
