import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Trieda vytvára celý program a stará sa o menu. Je spravovaná manažérom. Je singleton.
 * 
 * @author Igor Novák
 * @version 21.12.2017
 */
public class Aplikacia {
    
    private static Aplikacia aplikacia = new Aplikacia();
    
    private ArrayList<Obtiaznost> obtiaznosti;
    private Hra hra;
    private Manazer manazer;
    private int indexObtiaznosti;;
    
    /**
     * Konštruktor, kde sa inizializujú atribúty.
     */
    private Aplikacia() {
        this.obtiaznosti = new ArrayList<Obtiaznost>();
        //this.obtiaznosti.add(new Obtiaznost(15, 10, 10, "Ľahká"));
        //this.obtiaznosti.add(new Obtiaznost(40, 20, 20, "Stredná"));
        this.hra = null;
        this.manazer = null;
        this.indexObtiaznosti = 0;
        this.nacitajObtiaznosti();
        this.spustMenu();
    }
    
    /**
     * Statická metóda main, ktorá sa vykoná pri spustení programu.
     */
    public static void main(String[] args) {
        Aplikacia.getInstance();
    }
    
    /**
     * Statická metóda, ktorá vracia inštanciu triedy Aplikacia.
     */
    public static Aplikacia getInstance() {
        return Aplikacia.aplikacia;
    }
    
    /**
     * Metóda, ktorá načítava dáta o obtiažnostiach zo suboru.
     */
    private void nacitajObtiaznosti() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("obtiaznosti.txt"));
            String riadok;
            int pocitadlo = 0;
            int pocetMin = 0;
            int pocetX = 0;
            int pocetY = 0;
            String nazov = null;
            while ((riadok = in.readLine()) != null) {
                switch (pocitadlo++) {
                    case 0:
                        pocetMin = Integer.parseInt(riadok);
                        break;
                    case 1:
                        pocetX = Integer.parseInt(riadok);
                        break;
                    case 2:
                        pocetY = Integer.parseInt(riadok);
                        break;
                    case 3:
                        nazov = riadok;
                        this.obtiaznosti.add(new Obtiaznost(pocetMin, pocetX, pocetY, nazov));
                        pocitadlo = 0;
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    /**
     * Metóda, ktorá spúšťa hlavné menu.
     */
    public void spustMenu() {
        boolean koniec = false;
        Scanner sc = new Scanner(System.in);
        while (!koniec) {
            System.out.printf("%n%s%n%s%n%s%n%s%n%s%n", "1: Spusť hru", "2: Vypíš obtiažnosti", "3: Pridaj obtiažnosť", "4: Ukonči program", "____________________");
            int zadanie = sc.nextInt();
            switch (zadanie) {
                case 1:
                    int index = 0;
                    for (Obtiaznost obtiaznost : this.obtiaznosti) {
                        System.out.printf("%s%s%s", "" + index++, ": ", obtiaznost.getNazov());
                        System.out.println();
                    }
                    System.out.println("____________________");
                    this.spustHru(sc.nextInt());
                    koniec = true;
                    break;
                case 2:
                    this.vypisObtiaznosti();
                    break;
                case 3:
                    boolean validne;
                    do {
                        System.out.println();
                        System.out.println("Zadaj počet stĺpcov/riadkov: ");
                        System.out.println("____________________");
                        int pocetSAR = sc.nextInt();
                        System.out.println("Zadaj počet mín: ");
                        System.out.println("____________________");
                        int pocetMin = sc.nextInt();
                        System.out.println("Zadaj názov obtiažnosti: ");
                        System.out.println("____________________");
                        String nazov = sc.next();
                        validne = this.pridajObtiaznost(pocetMin, pocetSAR, pocetSAR, nazov);
                    } while (!validne);
                    break;
                case 4:
                    System.exit(0);
            }
        }
    }
    
    /**
     * Metóda, ktorá spúšťa hru.
     */
    public void spustHru(int indexObtiaznosti) {
        this.hra = new Hra(this.obtiaznosti.get(indexObtiaznosti), this);
        this.indexObtiaznosti = indexObtiaznosti;
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
    }
    
    /**
     * Metóda, ktorá pridáva obtiažnosť.
     */
    public boolean pridajObtiaznost(int pocetMin, int pocetStlpcov, int pocetRiadkov, String nazovObtiaznosti) {
        boolean validne = false;
        if (pocetStlpcov == pocetRiadkov && 40 % pocetRiadkov == 0) {
            if (!(pocetStlpcov > 40 || pocetRiadkov > 40)) {
                if (!(pocetStlpcov < 2 || pocetRiadkov < 2)) {
                    if (!(pocetStlpcov * pocetRiadkov < pocetMin)) {
                        this.obtiaznosti.add(new Obtiaznost(pocetMin, pocetStlpcov, pocetRiadkov, nazovObtiaznosti));
                        validne = true;
                    } else {
                        System.out.println("Príliš veľa mín!");
                    }
                } else {
                    System.out.println("Plocha je moc malá!");
                }
            } else {
                System.out.println("Plocha je moc veľká!");
            }
        } else {
            System.out.println("Plocha nemá správne rozmery!");
            System.out.println("(počet stĺpcov = počet riadkov)");
            System.out.println("(číšlo 40 musí byť deliteľné počtom riadkov)");
        }
        return validne;
    }
    
    /**
     * Metóda, ktorá vypisuje obtiažnosti.
     */
    public void vypisObtiaznosti() {
        System.out.printf("%-25s %-25s %-25s %n", "Názov obtiažnosti:", "Rozmery plochy:", "Počet mín:");
        for (Obtiaznost obtiaznost : this.obtiaznosti) {
            System.out.printf("%-25s %-25s %-25s %n", obtiaznost.getNazov(), obtiaznost.getPocetX() + "x" + obtiaznost.getPocetY(), obtiaznost.getPocetMin());
        }
    }
    
    /**
     * Metóda, ktorá vypína hru.
     */
    public void vypniHru() {
        //this.hra.vypni();
        this.hra = null;
        this.manazer = null;
        this.indexObtiaznosti = 0;
    }
    
    /**
     * Metóda, ktorá sa vykoná po stisknutí medzerníka. Začína hru odznova na rovnakej obtiažnosti.
     */
    public void aktivuj() {
        this.spustHru(this.indexObtiaznosti);
    }
    
    /**
     * Metóda, ktorá sa vykoná pri ľavom kliknutí myšou a zistí súradnice kurzora.
     */
    public void vyberSuradnice(int x, int y) {
        this.hra.vyberSuradnice(x, y);
    }
    
    /**
     * Metóda, ktorá sa vykoná pri pravom kliknutí myšou a zistí súradnice kurzora.
     */
    public void vyberDruheSuradnice(int x, int y) {
        this.hra.vyberDruheSuradnice(x, y);
    }
    
    /**
     * Metóda, ktorá sa vykoná pri stlačení klávesu "esc". Vypne hru a vráti sa do hlavného menu.
     */
    public void zrus() {
        this.vypniHru();
        System.out.println("Spust Menu!");
        this.spustMenu();
    }
}