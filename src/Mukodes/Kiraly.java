package Mukodes;

import javax.swing.*;

/**
 *
 *
 */
public class Kiraly extends Babu {
    /**
     * A Babu értéke, azért kell mert, ha 25 lépés után
     * nem történik változás a bábuk összértékben akkor döntetlen a játékeredménye.
     */
    private Integer ertek = 5;

    /**
     * A Babu színe (fekete vagy fehér).
     */
    private String szin;

    /**
     * Mezo amelyen a Babu jelenleg tartózkodik.
     */
    private Mezo jelenlegi_mezo;

    private JLabel babuLabel;




    public Kiraly(String sz) {
        super(sz);
        if(szin.equals("feher")){
            babuLabel = new JLabel(new ImageIcon("resources/feher_kiraly.png"));
        }
        else
            new JLabel(new ImageIcon("resources/fekete_kiraly.png"));
    }

    @Override
    public void Lep(Mezo m) {

    }

    @Override
    public void Ugrik() {

    }
}
