/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


/*******************************************************************************
 * Instance třídy PrikazProzkoumej představují ...
 * Umožňuje prozkoumavat veci, aby sme zistili, či sú v nich ešte nejaké iné veci.
 *
 * @author    Simona Nepšinská
 *           pro školní rok 2015/2016 LS - cvičenie Štvrtok 11:00
 * @version BlueJ 3.1.0, JDK 8
 * Dátum poslednej zmeny: 22.5.2016 
 */
public class PrikazProzkoumej implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "prozkoumej";
    private HerniPlan plan;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
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
        Vec zkoumana = aktualni.getVec(nazevZkoumaneho);  //vrati vec podľa názvu a uloží ju do zkoumanej
        
        if(zkoumana ==null){
            return "Takáto vec tu nie je.";
        }
        else{
            return zkoumana.popisVec(); 
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
