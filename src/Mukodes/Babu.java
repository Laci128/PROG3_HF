package Mukodes;

import javax.swing.*;
import java.io.Serializable;

/**
 * A játék egyes bábui, amikkel lépünk vagy ugrunk az adott körben.
 * Ősosztálya a Kiraly osztálynak.
 */
public class Babu implements Serializable {
    /**
     * A Babu értéke, azért kell mert, ha 25 lépés után
     * nem történik változás a bábuk összértékben akkor döntetlen a játékeredménye.
     */
    private Integer ertek = 1;

    /**
     * A Babu színe ("fekete" vagy "feher").
     */
    private String szin;

    /**
     * Mezo amelyen a Babu jelenleg tartózkodik.
     */
    private Mezo jelenlegiMezo;

    /**
     * A Babu-hoz tarozó JLabel
     */
    private JLabel babuLabel;

    /**
     * A Babu ugrási állapota, ugrott-e már vagy sem
     */
    private Boolean ugrott = false;

    /**
     * A Babu konstruktora
     * @param sz ezt kapja értekül kap a szin tagváltozó
     */
    public Babu(String sz){
        szin = sz;
        if(szin.equals("feher")){
            babuLabel = new JLabel(new ImageIcon("resources/feher.png"));
        }
        else
            babuLabel = new JLabel(new ImageIcon("resources/fekete.png"));
    }

    /**
     * A Babu lépő függvénye, meg kell adni egy Mezo-t ahova akar lépni.
     * Csak a másik szín fele tud lépni és csak egyet
     * @param celMezo a Mezo, amire lépni akar
     * @return true, ha sikerült oda lépni, false, ha nem
     */
    public boolean Lep(Mezo celMezo){
        if(jelenlegiMezo.getSzomszedok().contains(celMezo) && celMezo.getBabu() == null){
            if(szin.equals("feher")){
                if(celMezo.getSor() - jelenlegiMezo.getSor() == 1){
                    jelenlegiMezo.setBabu(null);
                    jelenlegiMezo = celMezo;
                    celMezo.setBabu(this);
                    return true;
                }
                else
                    return false;
            }
            else{
                if(szin.equals("fekete")) {
                    if (celMezo.getSor() - jelenlegiMezo.getSor() == -1) {
                        jelenlegiMezo.setBabu(null);
                        jelenlegiMezo = celMezo;
                        celMezo.setBabu(this);
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

    /**
     * A Babu ugró függvénye, szintén meg kell adni egy Mezo-t ahova akar ugrani
     * Bármilyen irányba tud ugrani, egyszerre csak egy Mezot, de egymás után többször is lehet ugrani
     * @param celMezo a Mezo, amire ugrani akar
     * @return Mezo amit átugrott, null ha nem tudott a celMezore átugrani
     */
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
                        kozosMezo.setBabu(null);
                        ugrott = true;
                        return kozosMezo;
                    }
                    else
                        return null;
                } else
                    return null;
            } else
                return null;
        }

        return null;
    }

    /**
     * A Király leszármazottjában van értelme, csak a kollekció miatt van itt.
     * Nem csinál semmit.
     * @param uresbenUgrott -
     */
    public void setUresbenUgrott(Boolean uresbenUgrott) {}

    /**
     * A Király leszármazottjában van értelme, csak a kollekció miatt van itt.
     * @param palya -
     */
    public void setPalya(Palya palya) {}

    /**
     * A Király leszármazottjában van értelme, csak a kollekció miatt van itt.
     * Itt ugyanazt csinálja, mint a getUgrott
     * @return ugrott tagváltozó
     */
    public boolean UgrottE(){
        return ugrott;
    }

    /**
     * A Király leszármazottjában van értelme, csak a kollekció miatt van itt.
     * Nincs értelme, mindig false-t ad vissza.
     * @return mindig false
     */
    public boolean TeljesenUgrott(){
        return false;
    }



    /**
     * szin Setter-e
     * @param szin ezt kapja értekül kap a szin tagváltozó
     */
    public void setSzin(String szin) {
        this.szin = szin;
    }
    /**
     * szin Getter-e
     * @return szin tagváltozó
     */
    public String getSzin() {
        return szin;
    }


    /**
     * ertek Getter-e
     * @return ertek tagváltozó
     */
    public Integer getErtek() {
        return ertek;
    }


    /**
     * babuLabel Getter-e
     * @return babuLabel tagváltozó
     */
    public JLabel getBabuLabel() {
        return babuLabel;
    }

    /**
     * jelenlegiMezo Setter-e
     * @param jelenlegiMezo ezt kapja értekül kap a jelenlegiMezo tagváltozó
     */
    public void setJelenlegiMezo(Mezo jelenlegiMezo) {
        this.jelenlegiMezo = jelenlegiMezo;
    }
    /**
     * jelenlegiMezo Getter-e
     * @return jelenlegiMezo tagváltozó
     */
    public Mezo getJelenlegiMezo() {
        return jelenlegiMezo;
    }

    /**
     * ugrott Setter-e
     * @param ugrott ezt kapja értekül kap az ugrott tagváltozó
     */
    public void setUgrott(Boolean ugrott) {
        this.ugrott = ugrott;
    }
    /**
     * ugrott Getter-e
     * @return ugrott tagváltozó
     */
    public Boolean getUgrott() {
        return ugrott;
    }

}
