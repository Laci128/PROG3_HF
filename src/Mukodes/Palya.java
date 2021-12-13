package Mukodes;

import java.io.Serializable;
import java.util.ArrayList;
import java.lang.Math;


//A pálya ami a játéktér mezőit tárolja. //és kezeli?
//Legenerálja a Palyat és törli a régi mezőket.
/**
 *  A teljes játék működését bonyolítja le.
 */
public class Palya implements Serializable {
    /**
     * A Palya mezői, a pálya 8x8-as de csak a mezők felét tárolom,
     * mert csak azon lépkednek a bábuk.
     */
    //Csak a fehér mezők, SPECIFIKÁCIÓBAN ELÍRTAM, FEKETÉT ÍRTAM
    private ArrayList<Mezo> mezok = new ArrayList<Mezo>();

    private ArrayList<Mezo> temp_szomszedok;

    private Babu feherBabu = new Babu("feher");
    private Babu feketeBabu = new Babu("fekete");

    private Integer palyaErteke = 0;
    private Integer korDontetlenig = 25;

    /**
     * Csak szerializás miatt
     */
    private String jelenlegiJatekos;
    /**
     * Csak szerializás miatt
     */
    private Babu kivalasztottBabu;


    public Palya() {

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

    public int palyanLevoBabukErteke(){
        int ertek = 0;
        for(Mezo m: mezok){
            if(m.getBabu() != null)
                ertek += m.getBabu().getErtek();
        }
        return ertek;
    }

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


    private boolean uresE(Mezo jelenlegiMezo, int sor, int oszlop){
        Mezo vizsgaltMezo = null;
        for(Mezo mezo: mezok){
            if(mezo.getSor() == (jelenlegiMezo.getSor()+sor) && mezo.getOszlop() == (jelenlegiMezo.getOszlop())+oszlop)
                vizsgaltMezo = mezo;
        }
        if(vizsgaltMezo != null && vizsgaltMezo.getBabu() == null)
            return true;
        else
            return false;

    }

    public boolean szabadEAzUt(Mezo jelenlegiMezo,Mezo celMezo){
        int sorTav = celMezo.getSor() - jelenlegiMezo.getSor();
        int oszlopTav = celMezo.getOszlop() - jelenlegiMezo.getOszlop();

        //jobbra le
        if(sorTav > 0 && oszlopTav > 0) {
            int sor = 1;
            int oszlop = 1;
            while (sor < sorTav && oszlop < oszlopTav) {
                if (!uresE(jelenlegiMezo, sor, oszlop))
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
                if (!uresE(jelenlegiMezo, sor, oszlop))
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
                if (!uresE(jelenlegiMezo, sor, oszlop))
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
                if (!uresE(jelenlegiMezo, sor, oszlop))
                    return false;
                oszlop--;
                sor--;
            }
            return true;
        }

        return false;
    }


    public ArrayList<Mezo> getMezok() {
        return mezok;
    }

    public void setPalyaErteke(Integer palyaErteke) {
        this.palyaErteke = palyaErteke;
    }

    public Integer getPalyaErteke() {
        return palyaErteke;
    }

    public void setKorDontetlenig(Integer korDontetlenig) {
        this.korDontetlenig = korDontetlenig;
    }

    public Integer getKorDontetlenig() {
        return korDontetlenig;
    }

    public void setJelenlegiJatekos(String jelenlegiJatekos) {
        this.jelenlegiJatekos = jelenlegiJatekos;
    }

    public String getJelenlegiJatekos() {
        return jelenlegiJatekos;
    }


    public void setKivalasztottBabu(Babu kivalasztottBabu) {
        this.kivalasztottBabu = kivalasztottBabu;
    }

    public Babu getKivalasztottBabu() {
        return kivalasztottBabu;
    }

}
