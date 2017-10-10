package logika;


/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Simona Nepšinská
 *            pro školní rok 2015/2016 LS - cvičenie Štvrtok 11:00
 * @version BlueJ 3.1.0, JDK 8
 * Dátum poslednej zmeny: 22.5.2016 
 */
public class HerniPlan {
    private final static String VITEZNY = "vlčie doupě"; //dopisane, aby sme sa nepomylili, tak sme vytvorili konstantu
    private Prostor aktualniProstor;
    private Batoh batoh;
    
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();
        batoh=new Batoh();

    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor školka = new Prostor("školka","školka, kde sa hrajú a učia kachničky");
        Prostor jazero = new Prostor("jazero", "jazero, kde býva kachnička s maminkou");
        Prostor dedina = new Prostor("dedina","centrum kachniček, kde ma kanceláriu starostka");
        Prostor lúka = new Prostor("lúka","lúka s truhlou");
        Prostor brána = new Prostor("brána","zamknutá brána");
        Prostor chalupa =new Prostor("chalupa","chalupa, kde býva obor");
        Prostor les =new Prostor("les","les, kde môžeš stretnúť poľovníka");
        Prostor vlčieDoupě =new Prostor("vlčie_doupě","doupě, kde býva vlk");
        
        
        // přiřazují se průchody mezi prostory (sousedící prostory)
        školka.setVychod(jazero);
        jazero.setVychod(školka);
        jazero.setVychod(dedina);
        dedina.setVychod(jazero);
        lúka.setVychod(dedina);
        dedina.setVychod(lúka);
        lúka.setVychod(brána);
        brána.setVychod(lúka);
        brána.setVychod(chalupa);
        chalupa.setVychod(brána);
        chalupa.setVychod(les);
        les.setVychod(chalupa);
        les.setVychod(vlčieDoupě);
        
        brána.setZamknout(true);
        les.setZamknout(true);
        Vec klic=new Vec("klic",true);
        brána.nastavKlic(klic);
        
        
        dedina.setPostava(new Postava("starostka", "Vitaj v našej dedine! \n"+
        "Mám pre teba zlú správu Lisa, keď prišiel vlk do dediny uniesol tvoju maminku. \n " +
        "Je mi to veľmi ľúto."));
        chalupa.setPostava(new Postava("obor", "Vitaj v mojej chalúpke! \n "+
        "Som obor Leo a aby si sa dostala odtiaľto musíš mi doniesť jablko, \n " +
        "ak mi ho nedonesieš, tak ťa nepustím ďalej! "));
        
        
        
        
        Vec truhla=new Vec ("truhla", false);
        Vec strom=new Vec("strom", false);
        Vec kvety=new Vec("kvety", true);
        Vec krabica= new Vec ("krabica", true);
        Vec fotka=new Vec("fotka",true);
        Vec jablko=new Vec("jablko",true);
        
        lúka.vlozVec(truhla);
        lúka.vlozVec(strom);
        lúka.vlozVec(krabica);
        lúka.vlozVec(kvety);
        truhla.vlozVec(klic);
        truhla.vlozVec(fotka);
        truhla.vlozVec(jablko);
                
        aktualniProstor = školka;  // hra začíná v domečku       
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
                
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
       
    }
    /**
     * 
     */
    public boolean vyhra(){
        return aktualniProstor.getNazev().equals(VITEZNY);
    }
    
    /**
    *  Odkaz na batoh
    * 
    */ 
    public Batoh getBatoh() {
        return batoh;
    }  
}
