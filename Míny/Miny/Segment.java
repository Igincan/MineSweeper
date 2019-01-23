 
/**
 * Trieda, ktorá sa stará o vytvorenie jednotlivých segmentov číslice.
 * 
 * @author Igor Novák
 * @version 21.12.2017
 */
public class Segment {
    
    private Obdlznik obdlznik;
    private boolean jeViditelny;
    private int velkost;
    private String farba;
    
    /**
     * Konštruktor, kde sa inicializujú atribúty.
     */
    public Segment(int x, int y, int typ, int velkost) {
        this.jeViditelny = false;
        this.velkost = velkost;
        this.farba = "black";
        
        switch(typ) {
            case 0:
                this.obdlznik = this.vytvorObdlznik(x + (1 * this.velkost), y + (0 * this.velkost), 5 * this.velkost, 1 * this.velkost, this.farba);
                break;
            case 1:
                this.obdlznik = this.vytvorObdlznik(x + (6 * this.velkost), y + (1 * this.velkost), 1 * this.velkost, 5 * this.velkost, this.farba);
                break;
            case 2:
                this.obdlznik = this.vytvorObdlznik(x + (6 * this.velkost), y + (7 * this.velkost), 1 * this.velkost, 5 * this.velkost, this.farba);
                break;
            case 3:
                this.obdlznik = this.vytvorObdlznik(x + (1 * this.velkost), y + (12 * this.velkost), 5 * this.velkost, 1 * this.velkost, this.farba);
                break;
            case 4:
                this.obdlznik = this.vytvorObdlznik(x + (0 * this.velkost), y + (7 * this.velkost), 1 * this.velkost, 5 * this.velkost, this.farba);
                break;
            case 5:
                this.obdlznik = this.vytvorObdlznik(x + (0 * this.velkost), y + (1 * this.velkost), 1 * this.velkost, 5 * this.velkost, this.farba);
                break;
            case 6:
                this.obdlznik = this.vytvorObdlznik(x + (1 * this.velkost), y + (6 * this.velkost), 5 * this.velkost, 1 * this.velkost, this.farba);
                break;
        }
    }
    
    /**
     * Metóda, ktorá zviditeľní segment.
     */
    public void zviditelni() {
        this.jeViditelny = true;
        this.obdlznik.zobraz();
    }
    
    /**
     * Metóda, ktorá zneviditeľní segment.
     */
    public void zneviditelni() {
        this.jeViditelny = false;
        this.obdlznik.skry();
    }
    
    /**
     * Metóda, ktorá vypína hru.
     */
    public void vypni() {
        if (this.obdlznik != null) {
            this.obdlznik.zobraz();
            this.obdlznik.zmaz();
        }
    }
    /**
     * Metóda, ktorá vytvára inśtanciu typu Obdlznik.
     */
    private Obdlznik vytvorObdlznik(int x, int y, int sirka, int vyska, String farba) {
        Obdlznik vytvaranyObdlznik = new Obdlznik();
        vytvaranyObdlznik.posunVodorovne(x - 60);
        vytvaranyObdlznik.posunZvisle(y - 50);
        vytvaranyObdlznik.zmenStrany(sirka, vyska);
        vytvaranyObdlznik.zmenFarbu(farba);
        return vytvaranyObdlznik;
    }
}
