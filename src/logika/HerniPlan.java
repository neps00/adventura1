package logika;

import java.util.ArrayList;
import java.util.List;
import utils.Observer;
import utils.Subject;


/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Simona Nepšinská
 *            pro školní rok 2017/2018 - cvičení UT 9:15
 *
 */
public class HerniPlan implements Subject{
    private final static String VITEZNY = "vlčie doupě"; //dopisane, aby sme sa nepomylili, tak sme vytvorili konstantu
    private Prostor aktualniProstor;
    private Batoh batoh;
    private List<Observer> listObserveru = new ArrayList<Observer>();
    private Hra hra;
    
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan(Hra hra) {
        zalozProstoryHry();
        batoh=new Batoh();
        this.hra = hra;

    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor školka = new Prostor("školka","školka, kde sa hrajú a učia kachničky",35,25);
        Prostor jazero = new Prostor("jazero", "jazero, kde býva kachnička s maminkou",110,25);
        Prostor dedina = new Prostor("dedina","centrum kachniček, kde ma kanceláriu starostka",190,25);
        Prostor lúka = new Prostor("lúka","lúka s truhlou",150,80);
        Prostor brána = new Prostor("brána","zamknutá brána",90,135);
        Prostor chalupa =new Prostor("chalupa","chalupa, kde býva obor",165,160);
        Prostor les =new Prostor("les","les, kde môžeš stretnúť poľovníka",200,230);
        Prostor vlčieDoupě =new Prostor("vlčie_doupě","doupě, kde býva vlk",100,250);
        
        
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
        Vec klic=new Vec("klic",true,"klic.png");
        brána.nastavKlic(klic);
        
        
        dedina.setPostava(new Postava("starostka", "Vitaj v našej dedine! \n"+
        "Mám pre teba zlú správu Lisa, keď prišiel vlk do dediny uniesol tvoju maminku. \n " +
        "Je mi to veľmi ľúto."));
        chalupa.setPostava(new Postava("obor", "Vitaj v mojej chalúpke! \n "+
        "Som obor Leo a aby si sa dostala odtiaľto musíš mi doniesť jablko, \n " +
        "ak mi ho nedonesieš, tak ťa nepustím ďalej! "));
        
        
        
        //Založenie vecí.
        Vec truhla=new Vec ("truhla", false,"truhla.png");
        Vec strom=new Vec("strom", false,"strom.png");
        Vec kvety=new Vec("kvety", true,"kvety.png");
        Vec krabica= new Vec ("krabica", true,"krabica.png");
        Vec fotka=new Vec("fotka",true,"fotka.png");
        Vec jablko=new Vec("jablko",true,"jablko.png");
        
        //Vloženie vecí do priestorov.
        lúka.vlozVec(truhla);
        lúka.vlozVec(strom);
        lúka.vlozVec(krabica);
        lúka.vlozVec(kvety);
        truhla.vlozVec(klic);
        truhla.vlozVec(fotka);
        truhla.vlozVec(jablko);
                
        aktualniProstor = školka;  // hra začíná v školke      
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
       notifyObservers();
       }
    
    /**
     * Metoda rozhoduje, či hráč zvíťazil na základe podmienok.
     */
    public boolean vyhra(){
        return aktualniProstor.getNazev().equals(VITEZNY);
    }
    
    /**
    *  Metoda vracia hodnotu batohu.
    * 
    */ 
    public Batoh getBatoh() {
        return batoh;
    }  

    /**
     * Metoda vrací celou aktuální hru.
     * 
     */
    public Hra getHra(){
        return this.hra;
    }
    
    /**
    *  Override pro registraci, odranení a notofikaci observeru.
    * 
    */ 
    @Override
    public void registerObserver(Observer observer) {
        listObserveru.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        listObserveru.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer listObserveruItem : listObserveru) {
            listObserveruItem.update();
            
        }
    }
}
