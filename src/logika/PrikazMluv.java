/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy PrikazMluv umožňuje mluvení s osobami ve hře.
 * Táto trieda umožňuje mluvit s postavami v hre. 
 *
 * @author    Simona Nepšinská
 *            pro školní rok 2017/2018 - cvičení UT 9:15
 
 */
public class PrikazMluv implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "mluv";
    private HerniPlan plan;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor pro třídu PrikazMluv.
     */
    public PrikazMluv(HerniPlan plan)
    { this.plan=plan;
    }
    
    /**
     *  Vykonáva príkaz "mluv". Najskor sa zisti, ci je nejaka postava v priestore.
     *  
     *
     *@param parametre - jeden-meno osoby, s ktorou chceme hovoriť
     *@return správa, ktorú vypíše hra hráčovi
     */  
    public String proved(String... parametry){ 
        if (parametry.length ==0){
            return "S kým mám mluvit?";
        }
        
        String nazevSKymMluvit=parametry[0]; 
        Prostor aktualni=plan.getAktualniProstor();
        Postava postava=aktualni.vyberPostava(nazevSKymMluvit); // vráti osobu a uloží ju do postavy
                
        if(postava ==null){
            return "Takáto postava tu nie je.";
            
        }
        else{
            return postava.getMluv(); 
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
