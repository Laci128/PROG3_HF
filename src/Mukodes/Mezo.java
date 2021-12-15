package Mukodes;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * A játéktér egyes mezői, amin a bábuk helyezkednek el.
 */
public class Mezo implements Serializable {

    /**
     * A pozíciójának egyik koordinátája, fentről lefelé hanyadik sor
     */
    Integer Sor;
    /**
     * A pozíciójának másik koordinátája, balról jobbra hanyadik oszlop
     */
    Integer Oszlop;
    /**
     * A Mezo szomszédai.
     */
    private ArrayList<Mezo> szomszedok = new ArrayList<Mezo>();
    /**
     * Ezen a Mezon jelenleg rajta lévő Babu.
     */
    private Babu babu;
    /**
     * JPanel ami a mezőhöz tartozik.
     */
    private JPanel mezoPanel = new JPanel();


    /**
     * A Mezo konstruktora
     * @param sor ezt kapja értekül kap a Sor tagváltozó
     * @param oszlop ezt kapja értekül kap az Oszlop tagváltozó
     */
    public Mezo(int sor, int oszlop){
        Sor = sor;
        Oszlop = oszlop;
        mezoPanel.setBackground(new Color(245,245,245));
    }

    /**
     * A Mező Sor és Oszlop számából egy mezőszámot ad meg.
     * @return hanyadik (component-je a 8x8-as TablPanelnek) - 1
     */
    public int Mezoszam(){
        return (Sor - 1) * 8 + Oszlop;
    }

    /**
     * Megadja, hogy a Mezo-n lévő Babu Kiraly lesz-e vagy sem
     * Fekete Babu-k a fehér elős sorában, míg a fehér bábuk a fekete első sorában lesz az.
     * @return visszaadja a Kiraly-t ha igen, null-t ha nem
     */
    public Babu KiralyLeszE(){
        if(babu.getErtek() == 5)
            return null;

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
     * szomszedok Setter-e
     * @param sz ezt kapja értekül kap a szomszedok tagváltozó
     */
    public void setSzomszedok(ArrayList<Mezo> sz) {
        szomszedok = sz;
    }
    /**
     * szomszedok Getter-e
     * @return szomszedok tagváltozó
     */
    public ArrayList<Mezo> getSzomszedok() {
        return szomszedok;
    }

    /**
     * Sor Setter-e
     * @param sor ezt kapja értekül kap a Sor tagváltozó
     */
    public void setSor(int sor) {
        Sor = sor;
    }
    /**
     * Sor Getter-e
     * @return Sor tagváltozó
     */
    public int getSor() {
        return Sor;
    }

    /**
     * Oszlop Setter-e
     * @param oszlop ezt kapja értekül kap az Oszlop tagváltozó
     */
    public void setOszlop(int oszlop) {
        Oszlop = oszlop;
    }
    /**
     * Oszlop Getter-e
     * @return Oszlop tagváltozó
     */
    public int getOszlop() {
        return Oszlop;
    }


    /**
     * mezoPanel Getter-e
     * @return mezoPanel tagváltozó
     */
    public JPanel getMezoPanel() {
        return mezoPanel;
    }



}
