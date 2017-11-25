package logika;

import java.util.*; 
import java.util.stream.Collectors;


/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Simona Nepšinská
 *          pro školní rok 2017/2018 - cvičení UT 9:15
 */
public class Prostor {

    private String nazev;
    private String popis;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private Map<String,Vec> veci; 
    private boolean jeZamceno = false;
    private Vec klic;
    private String otazka;
    private String odpoved;
    private Set<Postava> postavy;
    private boolean probehlaVymena=false;
    private double posLeft;
    private double posTop;
   
    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis, double posLeft, double posTop) {
        this.nazev = nazev;
        this.popis = popis;
        this.posLeft=posLeft;
        this.posTop=posTop;
        vychody = new HashSet<>();
        veci=new HashMap<>();
        postavy= new HashSet<>();
       
    }
    
    /**
     * Metoda vrací pozici objektu.
     * @return posLeft
     */
    public double getPosLeft() {
    return posLeft;
    }
    
    /**
     * Metoda vrací pozici objektu.
     * @return posTop
     */
    public double getPosTop() {
    return posTop;
    }
    
    

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
    @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

        return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        
        if(nazev.equals("jazero")){
          return "Vitaj doma! \n" +
            "Mami, mami, kde si?! \n" +
            "Lise nik neodpovedal, svoju mamičku doma nenašla, nevedela, čo mala robiť a \n" +
            "jediný kto to mohol vedieť bola starostka, ktorá mala svoju kanceláriu v dedine.\n"+
            "Choď do dediny! \n " ;
        }
        else if(nazev.equals("vlčie_doupě")){
            return "Vyhral si, na vlka si použil čarovný nápoj. Ten vlka premenil na malého vĺčka o veľkosti očka na ihle. \n" +
            "Pomohol si kachničke zachrániť maminku a Lisa ti za to veľmi ďakuje !  Ahoj.";
        }
        else{
        return "Jsi v mistnosti/prostoru " + popis + ".\n"
        + popisVychodu() 
        +popisVeci()
        +seznamPostav()
        ;
        }
            
    }
    
        

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "Vychody: ";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
            if(sousedni.jeZamceno()){
                vracenyText += "(Zamknuto!)";
            }
        }
        return vracenyText;
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
            .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
        .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }

    
    /**
     * 
     * Metoda vypíše zoznam východov z aktuálneho prostoru.
     *  
     */
     public String seznamVychodu() 
    {
        String vracenyText = "vychody:";
        for (Prostor sousedni : vychody) {
             vracenyText += " " + sousedni.getNazev();
        }
        return vracenyText;
    }
      
    
  
    
    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }

   
    /**
     * Vkladá vec do priestoru
     * 
     * @param- nazeov veci, ktoru vkladam
     */
    public void vlozVec(Vec neco){
        veci.put(neco.getNazev(),neco);
    }

    /**
     * Odebereme věc z mapy a vrátí se název námi odebrané věci.
     *
     *@param    String název odebírané věci
     *@return   odebraná věc
     */
    public Vec odeberVec(String nazev){
        return veci.remove(nazev);
    }
    
    
    /**
     * Pokud věc není v místnosti, prohledávají se věci v místnosti.
     * Pokud nějaká věc obsahuje věc hledanou, vrací se true.
     * Pokud hledaná věc není nikde v prostoru (ani v "truhle"), vrací se false.
     * V ostatní případech se věc s prostoru vyskytuje, metoda vrací true.
     *
     *@param    String jmeno veci
     *@return   true/false 
     */
    public boolean obsahujeVec(String jmeno) {
        if (najdiVecVMistnosti(jmeno) == null) { // vec není v místnosti
            for (Vec vec : veci.values()) {    // prohledávám věci v místnosti
                if (vec.obsahujeVec(jmeno)) { // když nějaká věc obsahuje věc
                    return true;
                }
            }
            return false;  // věc není ani v prozkoumaných věcech (truhlách)
        }
        else {
            return true;
        }

    }
        
    /**
     * Metoda vráti veci v mape.
     * 
     */
    public Map<String,Vec> getVeci()
     {
         return this.veci;
     }
        
    /**
     * Popisuje veci, ktoré sú v miestnosti.
     * 
     * @return text s vecami
     */   
    private String popisVeci(){
    
        String vracenyText= "\nJsou tu tyto veci: ";
        for(String nazev: veci.keySet()){
            vracenyText += " " + nazev;
        }
        return vracenyText;
    }
       
    /**
     * Odoberá vec z vecí. Vloží sa výsledok z metódy najdiVecVMistnosti a skúma či nie je aj vo veciach. Ak je tak sa odoberie.
     * 
     * @param - vec, ktorú odoberáme
     * @return - vracia vec, ktorú sme odobrali
     */
    public Vec vyberVec (String nazev) { 
        Vec vybranaVec = najdiVecVMistnosti(nazev);
        if(vybranaVec ==null ){
            for (Vec vec : veci.values()) { // hledám, zda není v nějaké věci
                vybranaVec=vec.vyberVec(nazev);
                if (vybranaVec != null) {
                    break;
                }
            }
        }
        return vybranaVec;
    }
    
    /**
     * Pomocná metóda k metóde vyber vec. Hľadá vec len v priestore.
     * 
     * @return null(na zaciatku nastavene), ak vec nie je v priestore
     */
    public Vec najdiVecVMistnosti(String nazev) {
        Vec vec = null;
        for (Vec neco : veci.values()) {
            if (neco.getNazev().equals(nazev)) {
                vec = neco;
                break;
            }
        }
        return vec;
    }
    
    
    /**
     * Zistí, či je miestnosť zamknutá alebo nie.
     * 
     * @return hodnota true pre zamknutú miestnosť, false pre odomknutú 
     */
    public boolean jeZamceno(){
        return jeZamceno;
    }

    /**
     * Nastaví, či je miestnosť zamknutá alebo nie.
     */
    public void setZamknout(boolean zamceno){
        this.jeZamceno=zamceno;
    }

    /**
     * Nastaví vec(klic), ktorú potrebujeme k vstupu do uzamknutej miestnosti.
     */
    public void nastavKlic(Vec klic){
        this.klic=klic;
    }

    /**
     * Vloží klic do miestnosti.
     */
    public Vec getKlic(){
        return klic;
    }

    
    /**
     * Vypíše zoznam postáv, ak sú nejaké v miestnosti.
     */
    public String seznamPostav() {
        String seznam = "\nOsoby v místnosti: ";
        if (postavy.size()==0){
            seznam = seznam + " žádné osoby zde nejsou.";}
        else{
            for (Postava u : postavy) {
                seznam += u.getJmeno();
            }}
        return seznam;
    }
    
    /**
     * Vybere osobu, ktorú prehľadáva v zozname osob a ak ju nájde vypíše ju, ak ju nenájde vypíše null.
     * 
     * @return osoba alebo null 
     */
    public Postava vyberPostava (String jmeno) {
        for ( Postava u : postavy ){
            if (u.getJmeno().equals(jmeno)) {
                return u;
            }
        }
        return null;
    }
    
    /**
     * Přidá osobu.
     * 
     */
    public void setPostava(Postava o) {
        postavy.add (o);
    }
     
    /**
     * Vymaže osobu zo zoznamu postav, ak tam nie je vráti null, ak tam je tak sa vyberie a vypíše ju.
     * 
     * @return osoba alebo null
     */
    public Postava vymazPostavu(String jm) {
        for ( Postava u : postavy ){
            if (u.getJmeno().equals(jm)) {
                Postava vybranaPostava = u;
                postavy.remove(u);
                return vybranaPostava;
            }}
        return null;
    }
    
    

}
