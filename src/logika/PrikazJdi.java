package logika;

/**
 *  Třída PrikazJdi implementuje pro hru příkaz jdi.
 *  Tato třída je součástí jednoduché textové hry.
 *  Umožňuje vstupovať do rôznych priestor hry.
 *  
 *@author     Simona Nepšinská
 *            pro školní rok 2015/2016 LS - cvičenie Štvrtok 11:00
 * @version BlueJ 3.1.0, JDK 8
 * Dátum poslednej zmeny: 22.5.2016 
 */
class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazJdi(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            
            return "Kam mám jít? Musíš zadat jméno východu";
        }

        String smer = parametry[0];
        
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);
        

        if (sousedniProstor == null) {
            return "Tam se odsud jít nedá!";
        }
        else {
            if(sousedniProstor.jeZamceno()){
                return "dvere do miestnosti "+sousedniProstor.getNazev()
                +" sú zamknuté";
            
            }
            
            }
            plan.setAktualniProstor(sousedniProstor);
            return sousedniProstor.dlouhyPopis();
        
            
              
        
        }
    
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
