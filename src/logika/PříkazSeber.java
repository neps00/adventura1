/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy PříkazSeber umožňuje zobratie prenositeľných vecí do batohu.
 * Hráč pomocou tohto príkazu bude môcť zobrať vec a vložiť ju do batohu, 
 * pokiaľ je v ňom ešte miesto a pokiaľ je vec prenositeľná.
 *
 * @author    Simona Nepšinská
 *            pro školní rok 2017/2018 - cvičení UT 9:15
  */
public class PříkazSeber implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "seber";
    private HerniPlan plan;
    private Batoh batoh;
   
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor pre triedu PrikazSeber.
     */
    public PříkazSeber(HerniPlan plan, Batoh batoh)
    { this.plan=plan;
      this.batoh=plan.getBatoh();
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
        Vec vec=aktualni.odeberVec(nazevSbiraneho); 
        if(vec==null){
        return "To tu neni.";
        }
        
      else{
            if(vec.jePrenositelna()){
                // věc lze přenést
                if(batoh.vlozVec(vec)){
                    plan.notifyObservers();
                    return "Sebral/a jsi " + vec.getNazev() + ".";
                }
                aktualni.vlozVec(vec);
                plan.notifyObservers();
                return "Batoh je plný. Nejprve se zbav zbytečností.";
                // pokud se nevejde, musí se vrátit do prostoru
                // pokud ano, dá věc do kapsy
            }  
            else{
                aktualni.vlozVec(vec);
                plan.notifyObservers();
                return "Tohle si odtud vzít nemůžeš.";
                // věc není přenositelná
            }  
        }   
        
    }
    
    /**
     * Metoda vracá název příkazu(slovo které používa hrac pro jeho vyvolani)
     * return nazev příkazu
     */
    @Override
    public String getNazev(){
        return NAZEV;
    }
    

}
