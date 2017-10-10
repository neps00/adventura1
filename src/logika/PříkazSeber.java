/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy PříkazSeber představují ...
 * Hráč pomocou tohto príkazu bude môcť zobrať vec a vložiť ju do batohu, 
 * pokiaľ je v ňom ešte miesto a pokiaľ je vec prenositeľná.
 *
 * @author    Simona Nepšinská
 *            pro školní rok 2015/2016 LS - cvičenie Štvrtok 11:00
 * @version BlueJ 3.1.0, JDK 8
 * Dátum poslednej zmeny: 22.5.2016 
  */
public class PříkazSeber implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "seber";
    private HerniPlan plan;
   
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PříkazSeber(HerniPlan plan)
    { this.plan=plan;
     //this.batoh=batoh;
    }
    
    @Override
    /**
     * Metoda, ktorá provede príkaz v hre. Skontroluje najskor ci sa zmesti do batohu,
     * ak áno, tak nájde vec v priestore, poprípade v iných veciach a ak tam je taká vec
     * tak overí, či je prenositeľná.
     * 
     * @param parametry- je jeden- a to nazov veci.
     */
    public String proved(String... parametry){
        if (parametry.length ==0){
            return "Co mám sebrat?";
        }
        //skontrolovat ci sa vejde do batohu
        if(!plan.getBatoh().vejdeSeDoBatohu()){
            return "Nevejde se do batohu, lebo je plný.";
        }
        
        String nazevSbiraneho=parametry[0];
        Prostor aktualni=plan.getAktualniProstor();
        Vec vec=aktualni.getVec(nazevSbiraneho); 
        if(vec==null){
        return "to tu neni";
        }
        
        if(vec.jePrenositelna()){
            plan.getBatoh().pridajVec(vec);
            aktualni.vyberVec(nazevSbiraneho); 
            return "sebráno";
        }
        
        aktualni.vlozVec(vec);
        return "to neuzvedneš";
        
        
        
        
        
    }
    
    /**
     * Metoda vracá název příkazu(slovo které používa hrac pro jeho vyvolani)
     * return nazev příkazu
     */
    @Override
    public String getNazev(){
        return NAZEV;
    }
    //== Nesoukromé metody (instancí i třídy) ======================================


    //== Soukromé metody (instancí i třídy) ========================================

}
