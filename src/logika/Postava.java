/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy Postava představují ...
 *
 * @author    Simona Nepšinská
 *            pro školní rok 2015/2016 LS - cvičenie Štvrtok 11:00
 * @version BlueJ 3.1.0, JDK 8
 * Dátum poslednej zmeny: 22.5.2016 
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
     *  Konstruktor ....
     */
    public Postava(String jmeno, String mluvPred)
    {this.jmeno = jmeno;
     this.mluvPred = mluvPred;
     
    }
    
    public void nastavVymenu(Vec coChce, String mluvPo){
        this.coChce=coChce;
        this.mluvPo=mluvPo;
    }
    
    
    public boolean getProbehlaVymena(){
        return probehlaVymena;
    }
    
    /**
     * Nastaví, či výmena probehla - true alebo nie - false
     */
    public void probehlaVymena(boolean vymena){
       this.probehlaVymena=vymena;
    }
    
    public String getMluv(){
    return mluvPred;
    }
    
    public String getVymena(){
    if(probehlaVymena){
        return mluvPred;
    
    }
    else{
    return mluvPo;
    }
    }
    
    public String getJmeno(){
        return jmeno;
    }
    
    public Vec getCoChce() {
        return coChce;
    }
    
    

}
