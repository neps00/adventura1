/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


/*******************************************************************************
 * Instance třídy PrikazOdhod představují ...
 * Trieda odhod, odhodi vybranu vec z batohu, aby tam bolo miesto.
 *
 * @author    Simona Nepšinská
 *            pro šk. rok 2015/2016 LS - cvičenie Štvrtok 11:00
 * 
 *  @version BlueJ 3.1.0, JDK 8
 * Dátum poslednej zmeny: 22.5.2016 
 */
public class PrikazOdhod implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV ="odhod";
    private HerniPlan plan;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazOdhod(HerniPlan plan)
    { this.plan = plan;
      
      
    }

    /**
     *  Vyhodí predmet z batohu, ak sa v ňom nachádza a vloží ho naspäť do aktuálneho priestoru.
     *  
     *  @param parameter- je jeden a to nazov veci, ktorú chce vyhodit z batohu
     *
     */     
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            return "Čo mám zahodit? ";
        }

        String nazevVeci = parametry[0];
        Prostor aktualni=plan.getAktualniProstor();
        Batoh batoh=plan.getBatoh();
        Vec vec=batoh.getVec(nazevVeci);
        
        if(vec !=null){
            batoh.odeberVec(nazevVeci);
            aktualni.vlozVec(vec);
            return "Odhodil si " + nazevVeci;
            
        
        }
        else{        
            return "Taká vec v batohu nie je!";
            
        }
        
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    public String getNazev() {
        return NAZEV;
    }

}
