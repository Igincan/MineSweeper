
/**
 * Trieda, ktorej inštancie ukladajú údaje o obtiažnostiach.
 * 
 * @author Igor Novák
 * @version 21.12.2017
 */
public class Obtiaznost {
    
    private int pocetMin;
    private int pocetX;
    private int pocetY;
    private int velkost;
    private String nazov;
    
    /**
     * Konštruktor, kde sa inizializujú atribúty.
     */
    public Obtiaznost(int pocetMin, int pocetX, int pocetY, String nazov) {
        this.pocetMin = pocetMin;
        this.pocetX = pocetX;
        this.pocetY = pocetY;
        this.nazov = nazov;
        this.velkost = 40 / this.pocetY;
    }
    
    /**
     * Metóda, ktorá vracia hodnotu atribútu pocetMin.
     */
    public int getPocetMin() {
        return this.pocetMin;
    }
    
    /**
     * Metóda, ktorá vracia hodnotu atribútu pocetX.
     */
    public int getPocetX() {
        return this.pocetX;
    }
    
    /**
     * Metóda, ktorá vracia hodnotu atribútu pocetY.
     */
    public int getPocetY() {
        return this.pocetY;
    }
    
    /**
     * Metóda, ktorá vracia hodnotu atribútu velkost.
     */
    public int getVelkost() {
        return this.velkost;
    }
    
    /**
     * Metóda, ktorá vracia hodnotu atribútu nazov.
     */
    public String getNazov() {
        return this.nazov;
    }
    
    /**
     * Metóda, ktorá vracia reťazec všetkých atribútov.
     */
    public String getVsetko() {
        return this.pocetMin + " " + this.pocetX + " " + this.pocetY + " " + this.velkost + " " + this.nazov;
    }
}
