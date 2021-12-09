package Felulet;

import Mukodes.Babu;
import Mukodes.Palya;
import Mukodes.Mezo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Magának a játéktáblának a panele
 */
public class TablaPanel extends JPanel {

    private Palya palya = new Palya();

    private Babu kivalasztottBabu;

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
                    if ((i + 1) == ((m.getSor() - 1) * 8 + m.getOszlop())) {
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

    private class egerLabelListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {                //Martin Label-höz adja hozzá
            //Component component = e.getComponent();
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

            for (Mezo m : palya.getMezok()) {
                if(sorszam != -1 && (sorszam +1) == ((m.getSor() - 1) * 8 + m.getOszlop())) {
                    m.getMezoPanel().setBackground(Color.GREEN);   //teszthez
                    kivalasztottBabu = m.getBabu();
                }
            }


            //JLabel babuLabel = (JLabel) e.getComponent();
            //babuLabel.setBackground(Color.CYAN);

            //ez működik:
            //JPanel babuLabel = (JPanel) e.getComponent();
            //babuLabel.setBackground(Color.CYAN);

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


            for (Mezo m : palya.getMezok()) {
                if(sorszam != -1 && (sorszam +1) == ((m.getSor() - 1) * 8 + m.getOszlop())) {
                    m.getMezoPanel().setBackground(Color.RED);   //teszthez
                    kivalasztottBabu.Lep(m);

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

    public Palya getPalya() {
        return palya;
    }

    public void setPalya(Palya palya) {
        this.palya = palya;
    }
}
