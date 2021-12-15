package Mukodes;

import java.io.Serializable;
import java.util.ArrayList;
import java.lang.Math;

/**
 *  A játéktér állapotát generálja, tárolja és változtatja (Mezok, Babuk, stb.)
 */
public class Palya implements Serializable {
    /**
     * A Palya Mezői, a pálya 8x8-as de csak a fehér mezőket tárolom,
     * mert csak azon lépkednek a bábuk.
     */
    private ArrayList<Mezo> mezok = new ArrayList<Mezo>();

    /**
     * Atmeneti lista, ami arra kell, hogy egy mezo szomszédait összegyűjt
     */
    private ArrayList<Mezo> temp_szomszedok;

    /**
     * A Palya-n jelenleg rajta lévő Babu-k összértéke.
     */
    private Integer palyaErteke;

    /**
     * Automatkus döntetlening hátra lévő körök száma.
     */
    private Integer korDontetlenig = 25;

    /**
     * A jelenlegi játékos színe, csak szerializáskor változik.
     */
    private String jelenlegiJatekos;
    /**
     * A kiválasztott bábu, csak szerializáskor változik.
     */
    private Babu kivalasztottBabu;

    /**
     * A Palya konstruktora
     */
    public Palya() {

        Babu feherBabu;
        Babu feketeBabu;
        for (int SOR = 1; SOR <= 7; SOR += 2) {
            for (int OSZLOP = 1; OSZLOP <= 7; OSZLOP += 2) {
                Mezo uj_mezo = new Mezo(SOR, OSZLOP);
                if(SOR <=3) {
                    feherBabu = new Babu("feher");
                    uj_mezo.setBabu(feherBabu);
                    uj_mezo.getMezoPanel().add(feherBabu.getBabuLabel());
                    feherBabu.setJelenlegiMezo(uj_mezo);
                }

                if(SOR >= 6) {
                    feketeBabu = new Babu("fekete");
                    uj_mezo.setBabu(feketeBabu);
                    uj_mezo.getMezoPanel().add(feketeBabu.getBabuLabel());
                    feketeBabu.setJelenlegiMezo(uj_mezo);
                }
                mezok.add(uj_mezo);
            }
        }

        for (int SOR = 2; SOR <= 8; SOR += 2) {
            for (int OSZLOP = 2; OSZLOP <= 8; OSZLOP += 2) {
                Mezo uj_mezo = new Mezo(SOR, OSZLOP);
                if(SOR <=3) {
                    feherBabu = new Babu("feher");
                    uj_mezo.setBabu(feherBabu);
                    uj_mezo.getMezoPanel().add(feherBabu.getBabuLabel());
                    feherBabu.setJelenlegiMezo(uj_mezo);
                }

                if(SOR >= 6) {
                    feketeBabu = new Babu("fekete");
                    uj_mezo.setBabu(feketeBabu);
                    uj_mezo.getMezoPanel().add(feketeBabu.getBabuLabel());
                    feketeBabu.setJelenlegiMezo(uj_mezo);
                }
                mezok.add(uj_mezo);
            }
        }

        //
        //Szomszédok beállítása
        //
        for (Mezo m : mezok) {
            temp_szomszedok = new ArrayList<Mezo>();
            for (Mezo sz : mezok) {
                if ((Math.abs(m.getSor() - sz.getSor())) == 1) {
                    if ((Math.abs(m.getOszlop() - sz.getOszlop())) == 1)
                        temp_szomszedok.add(sz);
                }
            }
            m.setSzomszedok(temp_szomszedok);
        }

        palyaErteke = palyanLevoBabukErteke();
    }

    /**
     * Összeeadja a Palya-n jelenleg rajta lévő Babu-k értékét.
     * @return a Palya-n jelenleg rajta lévő Babu-k összértéke.
     */
    public int palyanLevoBabukErteke(){
        int ertek = 0;
        for(Mezo m: mezok){
            if(m.getBabu() != null)
                ertek += m.getBabu().getErtek();
        }
        return ertek;
    }

    /**
     * Leellenőrzi, hogy valamelyik színű játékos nyert-e (a másik színnek nincs már Babu-ja).
     * @return a nyertes játékos színe vagy null, ha nem nyert senki
     */
    public String nyertEValaki(){
        Boolean vanEFeherBabu = false;
        Boolean vanEFeketeBabu = false;

        for(Mezo m: mezok) {
            if (m.getBabu() != null) {
                if (m.getBabu().getSzin().equals("feher"))
                    vanEFeherBabu = true;
                else
                    vanEFeketeBabu = true;
            }
        }

        if(vanEFeherBabu && !vanEFeketeBabu)
            return "feher";
        if(!vanEFeherBabu && vanEFeketeBabu)
            return "fekete";

        return null;

    }

    /**
     * Megadja ,hogy egy mezőtől viszonyítva valamennyi sorral és oszloppal arrébb lévő mező üres-e
     * @param jelenlegiMezo viszonyításhoz használt mező
     * @param sor arrébb lévő sor szám
     * @param oszlop arrébb lévő oszlop szám
     * @return true, ha üres  false, ha nem.
     */
    private boolean arrebbLevoMezoUresE(Mezo jelenlegiMezo, int sor, int oszlop){
        Mezo vizsgaltMezo = null;
        for(Mezo mezo: mezok){
            if(mezo.getSor() == (jelenlegiMezo.getSor()+sor) && mezo.getOszlop() == (jelenlegiMezo.getOszlop())+oszlop)
                vizsgaltMezo = mezo;
        }
        return (vizsgaltMezo != null && vizsgaltMezo.getBabu() == null);

    }

    /**
     * Megadja, hogy két mező közötti összes mező üres-e vagy sem.
     * @param jelenlegiMezo egyik mező
     * @param celMezo másik mező
     * @return true, ha az összes üres, false, ha nem
     */
    public boolean szabadEAzUt(Mezo jelenlegiMezo,Mezo celMezo){
        int sorTav = celMezo.getSor() - jelenlegiMezo.getSor();
        int oszlopTav = celMezo.getOszlop() - jelenlegiMezo.getOszlop();

        //jobbra le
        if(sorTav > 0 && oszlopTav > 0) {
            int sor = 1;
            int oszlop = 1;
            while (sor < sorTav && oszlop < oszlopTav) {
                if (!arrebbLevoMezoUresE(jelenlegiMezo, sor, oszlop))
                    return false;
                oszlop++;
                sor++;
            }
            return true;
        }

        //balra le
        if(sorTav > 0 && oszlopTav < 0) {
            int sor = 1;
            int oszlop = -1;
            while (sor < sorTav && oszlop > oszlopTav) {
                if (!arrebbLevoMezoUresE(jelenlegiMezo, sor, oszlop))
                    return false;
                oszlop--;
                sor++;
            }
            return true;
        }

        //jobbra fel
        if(sorTav < 0 && oszlopTav > 0) {
            int sor = -1;
            int oszlop = 1;
            while (sor > sorTav && oszlop < oszlopTav) {
                if (!arrebbLevoMezoUresE(jelenlegiMezo, sor, oszlop))
                    return false;
                oszlop++;
                sor--;
            }
            return true;
        }

        //balra fel
        if(sorTav < 0 && oszlopTav < 0) {
            int sor = -1;
            int oszlop = -1;
            while (sor > sorTav && oszlop > oszlopTav) {
                if (!arrebbLevoMezoUresE(jelenlegiMezo, sor, oszlop))
                    return false;
                oszlop--;
                sor--;
            }
            return true;
        }

        return false;
    }



    /**
     * mezok Getter-e
     * @return mezok tagváltozó
     */
    public ArrayList<Mezo> getMezok() {
        return mezok;
    }

    /**
     * palyaErteke Getter-e
     * @return palyaErteke tagváltozó
     */
    public Integer getPalyaErteke() {
        return palyaErteke;
    }

    /**
     * korDontetlenig Setter-e
     * @param korDontetlenig ezt kapja értekül kap a korDontetlenig tagváltozó
     */
    public void setKorDontetlenig(Integer korDontetlenig) {
        this.korDontetlenig = korDontetlenig;
    }
    /**
     * korDontetlenig Getter-e
     * @return korDontetlenig tagváltozó
     */
    public Integer getKorDontetlenig() {
        return korDontetlenig;
    }

    /**
     * jelenlegiJatekos Setter-e
     * @param jelenlegiJatekos ezt kapja értekül kap a jelenlegiJatekos tagváltozó
     */
    public void setJelenlegiJatekos(String jelenlegiJatekos) {
        this.jelenlegiJatekos = jelenlegiJatekos;
    }
    /**
     * jelenlegiJatekos Getter-e
     * @return jelenlegiJatekos tagváltozó
     */
    public String getJelenlegiJatekos() {
        return jelenlegiJatekos;
    }

    /**
     * kivalasztottBabu Setter-e
     * @param kivalasztottBabu ezt kapja értekül kap a kivalasztottBabu tagváltozó
     */
    public void setKivalasztottBabu(Babu kivalasztottBabu) {
        this.kivalasztottBabu = kivalasztottBabu;
    }
    /**
     * kivalasztottBabu Getter-e
     * @return kivalasztottBabu tagváltozó
     */
    public Babu getKivalasztottBabu() {
        return kivalasztottBabu;
    }

}
