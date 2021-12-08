package Mukodes;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;


//A pálya ami a játéktér mezőit tárolja. //és kezeli?
//Legenerálja a Palyat és törli a régi mezőket.
/**
 *  A teljes játék működését bonyolítja le.
 */
public class Palya {
    /**
     * A Palya mezői, a pálya 8x8-as de csak a mezők felét tárolom,
     * mert csak azon lépkednek a bábuk.
     */
    //Csak a fehér mezők, SPECIFIKÁCIÓBAN ELÍTRTAM, FEKETÉT ÍRTAM
    private List<Mezo> mezok = new ArrayList<Mezo>();

    private List<Mezo> temp_szomszedok;

    private Babu feherBabu = new Babu("feher");
    private Babu feketeBabu = new Babu("fekete");


    public Palya() {

        for (int SOR = 1; SOR <= 7; SOR += 2) {
            for (int OSZLOP = 1; OSZLOP <= 7; OSZLOP += 2) {
                Mezo uj_mezo = new Mezo(SOR, OSZLOP);
                if(SOR <=3) {
                    feherBabu = new Babu("feher");
                    uj_mezo.setBabu(feherBabu);
                    uj_mezo.getMezoPanel().add(feherBabu.getBabuLabel());
                }

                if(SOR >= 6) {
                    feketeBabu = new Babu("fekete");
                    uj_mezo.setBabu(feketeBabu);
                    uj_mezo.getMezoPanel().add(feketeBabu.getBabuLabel());
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
                }

                if(SOR >= 6) {
                    feketeBabu = new Babu("fekete");
                    uj_mezo.setBabu(feketeBabu);
                    uj_mezo.getMezoPanel().add(feketeBabu.getBabuLabel());
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
                        m.setSzomszedok(temp_szomszedok);
                }
            }
        }
    }

    public List<Mezo> getMezok() {
        return mezok;
    }


}
