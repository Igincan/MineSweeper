import java.util.Random;

/**
 * Trieda, ktorá sa stará o hernú plochu hry.
 * 
 * @author Igor Novák
 * @version 21.12.2017
 */
public class HernaPlocha {
    
    private Policko[][] policko;
    private int pocetX;
    private int pocetY;
    private int velkost;
    private int pocetMin;
    private int pocetOznacenychMin;
    private int pocetOdkrytychPolicok;
    private StavHry stavHry;
    private String nazovObtiaznosti;
    
    /**
     * Konštruktor inštancie, kde definujeme atribúty, ktorý zároveň generuje čo sa bude pod jednotlivými
     * políčkami nachádzať.
     * @param pocetX počet stĺpcov hernej plochy
     * @param pocetY počet riadkov hernej plochy
     * @param pocetMin počet mín ukrytých v hernej ploche
     * @param velkost premenná podľa ktorej sa určuje veľkost objektov
     */
    public HernaPlocha(int pocetX, int pocetY, int pocetMin, int velkost) {
        this.pocetX = pocetX;
        this.pocetY = pocetY;
        this.velkost = velkost;
        this.pocetMin = pocetMin;
        this.pocetOznacenychMin = 0;
        this.pocetOdkrytychPolicok = 0;
        
        this.policko = new Policko[this.pocetX][this.pocetY];
        this.stavHry = StavHry.NEDOHRANE;
        //generuje pole políčok
        for (int i = 0; i < this.pocetX; i++) {
            for (int j = 0; j < this.pocetY; j++) {
                this.policko[i][j] = new Policko(i, j, 0, this.velkost, this.stavHry);
            }
        }
        //schováva míny do náhodný políčok
        Random generator = new Random();
        int pocitadlo = 0;
        while (pocitadlo < this.pocetMin) {
            int poziciaX = generator.nextInt(this.pocetX);
            int poziciaY = generator.nextInt(this.pocetY);
            if (!this.policko[poziciaX][poziciaY].getSkryvaMinu()) {
                this.policko[poziciaX][poziciaY].schovajMinu();
            } else {
                pocitadlo--;
            }
            pocitadlo++;
        }
        //zisťuje počet okolitých políčok s mínami a zapísuje do inštancie triedy Policko
        for (int i = 0; i < this.pocetX; i++) {
            for (int j = 0; j < this.pocetY; j++) {
                int pocetOkolitychMin = 0;
                int susedneX = i;
                int susedneY = j;
                
                pocetOkolitychMin += this.getPocetMinVPolicku(--susedneX, --susedneY);
                pocetOkolitychMin += this.getPocetMinVPolicku(++susedneX, susedneY);
                pocetOkolitychMin += this.getPocetMinVPolicku(++susedneX, susedneY);
                pocetOkolitychMin += this.getPocetMinVPolicku(susedneX, ++susedneY);
                pocetOkolitychMin += this.getPocetMinVPolicku(susedneX, ++susedneY);
                pocetOkolitychMin += this.getPocetMinVPolicku(--susedneX, susedneY);
                pocetOkolitychMin += this.getPocetMinVPolicku(--susedneX, susedneY);
                pocetOkolitychMin += this.getPocetMinVPolicku(susedneX, --susedneY);
                
                this.policko[i][j].setPocetOkolitychMin(pocetOkolitychMin);
            }
        }
    }
    
    /**
     * Metóda, ktorá vypína hru.
     */
    public void vypni() {
        System.out.println("Vypina jednotlive policka");
        for (int i = 0; i < this.pocetX; i++) {
            for (int j = 0; j < this.pocetY; j++) {
                this.policko[i][j].vypni();
            }
        }
    }
    
    /**
     * Metóda inštancie, ktorá odkrýva políčko podľa zadaných súradníc a zisťuje výhru, či prehru hráča.
     * @param poziciaX x-ová súradnica odkrývaného políčka
     * @param poziciaY y-ová súradnica odkrývaného políčka
     */
    public void odkryPolicko(int poziciaX, int poziciaY) {
        //ak sa políčko nachádza v poli políčok tak ho odkryje
        if (poziciaX >= 0 && poziciaX < this.pocetX && poziciaY >= 0 && poziciaY < this.pocetY) {
            if (!this.policko[poziciaX][poziciaY].getJeOdkryte()) {
                this.policko[poziciaX][poziciaY].odkry();
                // zisťuje či hráč prehral:
                // ak áno, tak zafarbí všetky neoznačené políčka s mínami na žlto a zle označené míny odznačí
                // ak nie, tak zistí či hráč vyhral
                if (this.policko[poziciaX][poziciaY].getSkryvaMinu()) {
                    this.stavHry = StavHry.PREHRA;
                    for (int i = 0; i < this.pocetX; i++) {
                        for (int j = 0; j < this.pocetY; j++) {
                            if (this.policko[i][j].getSkryvaMinu() && !this.policko[i][j].getJeOznacene() && !(i == poziciaX && j == poziciaY)) {
                                this.policko[i][j].zmenFarbu("yellow");
                            } else if (!this.policko[i][j].getSkryvaMinu() && this.policko[i][j].getJeOznacene()) {
                                this.policko[i][j].oznac();
                            }
                        }
                    }
                } else {
                    // zisťuje či hráč vyhral:
                    // ak áno, tak označí na zeleno všetky políćka s mínami
                    if (++this.pocetOdkrytychPolicok == this.pocetX * this.pocetY - this.pocetMin) {
                        this.stavHry = StavHry.VYHRA;
                        for (int i = 0; i < this.pocetX; i++) {
                            for (int j = 0; j < this.pocetY; j++) {
                                if (this.policko[i][j].getSkryvaMinu()) {
                                    this.policko[i][j].oznacNaZeleno();
                                }
                            }
                        }
                    }
                }
                // ak sa v danom políčku ani v jeho okolí nenachádzajú žiadné míny,
                // tak všetky okolité políčka odkryje
                if (this.policko[poziciaX][poziciaY].getPocetOkolitychMin() == 0 && !this.policko[poziciaX][poziciaY].getSkryvaMinu()) {
                    this.odkryPolicko(poziciaX - 1, poziciaY - 1);
                    this.odkryPolicko(poziciaX, poziciaY - 1);
                    this.odkryPolicko(poziciaX + 1, poziciaY - 1);
                    this.odkryPolicko(poziciaX + 1, poziciaY);
                    this.odkryPolicko(poziciaX + 1, poziciaY + 1);
                    this.odkryPolicko(poziciaX, poziciaY + 1);
                    this.odkryPolicko(poziciaX - 1, poziciaY + 1);
                    this.odkryPolicko(poziciaX - 1, poziciaY);
                }
            }
        }
    }
    
    /**
     * Metóda inštancie, ktorá označuje alebo odznačuje políčko podľa zadaných súradníc.
     * @param poziciaX x-ová súradnica označovaného políčka
     * @param poziciaY y-ová súradnica označovaného políčka
     */
    public void oznacPolicko(int poziciaX, int poziciaY) {
        if (!this.policko[poziciaX][poziciaY].getJeOdkryte()) {
            this.policko[poziciaX][poziciaY].oznac();
        }
    }
    
    /**
     * Metóda inštancie, ktorá skrýva mínu do políčka podľa zadaných súradníc.
     * @param poziciaX x-ová súradnica políčka do ktorého sa skrýva mína
     * @param poziciaY y-ová súradnica políčka do ktorého sa skrýva mína
     */
    private void schovajMinuDoPolicka(int poziciaX, int poziciaY) {
        this.policko[poziciaX][poziciaY].schovajMinu();
    }
    
    /**
     * Metóda inštancie, ktorá vracia momentálny stav hry. (výhra, prehra, nedohrané)
     */
    public StavHry getStavHry() {
        return this.stavHry;
    }
    
    /**
     * Metóda inštancie, ktorá vracia počet mín v políčku podľa zadaných súradníc.
     * @param poziciaX x-ová súradnica daného políčka
     * @param poziciaY y-ová súradnica daného políčka
     */
    private int getPocetMinVPolicku(int poziciaX, int poziciaY) {
        if (poziciaX >= 0 && poziciaY >= 0 && poziciaX < this.pocetX && poziciaY < this.pocetY) {
            if (this.policko[poziciaX][poziciaY].getSkryvaMinu()) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }
}
