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

    private Palya palya;

    private Babu kivalasztottBabu;
    private JLabel jelenlegiJatekosLabel = new JLabel("feher");
    private Color Feher = new Color(245, 245, 245);

    TablaPanel(Palya betoltottPalya) {
        setLayout(new GridLayout(8, 8));
        setBounds(300,20,800,800);

        /*
            Kezdő pozíció
         */
        if(betoltottPalya != null) {
            palya = betoltottPalya;
            jelenlegiJatekosLabel.setText(palya.getJelenlegiJatekos());
            kivalasztottBabu = palya.getKivalasztottBabu();
        }
        else {
            palya = new Palya();
        }

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
    }

    public void passz(){
        if(jelenlegiJatekosLabel.getText().equals("feher")){
            jelenlegiJatekosLabel.setText("fekete");
        }
        else
            jelenlegiJatekosLabel.setText("feher");
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


    private void babutAthelyez(Mezo jelenlegiM, Mezo celM){
        jelenlegiM.getMezoPanel().setBackground(Feher);
        jelenlegiM.getMezoPanel().remove(kivalasztottBabu.getBabuLabel());
        jelenlegiM.getMezoPanel().revalidate();
        jelenlegiM.getMezoPanel().repaint();
        celM.getMezoPanel().setBackground(Color.CYAN);
        celM.getMezoPanel().add(kivalasztottBabu.getBabuLabel());
    }


    public void jatekVege(){
        //Valamelyik szín nyert
        if(palya.nyertEValaki() != null)
            JOptionPane.showMessageDialog(null,
                    "A játéknak vége. A "+palya.nyertEValaki()+ " játékos nyert",
                    "A "+palya.nyertEValaki()+ " játékos nyert",
                    JOptionPane.PLAIN_MESSAGE);

        //Döntetlen
        int ujErtek = palya.palyanLevoBabukErteke();
        if(ujErtek == palya.getPalyaErteke())
            palya.setKorDontetlenig(palya.getKorDontetlenig()-1);
        else
            palya.setKorDontetlenig(25);

        if(palya.getKorDontetlenig() == 0)
            JOptionPane.showMessageDialog(null,
                    "A játéknak vége. Döntetlen. \n25 körön keresztül nem változott a bábuk összértéke. \n(Nem lett király sima bábuból, nem volt leütés)",
                    "Döntetlen",
                    JOptionPane.PLAIN_MESSAGE);
    }


    private class passzGombListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            passz();
            jatekVege();
        }
    }


    private class egerLabelListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel klikkeltLabel = (JLabel) e.getComponent();

            JPanel labelPanele;
            int sorszam = -1;
            for(int i= 0; i <=63; i++) {
                labelPanele = (JPanel) getComponent(i);

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
                    if(kivalasztottBabu == null || kivalasztottBabu.getSzin().equals(jelenlegiJatekosLabel.getText())) {
                        if(kivalasztottBabu != null && kivalasztottBabu.getUgrott() && kivalasztottBabu.getSzin().equals(jelenlegiJatekosLabel.getText()))
                            return;
                        if(kivalasztottBabu != null)
                            kivalasztottBabu.getJelenlegiMezo().getMezoPanel().setBackground(Feher);
                        if (kivalasztottMezo.getBabu().getSzin().equals(jelenlegiJatekosLabel.getText())) {
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
                JPanel temp = (JPanel) getComponent(i);

                    if ((klikkeltPanel.equals(temp))) {
                        sorszam = i;
                        break;
                    }
            }

            for (Mezo celMezo : palya.getMezok()) {
                if(sorszam != -1 && (sorszam +1) == celMezo.Mezoszam()) {
                    Mezo jelenlegiMezo;
                    Mezo atugrottMezo;
                    if (kivalasztottBabu == null){
                        return;
                    }
                    if(kivalasztottBabu.getSzin().equals(jelenlegiJatekosLabel.getText())) {
                        jelenlegiMezo = kivalasztottBabu.getJelenlegiMezo();


                        //LÉP
                        if( palya.szabadEAzUt(jelenlegiMezo, celMezo) && !kivalasztottBabu.UgrottE() ) {
                            if(kivalasztottBabu.getUgrott()) {
                                kivalasztottBabu.setUgrott(false);
                                passz();
                                return;
                            }
                            if (kivalasztottBabu.Lep(celMezo)) {
                                if (kivalasztottBabu.getUgrott())
                                    kivalasztottBabu.setUgrott(false);
                                babutAthelyez(jelenlegiMezo, celMezo);
                                KiralyLeszEFelulet();
                                passz();

                                //Lépés után ellenőriz
                                jatekVege();
                            }
                        }
                        //UGRIK
                        else {
                            atugrottMezo = kivalasztottBabu.Ugrik(celMezo);

                            if (atugrottMezo != null){
                                babutAthelyez(jelenlegiMezo,celMezo);

                                if(atugrottMezo.getBabu()!= null) {
                                    atugrottMezo.getMezoPanel().setBackground(Feher);
                                    atugrottMezo.getMezoPanel().remove(atugrottMezo.getBabu().getBabuLabel());
                                    atugrottMezo.getMezoPanel().revalidate();
                                    atugrottMezo.getMezoPanel().repaint();
                                    atugrottMezo.setBabu(null);
                                }

                                if(kivalasztottBabu.TeljesenUgrott()){
                                    kivalasztottBabu.setUgrott(false);
                                    kivalasztottBabu.setUresbenUgrott(false);
                                    passz();

                                    //Királynál ha üresen is ugrott a végén ellenőriz
                                    jatekVege();
                                    return;
                                }

                                if(KiralyLeszEFelulet())
                                    passz();

                            }
                            else{
                                if(kivalasztottBabu.getUgrott()){
                                    kivalasztottBabu.setUgrott(false);
                                    passz();

                                    //Sima bábu ugrása végén és Királynál ha ugrások után nem ugrik üreset
                                    jatekVege();
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


    public void setPalya(Palya palya) {
        this.palya = palya;
    }

    public Palya getPalya() {
        return palya;
    }

    public void setJelenlegiJatekosLabel(JLabel jelenlegiJatekos) {
        this.jelenlegiJatekosLabel = jelenlegiJatekos;
    }

    public JLabel getJelenlegiJatekosLabel() {
        return jelenlegiJatekosLabel;
    }

    public void setKivalasztottBabuTabla(Babu kivalasztottBabu) {
        this.kivalasztottBabu = kivalasztottBabu;
    }

    public Babu getKivalasztottBabuTabla() {
        return kivalasztottBabu;
    }
}
