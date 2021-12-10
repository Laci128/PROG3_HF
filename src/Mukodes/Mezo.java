package Mukodes;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

/**
 * A játéktér alapvető mezői, amin a bábuk helyezkednek el.
 * Ősosztálya a Kiraly bábunak.
 */
public class Mezo {

    /**
     * A pozíciójának egyik koordinátája
     */
    Integer Sor;

    /**
     * a pozíciójának másik koordinátája
     */
    Integer Oszlop;

    /**
     * A Mezo szomszédai.
     */
    private List<Mezo> szomszedok = new ArrayList<Mezo>();

    /**
     * Ezen a mezon jelenleg rajta levo Babu.
     */
    private Babu babu;

    private JPanel mezoPanel = new JPanel();


    /**
     * A Mezo konstruktora
     * @param a a pozíciójának egyik koordinátája
     * @param b a pozíciójának másik koordinátája
     */
    public Mezo(int a, int b){
        Sor = a;
        Oszlop = b;
        mezoPanel.setBackground(new Color(245,245,245));
    }

    public int Mezoszam(){
        return (Sor - 1) * 8 + Oszlop;
    }

    public Babu KiralyLeszE(){
        Kiraly kiraly;
        if(babu.getSzin().equals("feher")){
            if(Sor == 8){
                mezoPanel.remove(babu.getBabuLabel());
                kiraly = new Kiraly("feher");
                babu = kiraly;
                mezoPanel.add(kiraly.getBabuLabel());
                kiraly.setJelenlegiMezo(this);
                return babu;
            }
            else
                return null;
        }
        if(babu.getSzin().equals("fekete")) {
            if(Sor == 1){
                mezoPanel.remove(babu.getBabuLabel());
                kiraly = new Kiraly("fekete");
                babu = kiraly;
                mezoPanel.add(kiraly.getBabuLabel());
                kiraly.setJelenlegiMezo(this);
                return babu;
            }
            else
                return null;
        }
        return null;
    }



    /**
     * A babu setter függvénye
     * @param b Babu amit a Mezore akarunk tenni
     */
    public void setBabu(Babu b) {
        babu = b;
    }

    /**
     * A babu getter függvenye
     * @return A Mezon lévő bábu
     */
    public Babu getBabu() {
        return babu;
    }


    /**
     *
     * @param sz
     */
    public void setSzomszedok(List<Mezo> sz) {
        szomszedok = sz;
    }

    /**
     *
     * @return
     */
    public List<Mezo> getSzomszedok() {
        return szomszedok;
    }

    public int getSor() {
        return Sor;
    }

    public void setSor(int sor) {
        this.Sor = sor;
    }

    public int getOszlop() {
        return Oszlop;
    }

    public void setOszlop(int oszlop) {
        this.Oszlop = oszlop;
    }

    public JPanel getMezoPanel() {
        return mezoPanel;
    }

    public void setMezoPanel(JPanel mezoPanel) {
        this.mezoPanel = mezoPanel;
    }


}
