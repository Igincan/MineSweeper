
/**
 * Trieda sa stará o chod hry a prepojenie vstupu hráča a samotnej hry.
 * 
 * @author Igor Novák
 * @version 21.12.2017
 */
public class Hra {
    
    private HernaPlocha hernaPlocha;
    private Obtiaznost obtiaznost;
    private Aplikacia aplikacia;
    
    private int velkost;
    private boolean kliknutimOznacuje;
    
    /**
     * Konštruktor, kde sa inizializujú atribúty.
     * @param obtiaznost inštancia ktorá určuje veľkosť hracieho pola a počet mín v ňom ukrytých
     */
    public Hra(Obtiaznost obtiaznost, Aplikacia aplikacia) {
        this.obtiaznost = obtiaznost;
        this.aplikacia = aplikacia;
        
        this.kliknutimOznacuje = false;
        this.velkost = this.obtiaznost.getVelkost();
        this.hernaPlocha = new HernaPlocha(this.obtiaznost.getPocetX(), this.obtiaznost.getPocetY(), this.obtiaznost.getPocetMin(), this.velkost);
    }
    
    /**
     * Metóda, ktorá vypína hru.
     */
    public void vypni() {
        this.hernaPlocha.vypni();
    }
    
    /**
     * Metóda, ktorá odkrýva políčko na hernej ploche podľa polohy myši.
     */
    public void vyberSuradnice(int x, int y) {
        if (this.hernaPlocha.getStavHry() == StavHry.NEDOHRANE) {
            int poziciaX = x / (this.velkost * 20);
            int poziciaY = y / (this.velkost * 20);
            
            this.hernaPlocha.odkryPolicko(poziciaX, poziciaY);
        }
    }
    
    /**
     * Metóda, ktorá označuje políčko na hernej ploche podľa polohy myši.
     */
    public void vyberDruheSuradnice(int x, int y) {
        if (this.hernaPlocha.getStavHry() == StavHry.NEDOHRANE) {
            int poziciaX = x / (this.velkost * 20);
            int poziciaY = y / (this.velkost * 20);

            this.hernaPlocha.oznacPolicko(poziciaX, poziciaY);
        }
    }
}
