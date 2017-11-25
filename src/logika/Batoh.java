/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import java.util.*;


/*******************************************************************************
 * Instance třídy Batoh představují miesto, kde mozeme ukladat a prenášať veci.
 * Trieda Batoh tvori pre hráčov register jednotlivých vecí, ktoré počas hry 
 * sa rozhodli zobrať so sebou.
 *
 * @author    Simona Nepšinská
 *            pro školní rok 2017/2018 - cvičení UT 9:15
 * 
 */
public class Batoh
{
    //== Datové atributy (statické i instancí)======================================
    /**Prvý príkaz vytvára zoznam vecí, ktoré budú v batohu.
     * Druhý príkaz určuje maximálnu hodnotu, ktorá môže byť v batohu.
     * 
     */
    private Map<String, Vec>veci;
    private static final int MAXIMALNI_POCET=3;
   
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor pre triedu Batoh
     */
    
    public Batoh()
    {veci=new HashMap<String, Vec>();
    }
    
    /**
     * Vrací seznam věcí z batohu prostrednictvom mapy.
     */
    
    public Map <String, Vec> vratBatoh()
    {
        return this.veci;
    }
    
    
    /**
     * Táto metoda testuje, či je ešte miesto v batohu, ak áno vráti true a ak nie vráti false.
     */
    public boolean vejdeSeDoBatohu(){
        if(veci.size()<MAXIMALNI_POCET){
            return true;
        }
        
        return false;
    }
    /**
     * Metoda pridáva vec do batohu, s tým, že aj vypíše, či sa vložila, ak tam je miesto,
     * ak nie je, tak vypíše opak.
     */
   // public String pridajVec(Vec vec){
     //   if(vejdeSeDoBatohu()){
    //    veci.put(vec.getNazev(),vec);
     //   return "Pridal si vec do batohu.";
     //   }
   //     return "Bohužial, túto vec si nemôžeš vziať, lebo v batohu už nie je miesto.";
   // }
    
    /**
     * Metoda vklada vec do batohu, ak je v ňom miesto a je prenositeľná.
     * @param vec
     * @return true - ak je vlozena
     *          false - ak sa nevlozila
     */
    public boolean vlozVec(Vec vec)
    {
        if(vejdeSeDoBatohu() && (vec.jePrenositelna())) {
            veci.put(vec.getNazev(), vec);

            return true;
        }
        return false;
    }
    
    /**
     * Metoda odeberá vec z batohu, ak tam je. Ak v batohu nie je vráti false.
     */
    public boolean odeberVec(String nazev){
       
        if(veci.containsKey(nazev)){
        
        veci.remove(nazev);
        return true;
    
       }
       return false;
    
    }
    
    /**
     * Metoda vyhodí vec z batohu.
     * 
     */   
     public Vec vyhodVec(String nazev){
        Vec vyhozenaVec = null;
        if (veci.containsKey(nazev)) {
            vyhozenaVec = veci.get(nazev);
            veci.remove(nazev);
        }
        return vyhozenaVec;  
    } 
    
    /**
     * Získame vec.
     */
    public Vec getVec(String nazevVeci){
    return veci.get(nazevVeci);
    }   
    
    /**
     * Metoda vypíše obsah batohu.
     */
    public String obsahBatohu(){
    String vypis="V batohu je: ";
    for (String nazevVeci:veci.keySet()){
     vypis+= nazevVeci + ".";
    }
    return vypis;
    }
    
    /**
    *  Metoda pro zjištění, zda je daná věc v batohu(pokud není překročena kapacita batohu).
    *  @param  predmet  Parametrem je věc, na kterou se ptáme.
    */   
    public boolean obsahujeVec(String nazevVeci) {
        if (veci.containsKey(nazevVeci)) {
            return true;
        }
        return false;
    }

   

}
