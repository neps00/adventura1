/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy PrikazMluv představují ...
 * Táto trieda umožňuje mluvit s postavami v hre. 
 *
 * @author    Simona Nepšinská
 *            pro školní rok 2015/2016 LS - cvičenie Štvrtok 11:00
 *@version BlueJ 3.1.0, JDK 8
 * Dátum poslednej zmeny: 22.5.2016 
 */
public class PrikazMluv implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "mluv";
    private HerniPlan plan;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
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
    
    

    //== Nesoukromé metody (instancí i třídy) ======================================


    //== Soukromé metody (instancí i třídy) ========================================

}
