package Mukodes;

import javax.swing.*;

/**
 * A játék egyes király típusú bábui, amikkel másképp lépünk vagy ugrunk,
 * mint a sima bábukkal, de szintén csak egyszerre az egyiket az adott körben.
 * Leszármazottja a Babu osztálynak.
 */
public class Kiraly extends Babu {
    /**
     * A Kiraly értéke, azért kell mert, ha 25 lépés után
     * nem történik változás a bábuk összértékben akkor döntetlen a játékeredménye.
     */
    private Integer ertek = 5;

    /**
     * A Kiraly színe (fekete vagy fehér).
     */
    private String szin;

    /**
     * Mezo amelyen a Kiraly jelenleg tartózkodik.
     */
    private Mezo jelenlegiMezo;

    /**
     * A Kiraly-hoz tarozó JLabel
     */
    private JLabel babuLabel;

    /**
     * A Kiraly ugrási állapota, ugrott-e már vagy sem
     */
    private Boolean ugrott = false;

    /**
     * A Kiraly üres ugrási állapota, ugrott-e már vagy sem üres Mezo-t
     */
    private Boolean uresbenUgrott = false;

    /**
     * A Palya amin a Kiraly tartózkodik, azért kell hogy meg tudja nézni,
     * hogy szabad-e az út adott irányba lépéshez.
     */
    private Palya palya;


    /**
     * Kiraly konstruktora
     * @param sz ezt kapja értekül kap a szin tagváltozó
     */
    public Kiraly(String sz) {
        super(sz);
        szin = sz;
        if(szin.equals("feher"))
            babuLabel = new JLabel(new ImageIcon("resources/feher_kiraly.png"));
        else
           babuLabel = new JLabel(new ImageIcon("resources/fekete_kiraly.png"));
    }

    /**
     * A Kiraly lépő függvénye, meg kell adni egy Mezo-t ahova akar lépni
     * @param celMezo a Mezo, amire lépni akar
     * @return true, ha sikerült oda lépni, false, ha nem
     */
    @Override
    public boolean Lep(Mezo celMezo) {
        if(palya.szabadEAzUt(jelenlegiMezo, celMezo)){
            jelenlegiMezo.setBabu(null);
            jelenlegiMezo = celMezo;
            celMezo.setBabu(this);
            return true;
        }
        else
            return false;
    }

    /**
     * A Kiraly ugró függvénye, szintén meg kell adni egy Mezo-t ahova akar ugrani.
     * Bármilyen irányba tud ugrani, egyszerre csak egy Mezot, de egymás után többször is lehet ugrani.
     * Emellett ha már ugrott, akkor sima ugrás helyett átugorhat egy üres Mezo-t is, de akkor utána már nem ugorhat
     * @param celMezo a Mezo, amire ugrani akar
     * @return Mezo amit átugrott, null ha nem tudott a celMezore átugrani
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


                    if (kozosMezo.getBabu() != null && !kozosMezo.getBabu().getSzin().equals(szin)) {
                        jelenlegiMezo.setBabu(null);
                        jelenlegiMezo = celMezo;
                        celMezo.setBabu(this);
                        kozosMezo.setBabu(null);
                        ugrott = true;
                        uresbenUgrott = false;
                        return kozosMezo;
                    }


                    if(ugrott && !uresbenUgrott && kozosMezo.getBabu() == null){
                        jelenlegiMezo.setBabu(null);
                        jelenlegiMezo = celMezo;
                        celMezo.setBabu(this);
                        uresbenUgrott = true;
                        return kozosMezo;
                    }

                    return null;
                } else
                    return null;
            } else
                return null;
        }

        return null;
    }

    /**
     * Megadja, hogy valahogy ugrott-e már a Kiraly
     * @return true, ha ugrott vagy üreset ugrott már, false egyébként
     */
    @Override
    public boolean UgrottE() {
        return (ugrott || uresbenUgrott);
    }

    /**
     * Igazat, ha ugrott és üreset ugrott is a Király
     * @return true, ha ugrott és üreset is ugrott, false egyébként
     */
    @Override
    public boolean TeljesenUgrott() {
        return (ugrott && uresbenUgrott);
    }

    /**
     * uresbenUgrott Setter-e
     * @param uresbenUgrott ezt kapja értekül kap a uresbenUgrott tagváltozó
     */
    @Override
    public void setUresbenUgrott(Boolean uresbenUgrott) {
        this.uresbenUgrott = uresbenUgrott;
    }

    /**
     * ertek Getter-e
     * @return ertek tagváltozó
     */
    @Override
    public Integer getErtek() {
        return ertek;
    }

    /**
     * palya Setter-e
     * @param palya ezt kapja értekül kap a palya tagváltozó
     */
    @Override
    public void setPalya(Palya palya) {
        this.palya = palya;
    }


}
