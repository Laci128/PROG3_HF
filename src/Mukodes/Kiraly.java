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

    private Boolean uresbenUgrott = false;


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
        Mezo[] kozosSzomszedok = new Mezo[2];
        int index = 0;
        for(Mezo mezo: celMezo.getSzomszedok()){
            if(celMezo.getSzomszedok().contains(mezo) && jelenlegiMezo.getSzomszedok().contains(mezo)){
                kozosSzomszedok[index] = mezo;
                index++;
            }
        }
        if(kozosSzomszedok[0] == null)
            return null;

        for (Mezo kozosMezo: kozosSzomszedok) {
            if ((celMezo.getSor() - jelenlegiMezo.getSor() == 2 && kozosMezo.getSor() - jelenlegiMezo.getSor() == 1)
                    || (celMezo.getSor() - jelenlegiMezo.getSor() == -2 && kozosMezo.getSor() - jelenlegiMezo.getSor() == -1)){
                if ((celMezo.getOszlop() - jelenlegiMezo.getOszlop() == 2 && kozosMezo.getOszlop() - jelenlegiMezo.getOszlop() == 1)
                        || (celMezo.getOszlop() - jelenlegiMezo.getOszlop() == -2 && kozosMezo.getOszlop() - jelenlegiMezo.getOszlop() == -1)) {


                    boolean celMezoSzomszedBabuVanE = false;
                    for(Mezo m: celMezo.getSzomszedok()){
                        if(m.getBabu() != null && m != kozosMezo){
                            celMezoSzomszedBabuVanE = true;
                            break;
                        }
                    }

                    if(!ugrott && !uresbenUgrott && kozosMezo.getBabu() == null && celMezoSzomszedBabuVanE){
                        jelenlegiMezo.setBabu(null);
                        jelenlegiMezo = celMezo;
                        celMezo.setBabu(this);
                        uresbenUgrott = true;
                        return kozosMezo;
                    }
                    //ugrott            false
                    //uresbenUgrott     true

                    if (kozosMezo.getBabu() != null && !kozosMezo.getBabu().getSzin().equals(szin)) {
                        jelenlegiMezo.setBabu(null);
                        jelenlegiMezo = celMezo;
                        celMezo.setBabu(this);
                        ugrott = true;
                        uresbenUgrott = false;
                        return kozosMezo;
                    }
                    //ugrott            true
                    //uresbenUgrott     false

                    if(ugrott && !uresbenUgrott && kozosMezo.getBabu() == null){
                        jelenlegiMezo.setBabu(null);
                        jelenlegiMezo = celMezo;
                        celMezo.setBabu(this);
                        uresbenUgrott = true;
                        return kozosMezo;
                    }
                    //ugrott            true
                    //uresbenUgrott     true

                    return null;
                } else
                    return null;
            } else
                return null;
        }

        return null;
    }


    @Override
    public boolean Ugrott() {
        return (ugrott || uresbenUgrott);
    }

    @Override
    public boolean TeljesenUgrott() {
        return (ugrott && uresbenUgrott);
    }

    @Override
    public void setUresbenUgrott(Boolean uresbenUgrott) {
        this.uresbenUgrott = uresbenUgrott;
    }

    public Boolean getUresbenUgrott() {
        return uresbenUgrott;
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
