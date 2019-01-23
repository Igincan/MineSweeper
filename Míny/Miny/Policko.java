
/**
 * Trieda, ktorá sa stará o jednotlivé políčka na hernej ploche.
 * 
 * @author Igor Novák
 * @version 21.12.2017
 */
public class Policko {
    
    private int poziciaX;
    private int poziciaY;
    private String farba;
    private boolean skryvaMinu;
    private int pocetOkolitychMin;
    private int velkost;
    private Stvorec stvorec;
    private boolean jeOdkryte;
    private Cislica cislica;
    private boolean jeOznacene;
    private Stvorec oznacujuciStvorec;
    private String oznacovaciaFarba;
    private StavHry stavHry;
    
    /**
     * Konštruktor, kde sa inizializujú atribúty.
     */
    public Policko(int poziciaX, int poziciaY, int pocetOkolitychMin, int velkost, StavHry stavHry) {
        this.poziciaX = poziciaX;
        this.poziciaY = poziciaY;
        this.pocetOkolitychMin = pocetOkolitychMin;
        this.velkost = velkost;
        this.skryvaMinu = false;
        
        this.farba = "black";
        this.oznacovaciaFarba = "yellow";
        this.jeOdkryte = false;
        this.jeOznacene = false;
        
        this.stavHry = stavHry;
        this.cislica = null;
        this.oznacujuciStvorec = this.vytvorStvorec(this.poziciaX * (this.velkost * 20) + ((this.velkost * 20) / 4), this.poziciaY * (this.velkost * 20) + ((this.velkost * 20) / 4), (this.velkost * 20) / 2, this.oznacovaciaFarba);
        this.stvorec = this.vytvorStvorec(this.poziciaX * (this.velkost * 20), this.poziciaY * (this.velkost * 20), (this.velkost * 20), this.farba);
        this.stvorec.zobraz();
    }
    
    /**
     * Metóda, ktorá odkrýva políčko.
     */
    public void odkry() {
        if (!this.jeOznacene) {
            this.jeOdkryte = true;
            if (this.skryvaMinu) {
                this.zmenFarbu("red");
            } else {
                this.zmenFarbu("white");
                if (!(this.cislica == null)) {
                    this.cislica.zobraz();
                }
            }
        }
    }
    
    /**
     * Metóda, ktorá označuje políčko.
     */
    public void oznac() {
        if (!this.jeOznacene) {
            this.oznacujuciStvorec.zobraz();
            this.jeOznacene = true;
        } else {
            this.oznacujuciStvorec.skry();
            this.jeOznacene = false;
        }
    }
    
    /**
     * Metóda, ktorá označuje políčko na zeleno.
     */
    public void oznacNaZeleno() {
        this.oznacujuciStvorec.zmenFarbu("green");
        this.jeOznacene = true;
    }
    
    /**
     * Metóda, ktorá mení farbu polička.
     */
    public void zmenFarbu (String farba) {
        this.stvorec.zmenFarbu(farba);
        this.farba = farba;
    }
    
    /**
     * Metóda, ktorá schováva mínu do políčka.
     */
    public void schovajMinu() {
        this.skryvaMinu = true;
    }
    
    /**
     * Metóda, ktorá vracia hodnotu atribútu skryvaMinu.
     */
    public boolean getSkryvaMinu() {
        return this.skryvaMinu;
    }
    
    /**
     * Metóda, ktorá vracia hodnotu atribútu jeOdkryte.
     */
    public boolean getJeOdkryte() {
        return this.jeOdkryte;
    }
    
    /**
     * Metóda, ktorá vracia hodnotu atribútu jeOznacene.
     */
    public boolean getJeOznacene() {
        return this.jeOznacene;
    }
    
    /**
     * Metóda ktorá vypína hru.
     */
    public void vypni() {
        System.out.println("Vypina policko!");
        if (this.stvorec != null) {
            this.stvorec.zobraz();
            this.stvorec.zmaz();
        }
        if (this.oznacujuciStvorec != null) {
            this.oznacujuciStvorec.zobraz();
            this.oznacujuciStvorec.zmaz();
        }
        if (this.cislica != null) {
            this.cislica.vypni();
        }
    }
    
    /**
     * Metóda, ktorá nastavuje počet okolitých mín. Číslo ktoré sa skrýva pod políčkom.
     */
    public void setPocetOkolitychMin(int pocetOkolitychMin) {
        this.pocetOkolitychMin = pocetOkolitychMin;
        if (this.pocetOkolitychMin != 0) {
            this.cislica = new Cislica(this.poziciaX * (this.velkost * 20) + ((this.velkost * 20) - ((this.velkost * 20) / 20 * 6)) / 2, this.poziciaY * (this.velkost * 20) + ((this.velkost * 20) - ((this.velkost * 20) / 20 * 13)) / 2, this.pocetOkolitychMin, this.velkost);
        }
    }
    
    /**
     * Metóda, ktorá vracia hodnotu atribútu pocetOkolitychMin.
     */
    public int getPocetOkolitychMin() {
        return this.pocetOkolitychMin;
    }
    
    /**
     * Metóda, ktorá vytvára inštanciu typu Stvorec.
     */
    private Stvorec vytvorStvorec(int x, int y, int strana, String farba) {
        Stvorec vytvaranyStvorec = new Stvorec();
        vytvaranyStvorec.posunVodorovne(x - 60);
        vytvaranyStvorec.posunZvisle(y - 50);
        vytvaranyStvorec.zmenStranu(strana);
        vytvaranyStvorec.zmenFarbu(farba);
        return vytvaranyStvorec;
    }
}
