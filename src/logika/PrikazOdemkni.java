/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy PrikazOdemkni umožňuje odomykání zamčených prostoru. 
 *Príkaz odemkni bude odomykať zamknuté miestnosti, ak máš kľúč.
 *
 * @author    Simona Nepšinská
 *             pro školní rok 2017/2018 - cvičení UT 9:15
 * *
 */
public class PrikazOdemkni implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "odemkni";
    private HerniPlan plan;
    
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor pro třídu PrikazOdemkni
     *  @param plan herní plán, je potřeba zjistit, zda jsem v místnosti
    *                    vedle místnosti, která se má odemknout
     */
    public PrikazOdemkni(HerniPlan plan)
    {this.plan=plan;
        
    }
    
    @Override
    /**
     * Metoda, ktorá provede príkaz odemkni v hre. Zistí, či je z aktuálnej
     * miestnosti východ do zadanej. Ak miestnosť existuje a je zamknutá, tak 
     * sa zistí, či je v batohu kľúč. Ak áno, miestnosť sa odomkne.
     * 
     * @param parametry-je jeden- a to nazov miestnosti,
     * ktorú má odomknúť.
     * 
     *
     */
    public String proved(String... parametry){
        if(parametry.length == 0){
            return "Čo mám odemknout?";
        
        }
        String nazovMistnosti=parametry[0];
        Prostor aktualniProstor=plan.getAktualniProstor();
        
        Prostor sousedniProstor=aktualniProstor.vratSousedniProstor(nazovMistnosti);
        if(sousedniProstor==null){
                return "Nepodarilo sa ti odemknout prostor, lebo priestor so zadaným priestorom nesusedí.";
            }
            else{
                if(plan.getBatoh().obsahujeVec("klic")==true){
                    sousedniProstor.setZamknout(false);
                    return "Podarilo sa ti otvoriť dvere do miestnosti " + sousedniProstor.getNazev() + ".";
                
                }
                else{
                return "Nemáš kľúč v batohu, skús ho nájsť.";
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
