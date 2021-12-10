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
    private Mezo jelenlegiMezo;

    private JLabel babuLabel;

    private Boolean ugrott = false;

    public Babu(String sz){
        szin = sz;
        if(szin.equals("feher")){
            babuLabel = new JLabel(new ImageIcon("resources/feher.png"));
        }
        else
            babuLabel = new JLabel(new ImageIcon("resources/fekete.png"));
    }


    public boolean Lep(Mezo m){
        if(jelenlegiMezo.getSzomszedok().contains(m) && m.getBabu() == null){
            if(szin.equals("feher")){
                if(m.getSor() - jelenlegiMezo.getSor() == 1){
                    jelenlegiMezo.setBabu(null);
                    jelenlegiMezo = m;
                    m.setBabu(this);
                    return true;
                }
                else
                    return false;
            }
            else{
                if(szin.equals("fekete")) {
                    if (m.getSor() - jelenlegiMezo.getSor() == -1) {
                        jelenlegiMezo.setBabu(null);
                        jelenlegiMezo = m;
                        m.setBabu(this);
                        return true;
                    } else
                        return false;
                }
            }
        }
        else{
            return false;
        }
        return false;
    }


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

                    if (kozosMezo.getBabu() != null && !kozosMezo.getBabu().getSzin().equals(szin)) {
                        jelenlegiMezo.setBabu(null);
                        jelenlegiMezo = celMezo;
                        celMezo.setBabu(this);
                        ugrott = true;
                        return kozosMezo;
                    } else
                        return null;
                } else
                    return null;
            } else
                return null;
        }

        return null;
    }


    public String getSzin() {
        return szin;
    }

    public void setSzin(String szin) {
        this.szin = szin;
    }

    public Integer getErtek() {
        return ertek;
    }

    public void setErtek(Integer ertek) {
        this.ertek = ertek;
    }

    public JLabel getBabuLabel() {
        return babuLabel;
    }

    public void setBabuLabel(JLabel babuLabel) {
        this.babuLabel = babuLabel;
    }

    public void setJelenlegiMezo(Mezo jelenlegiMezo) {
        this.jelenlegiMezo = jelenlegiMezo;
    }

    public Mezo getJelenlegiMezo() {
        return jelenlegiMezo;
    }

    public Boolean getUgrott() {
        return ugrott;
    }

    public void setUgrott(Boolean ugrott) {
        this.ugrott = ugrott;
    }

    public void setPalya(Palya palya) {}

    public Palya getPalya() {return null;}

    public void setUresbenUgrott(Boolean uresbenUgrott) {}

    public Boolean getUresbenUgrott() {return true;}

    }
