/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy PrikazDej představují fungování předání určité věci osobě.
 * Tento príkaz odovzdá určitý predmet nejakej postave.
 *
 * @author    Simona Nepšinská
 *            pro školní rok 2017/2018 - cvičení UT 9:15
 *
 */
public class PrikazDej implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "dej";
    private HerniPlan plan;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor triedy PrikazDej.
     */
    public PrikazDej(HerniPlan plan)
    {this.plan=plan;
    }
    
    @Override
    /**
     *Provadi sa s osobou, ktorá musí byť v miestnosti a musí danú vec aj chcieť, čo zistíte, keď sa s ňou porozprávate.
     *Najskor sa zisti, či je osoba v priestore a potom, či danú vec máš v batohu. Ak áno, tak sa uskutoční výmena.
     *
     *@param parametry - 1 parametr je jméno postavy,
     *                   2 parametr je jméno věci,
     *                         
     *@return zpráva, kterou vypíše hra hráči
     * 
     *
     */
    public String proved(String... parametry){
        if(parametry.length == 0){
            if(parametry.length==1){
                   return "Musíš zadať dva parametry, \"osobu\" aj \"vec\" ! "; 
                  }
            
            return "Čo mám dať? Musíš dať dva parametry, \"osobu\" a \"vec\".";
                  
        }
        
        
        String nazevOsoby=parametry[0];
        String nazevVeci=parametry[1];
        Prostor aktualni=plan.getAktualniProstor();
        Postava postava=aktualni.vyberPostava(nazevOsoby);      
        
        if(postava==null){
         
           if(!plan.getBatoh().obsahujeVec(nazevVeci)){
            return "Nemáš to, čo chcem.";
            }
            return "Táto osoba tu nie je.";
            }
        
        
            
       plan.getBatoh().odeberVec(nazevVeci);
       postava.probehlaVymena(true); 
       
       if(nazevOsoby.equals("obor") && nazevVeci.equals("jablko")){
             if(aktualni.vratSousedniProstor("les")!=null){
                    aktualni.vratSousedniProstor("les").setZamknout(false);
             }
       }
       return "\n Dal jsi "+nazevVeci+ " osobě " + postava.getJmeno() +".";
                         
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
