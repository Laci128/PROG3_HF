package Jatek;

import Felulet.*;

/**
 * Az egész Dáma játékot vezérlő osztály.
 */

public class Jatek {
    /**
     *
     */
    private String kezdetiJatekos = "feher";

    /**
     *
     */
    private String jelenlegiJatekos = new String();


    public void setKezdetiJatekos(String kezdetiJatekos) {
        this.kezdetiJatekos = kezdetiJatekos;
    }

    public String getKezdetiJatekos() {
        return kezdetiJatekos;
    }

    public void setJelenlegiJatekos(String jelenlegiJatekos) {
        this.jelenlegiJatekos = jelenlegiJatekos;
    }

    public String getJelenlegiJatekos() {
        return jelenlegiJatekos;
    }

    public static void main(String[] args) {
        Jatek jatek = new Jatek();

        MenuFrame menuFrame = new MenuFrame();
        menuFrame.setVisible(true);


    }


}
