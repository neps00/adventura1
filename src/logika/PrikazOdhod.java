/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


/*******************************************************************************
 * Instance třídy PrikazOdhod umožňuje vyhadzovanie vecí z batoha.
 * Trieda odhod, odhodi vybranu vec z batohu, aby tam bolo miesto.
 *
 * @author    Simona Nepšinská
 *            pro školní rok 2017/2018 - cvičení UT 9:15
 */
public class PrikazOdhod implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV ="odhod";
    private HerniPlan plan;
    private Batoh batoh;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor pre triedu PrikazOdhod
     */
    public PrikazOdhod(HerniPlan plan, Batoh batoh)
    { this.plan = plan;
    this.batoh = plan.getBatoh();
        
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
            //ak hráč nenapíše, co má zahodit
        }

        String nazevVeci = parametry[0];
        Prostor aktualni=plan.getAktualniProstor();
        Vec vec=batoh.getVec(nazevVeci);
        
        if(vec !=null){
            batoh.odeberVec(nazevVeci);
            aktualni.vlozVec(vec);
            plan.notifyObservers();
            return "Odhodil si " + nazevVeci + ".";
            //Zadaná vec sa odhodí do prostoru
            
        
        }
        else{        
            return "Taká vec v batohu nie je!";
            //Ak napíše takú vec, ktorá v batohu není
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
