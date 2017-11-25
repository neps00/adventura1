/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import java.util.*;


/*******************************************************************************
 * Instance třídy PrikazProzkoumej umožňujú preskúmanie vecí, jestli v nej nie sú ukryté ďalšie veci.
 * Umožňuje prozkoumavat veci, aby sme zistili, či sú v nich ešte nejaké iné veci.
 *
 * @author    Simona Nepšinská
 *           pro školní rok 2017/2018 - cvičení UT 9:15
 *
 */
public class PrikazProzkoumej implements IPrikaz
    {
    
    private static final String NAZEV = "prozkoumej";
    private HerniPlan plan;
    private Map<String, Vec> veci;
    private Vec vec;
    

    /***************************************************************************
     *  Konstruktor pro prikaz prozkoumej.
     */
    public PrikazProzkoumej(HerniPlan plan)
    {this.plan=plan;
    }
    
    /**
     *  Zistí či je daná vec v priestore alebo vo veciach a teda ju preskúma, ak obsahuje predmet v priestore nejaké
     *  iné veci, tak ich vypíše.
     *  
     *  @param parameter- je jeden a to nazov veci, ktorú chceme preskúmať
     *
     */ 
    @Override
    public String proved(String... parametry){ //... znamenaju, že viac veci mozes skumat v jednom prikaze
        if (parametry.length ==0){
            return "Co mám prozkoumat?";
        }
        
        String nazevZkoumaneho=parametry[0]; 
        Prostor aktualni=plan.getAktualniProstor();
        Vec zkoumana = aktualni.najdiVecVMistnosti(nazevZkoumaneho);  //vrati vec podľa názvu a uloží ju do zkoumanej
        
        if(zkoumana ==null){
            return "Takáto vec tu nie je.";
        }
        else{
            if(zkoumana.getNazev().equals("truhla"))
            {
                veci = zkoumana.getSeznamVeci();
                for (Vec vec : veci.values())
                {
                    aktualni.vlozVec(vec);
                }
            }
            plan.notifyObservers();
            zkoumana.prozkoumano(true);
            return zkoumana.popisVec(); 
            //ak prozkoumáme truhlu, v mape sa nám zobrazia obrázky ďalších 3 veci
        }
            
    }

    
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev(){
        return NAZEV;
    }

}
