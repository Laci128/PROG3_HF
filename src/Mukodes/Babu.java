package Mukodes;

import javax.swing.*;

/**
 *
 *
 */
public class Babu {
    /**
     * A Babu értéke, azért kell mert, ha 25 lépés után
     * nem történik változás a bábuk összértékben akkor döntetlen a játékeredménye.
     */
    private Integer ertek = 1;

    /**
     * A Babu színe (fekete vagy fehér).
     */
    private String szin;

    /**
     * Mezo amelyen a Babu jelenleg tartózkodik.
     */
    private Mezo jelenlegi_mezo;

    private JLabel babuLabel;


    public Babu(String sz){
        szin = sz;
        if(szin.equals("feher")){
            babuLabel = new JLabel(new ImageIcon("resources/feher.png"));
        }
        else
            babuLabel = new JLabel(new ImageIcon("resources/fekete.png"));
    }


    public void Lep(Mezo m){
        if(jelenlegi_mezo.getSzomszedok().contains(m) || m.getBabu() == null){
            jelenlegi_mezo.setBabu(null);
            jelenlegi_mezo = m;
            m.setBabu(this);
        }
    }

    public void Ugrik(){

    }


    public String getSzin() {
        return szin;
    }

    public void setSzin(String szin) {
        this.szin = szin;
    }

    public JLabel getBabuLabel() {
        return babuLabel;
    }

    public void setBabuLabel(JLabel babuLabel) {
        this.babuLabel = babuLabel;
    }



}
