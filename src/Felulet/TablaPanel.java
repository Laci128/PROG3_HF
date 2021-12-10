package Felulet;

import Mukodes.Babu;
import Mukodes.Palya;
import Mukodes.Mezo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Magának a játéktáblának a panele
 */
public class TablaPanel extends JPanel {

    private Palya palya = new Palya();

    private Babu kivalasztottBabu;
    private JLabel jelenlegiJatekos = new JLabel("feher");

    private Color Feher = new Color(245, 245, 245);

    TablaPanel() {
        setLayout(new GridLayout(8, 8));
        setBounds(300,20,800,800);

        /*
            Kezdő pozíció
         */
        JPanel ujpanel;
        int sor = 1;

        for(int i= 0; i <=63; i++) {
            if (i % 8 == 0)
                sor++;
            if ((i + sor) % 2 == 1){
                ujpanel = new JPanel();
                add(ujpanel);
            }
            else{
                for (Mezo m : palya.getMezok()) {
                    if ((i + 1) == m.Mezoszam()) {
                        m.getMezoPanel().addMouseListener(new egerPanelListener());
                        if(m.getBabu() != null)
                            m.getBabu().getBabuLabel().addMouseListener(new egerLabelListener());
                        add(m.getMezoPanel());
                    }
                }
            }
        }

        /*
          Mezők színének beállítása
         */
        sor = 1;
        for(int i= 0; i <=63; i++) {

            if (i % 8 == 0)
                sor++;
            if ((i + sor) % 2 == 1)
                getComponent(i).setBackground(Color.BLACK);
        }


        /*
         ******************RANDOM KIIRATASOK**************************
        */
        /*
        for (Mezo m : palya.getMezok()) {
            int szam = (m.getSor() - 1) * 8 + m.getOszlop();
            System.out.print(szam + " ");

        }
        System.out.print("\n");
        System.out.print("\n");

        for (Mezo m : palya.getMezok()) {
            if (m.getBabu() != null && m.getBabu().getSzin().equals("feher"))
                System.out.println("feher: (" + m.getSor() + "," + m.getOszlop() + ")");
            if (m.getBabu() != null && m.getBabu().getSzin().equals("fekete"))
                System.out.println("fekete: (" + m.getSor() + "," + m.getOszlop() + ")");
        }

         */

    }

    private TablaPanel magatVisszaado(){
        return this;
    }

    public void passz(){
        if(jelenlegiJatekos.getText().equals("feher")){
            jelenlegiJatekos.setText("fekete");
        }
        else
            jelenlegiJatekos.setText("feher");
        if(kivalasztottBabu != null) {
            kivalasztottBabu.getJelenlegiMezo().getMezoPanel().setBackground(Feher);
            kivalasztottBabu = null;
        }
    }

    public passzGombListener passzGombListenerAdo() {
        return new passzGombListener();
    }

    private boolean KiralyLeszEFelulet(){
        Babu temp = kivalasztottBabu.getJelenlegiMezo().KiralyLeszE();
        if(temp != null){
            kivalasztottBabu = temp;
            kivalasztottBabu.getJelenlegiMezo().getMezoPanel().revalidate();
            kivalasztottBabu.getJelenlegiMezo().getMezoPanel().repaint();
            kivalasztottBabu.getBabuLabel().addMouseListener(new egerLabelListener());

            kivalasztottBabu.setPalya(palya);
            return true;
        }
        return false;
    }

    /*private void TablaUpdate(){    //Nem hiszem hogy kelleni fog
        int sor = 1;
        for(int i= 0; i <=63; i++) {
            if (i % 8 == 0)
                sor++;
            if((i + sor) % 2 == 0){
                for (Mezo m : palya.getMezok()) {
                    if ((i + 1) == m.Mezoszam()) {
                        add(m.getMezoPanel());
                    }
                }
            }
        }
    }*/

    private void babutAthelyez(Mezo jelenlegiM, Mezo celM){
        jelenlegiM.getMezoPanel().setBackground(Feher);
        jelenlegiM.getMezoPanel().remove(kivalasztottBabu.getBabuLabel());
        jelenlegiM.getMezoPanel().revalidate();
        jelenlegiM.getMezoPanel().repaint();
        celM.getMezoPanel().setBackground(Color.CYAN);
        celM.getMezoPanel().add(kivalasztottBabu.getBabuLabel());
    }


    private class egerLabelListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel klikkeltLabel = (JLabel) e.getComponent();

            JPanel labelPanele = new JPanel();
            int sorszam = -1;
            for(int i= 0; i <=63; i++) {
                labelPanele = (JPanel) magatVisszaado().getComponent(i);

                for (Component comp : labelPanele.getComponents()) {
                    JLabel temp = (JLabel) comp;
                    if ((klikkeltLabel.equals(temp))) {
                        sorszam = i;
                        break;
                    }
                }
            }

            for (Mezo kivalasztottMezo : palya.getMezok()) {
                if(sorszam != -1 && (sorszam +1) == kivalasztottMezo.Mezoszam()) {
                    if(kivalasztottBabu == null || kivalasztottBabu.getSzin().equals(jelenlegiJatekos.getText())) {
                        if(kivalasztottBabu != null && kivalasztottBabu.getUgrott() && kivalasztottBabu.getSzin().equals(jelenlegiJatekos.getText()))
                            return;
                        if(kivalasztottBabu != null)
                            kivalasztottBabu.getJelenlegiMezo().getMezoPanel().setBackground(Feher);
                        if (kivalasztottMezo.getBabu().getSzin().equals(jelenlegiJatekos.getText())) {
                            kivalasztottBabu = kivalasztottMezo.getBabu();
                            kivalasztottMezo.getMezoPanel().setBackground(Feher);
                            kivalasztottMezo.getMezoPanel().setBackground(Color.CYAN);
                            kivalasztottBabu = kivalasztottMezo.getBabu();
                        }
                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }

    private class egerPanelListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            JPanel klikkeltPanel = (JPanel) e.getComponent();

            int sorszam = -1;
            for(int i= 0; i <=63; i++) {
                JPanel temp = (JPanel) magatVisszaado().getComponent(i);

                    if ((klikkeltPanel.equals(temp))) {
                        sorszam = i;
                        break;
                    }
            }

            for (Mezo celMezo : palya.getMezok()) {
                if(sorszam != -1 && (sorszam +1) == celMezo.Mezoszam()) {
                    //celMezo.getMezoPanel().setBackground(Color.RED);   //teszthez
                    Mezo jelenlegiMezo;
                    Mezo atugrottMezo;
                    if (kivalasztottBabu == null){
                        return;
                    }
                    if(kivalasztottBabu.getSzin().equals(jelenlegiJatekos.getText())) {
                        jelenlegiMezo = kivalasztottBabu.getJelenlegiMezo();
                        //LÉP
                        if(kivalasztottBabu.getErtek() == 5 || jelenlegiMezo.getSzomszedok().contains(celMezo)) {
                            if(kivalasztottBabu.getUgrott()) {
                                passz();
                                return;
                            }
                            if (kivalasztottBabu.Lep(celMezo)) {
                                babutAthelyez(jelenlegiMezo,celMezo);
                                KiralyLeszEFelulet();
                                passz();
                                return;
                            }
                        }
                        //UGRIK
                        if(kivalasztottBabu.getErtek() == 5 || !jelenlegiMezo.getSzomszedok().contains(celMezo)){
                            atugrottMezo = kivalasztottBabu.Ugrik(celMezo);
                            if (atugrottMezo != null){
                                babutAthelyez(jelenlegiMezo,celMezo);
                                if(KiralyLeszEFelulet())
                                    passz();

                                atugrottMezo.getMezoPanel().setBackground(Feher);
                                atugrottMezo.getMezoPanel().remove(atugrottMezo.getBabu().getBabuLabel());
                                atugrottMezo.getMezoPanel().revalidate();
                                atugrottMezo.getMezoPanel().repaint();
                                atugrottMezo.setBabu(null);

                            }
                            else{
                                if(kivalasztottBabu.getUgrott()){
                                    kivalasztottBabu.setUgrott(false);
                                    passz();
                                }
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }


    private class passzGombListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            passz();
        }
    }

    public Palya getPalya() {
        return palya;
    }

    public void setPalya(Palya palya) {
        this.palya = palya;
    }

    public JLabel getJelenlegiJatekos() {
        return jelenlegiJatekos;
    }

    public void setJelenlegiJatekos(JLabel jelenlegiJatekos) {
        this.jelenlegiJatekos = jelenlegiJatekos;
    }
}
