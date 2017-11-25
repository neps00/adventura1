/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy Postava představují postavy, ktoré sa v hre vyskytujú.
 *
 * @author    Simona Nepšinská
 *            pro školní rok 2017/2018 - cvičení UT 9:15
 *
 */
public class Postava
{
    //== Datové atributy (statické i instancí)======================================
    private String jmeno;
    private Vec coChce;
    private String mluvPred;
    private String mluvPo;
    private boolean probehlaVymena=false;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor pro postavu.
     */
    public Postava(String jmeno, String mluvPred)
    {this.jmeno = jmeno;
     this.mluvPred = mluvPred;
     
    }
    
    /**
     * Metoda nevrací nic, jenom nastavuje podmínky vymeny.
     * @param coChce
     * @param mluvPo 
     */
    public void nastavVymenu(Vec coChce, String mluvPo){
        this.coChce=coChce;
        this.mluvPo=mluvPo;
    }
    
    /**
     * Metoda overuje, či prebehla výmena.
     * @return true- ak prebehla
     *          false - ak neprebehla
     */
    public boolean getProbehlaVymena(){
        return probehlaVymena;
    }
    
    /**
     * Nastaví, či výmena probehla - true alebo nie - false
     */
    public void probehlaVymena(boolean vymena){
       this.probehlaVymena=vymena;
    }
    
    /**
     * Metoda vrací proslov postavy pred výmenou.
     * @return proslov mluvPred
     */
    public String getMluv(){
    return mluvPred;
    }
    
    /**
     * Metoda vrací proslov postavy, když neprobehla výmena je to proslov mluvPred a keď probehla, tak mluvPo.
     * 
     */
    public String getVymena(){
    if(probehlaVymena){
        return mluvPred;
    
    }
    else{
    return mluvPo;
    }
    }
    
    /**
     * Metoda vrací jméno postavy.
     *
     */
    public String getJmeno(){
        return jmeno;
    }
    
    /**
     * Metoda vrací jaku vec chce osoba na vymenu.
     * @return 
     */
    public Vec getCoChce() {
        return coChce;
    }
    
    

}
