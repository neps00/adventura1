package logika;

/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Simona Nepšinská
 *            pro školní rok 2015/2016 LS- cvičenie Štvrtok 11:00
 * @version BlueJ 3.1.0, JDK 8
 * Dátum poslednej zmeny: 22.5.2016 
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;
    

    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PříkazSeber(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazOdemkni(herniPlan));
       platnePrikazy.vlozPrikaz(new PrikazProzkoumej(herniPlan));
       platnePrikazy.vlozPrikaz(new PrikazMluv(herniPlan));
       platnePrikazy.vlozPrikaz(new PrikazDej(herniPlan));
       platnePrikazy.vlozPrikaz(new PrikazOdhod(herniPlan));
       
    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "Vítejte!\n" +
               "Pri krásnom modrastom jazere sa zrodila malá kachnia dedinka. Bola plná kachních rodín \n" +
               "rôzneho veku a všetky spolu dobre vychádzali. Neďaleko od jazera vo vybudovanej školke \n" +
               "sa hrali malé kachničky. Jedna z nich bola neobyčajná, vyžarovala z nej veľká statočnosť, \n" +
               "volala sa Lisa. Lisa však nemala práve najlepší osud, pretože z rodiny jej zostala už len maminka. \n" +
               "Jej dvaja súrodenci zomreli po narodení a ocinka zastrelil poľovník. Lisa mala s maminkou veľmi \n" +
               "pevný vzťah a mala ju veľmi rada.\n" +
               "Jedného dňa však prišiel do ich kachnej dedinky obrovský vlk, zožral dvoch káčerov a uniesol \n" +
               "Lisinu maminku do svojho dúpäťa. Lisa v tom čase bola v škôlke a pani učiteľka ich stihla skryť. \n"  +
               "Po incidente pani učiteľka každého poslala domov. \n" +
               "Choď domov na jazero! \n"+
               "Napište 'napoveda', pokud si nevíte rady, jak hrát dál.\n" +
               "\n" +
               herniPlan.getAktualniProstor().dlouhyPopis();
    }
    
        
    
    /**
     * Ak si vyhral (dostal sa do cieľa) tak sa vypíše epilóg.
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        if(herniPlan.vyhra()){             
            return " Dík, že si si zahrál. ";
        }
        return "Ahoj.";
    }
    
    
    /** 
     * Vrací true, pokud hra skončila.
     */
     public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
            parametry[i]= slova[i+1];   
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.proved(parametry);
            if(herniPlan.vyhra()){ //dopisany if
                konecHry=true;
            }
        }
        else {
            textKVypsani="Nevím co tím myslíš? Tento příkaz neznám. ";
        }
        return textKVypsani;
    }
    
    
     /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
     /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
     public HerniPlan getHerniPlan(){
        return herniPlan;
     }
    
}

