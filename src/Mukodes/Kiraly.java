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
    private Mezo jelenlegiMezo;

    private JLabel babuLabel;

    private Boolean ugrott = false;

    private Palya palya;


    public Kiraly(String sz) {
        super(sz);
        szin = sz;
        if(szin.equals("feher"))
            babuLabel = new JLabel(new ImageIcon("resources/feher_kiraly.png"));
        else
           babuLabel = new JLabel(new ImageIcon("resources/fekete_kiraly.png"));
    }

    @Override
    public boolean Lep(Mezo m) {
        if(palya.szabadEAzUt(jelenlegiMezo,m)){
            jelenlegiMezo.setBabu(null);
            jelenlegiMezo = m;
            m.setBabu(this);
            return true;
        }
        else
            return false;
    }
/*
    @Override
    //Bármerre EGYET léphet
    public boolean Lep(Mezo m){
        if(jelenlegiMezo.getSzomszedok().contains(m) && m.getBabu() == null) {
            int sorTav = m.getSor() - jelenlegiMezo.getSor();
            if (Math.abs(sorTav) == 1) {
                jelenlegiMezo.setBabu(null);
                jelenlegiMezo = m;
                m.setBabu(this);
                return true;
            } else
                return false;
        }
        else
            return false;
    }
    */

    @Override
    public Mezo Ugrik(Mezo celMezo){
        return null;
    }


    @Override
    public Integer getErtek() {
        return ertek;
    }

    @Override
    public void setErtek(Integer ertek) {
        this.ertek = ertek;
    }

    @Override
    public Boolean getUgrott() {
        return ugrott;
    }

    @Override
    public void setUgrott(Boolean ugrott) {
        this.ugrott = ugrott;
    }

    @Override
    public String getSzin() {
        return szin;
    }

    @Override
    public void setSzin(String szin) {
        this.szin = szin;
    }

    @Override
    public JLabel getBabuLabel() {
        return babuLabel;
    }

    @Override
    public void setBabuLabel(JLabel babuLabel) {
        this.babuLabel = babuLabel;
    }

    @Override
    public Mezo getJelenlegiMezo() {
        return jelenlegiMezo;
    }

    @Override
    public void setJelenlegiMezo(Mezo jelenlegiMezo) {
        this.jelenlegiMezo = jelenlegiMezo;
    }

    @Override
    public void setPalya(Palya palya) {
        this.palya = palya;
    }

    @Override
    public Palya getPalya() {
        return palya;
    }
}
