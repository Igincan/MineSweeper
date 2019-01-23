
/**
 * Trieda, ktorá sa stará o vykreslenie sedem-segmentovej cislice.
 * 
 * @author Igor Novák
 * @version 21.12.2017
 */
public class Cislica {
    
    private Segment[] segment;
    private int cislo;
    private int velkost;
    
    /**
     * Konštruktor, kde sa inicializujú atribúty,
     */
    public Cislica(int x, int y, int cislo, int velkost) {
        this.segment = new Segment[7];
        this.cislo = cislo;
        this.velkost = velkost;
        
        for (int i = 0; i < 7; i++) {
            this.segment[i] = new Segment(x, y, i, this.velkost);
        }
    }
    
    /**
     * Metóda, ktorá vypína hru.
     */
    public void vypni() {
        for (int i = 0; i < 7; i++) {
            this.segment[i].vypni();
        }
    }
    
    /**
     * Metóda, ktorá zobrazuje čílicu.
     */
    public void zobraz() {
        switch(this.cislo) {
            case 0:
                for (int i = 0; i < 7; i++) {
                    if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5) {
                        this.segment[i].zviditelni();
                    } else {
                        this.segment[i].zneviditelni();
                    }
                }
                break;
            case 1:
                for (int i = 0; i < 7; i++) {
                    if (i == 1 || i == 2) {
                        this.segment[i].zviditelni();
                    } else {
                        this.segment[i].zneviditelni();
                    }
                }
                break;
            case 2:
                for (int i = 0; i < 7; i++) {
                    if (i == 0 || i == 1 || i == 3 || i == 4 || i == 6) {
                        this.segment[i].zviditelni();
                    } else {
                        this.segment[i].zneviditelni();
                    }
                }
                break;
            case 3:
                for (int i = 0; i < 7; i++) {
                    if (i == 0 || i == 1 || i == 2 || i == 3 || i == 6) {
                        this.segment[i].zviditelni();
                    } else {
                        this.segment[i].zneviditelni();
                    }
                }
                break;
            case 4:
                for (int i = 0; i < 7; i++) {
                    if (i == 1 || i == 2 || i == 5 || i == 6) {
                        this.segment[i].zviditelni();
                    } else {
                        this.segment[i].zneviditelni();
                    }
                }
                break;
            case 5:
                for (int i = 0; i < 7; i++) {
                    if (i == 0 || i == 2 || i == 3 || i == 5 || i == 6) {
                        this.segment[i].zviditelni();
                    } else {
                        this.segment[i].zneviditelni();
                    }
                }
                break;
            case 6:
                for (int i = 0; i < 7; i++) {
                    if (i == 0 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6) {
                        this.segment[i].zviditelni();
                    } else {
                        this.segment[i].zneviditelni();
                    }
                }
                break;
            case 7:
                for (int i = 0; i < 7; i++) {
                    if (i == 0 || i == 1 || i == 2) {
                        this.segment[i].zviditelni();
                    } else {
                        this.segment[i].zneviditelni();
                    }
                }
                break;
            case 8:
                for (int i = 0; i < 7; i++) {
                    this.segment[i].zviditelni();
                }
                break;
            case 9:
                for (int i = 0; i < 7; i++) {
                    if (i == 0 || i == 1 || i == 2 || i == 3 || i == 5 || i == 6) {
                        this.segment[i].zviditelni();
                    } else {
                        this.segment[i].zneviditelni();
                    }
                }
                break;
        }
    }
}
