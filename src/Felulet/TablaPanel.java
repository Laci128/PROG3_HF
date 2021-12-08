package Felulet;

import Mukodes.Palya;
import Mukodes.Mezo;
import javax.swing.*;
import java.awt.*;

/**
 * Magának a játéktáblának a panele
 */
public class TablaPanel extends JPanel {
    private JPanel ujpanel;

    private Palya palya = new Palya();


    TablaPanel() {
        setLayout(new GridLayout(8, 8));
        setBounds(300,20,800,800);

        for (int i = 0; i <= 63; i++) {
            ujpanel = new JPanel();
            add(ujpanel);
        }


        /*
          Mezők színének beállítása
         */
        int sor = 1;
        for(int i= 0; i <=63; i++) {

            if (i % 8 == 0)
                sor++;
            if ((i + sor) % 2 == 1)
                getComponent(i).setBackground(Color.BLACK);
            else
                getComponent(i).setBackground(new Color(245,245,245));
        }


        JPanel temp = new JPanel();
        for(int i= 0; i <=63; i++) {
            for (Mezo m : palya.getMezok()) {
                if ((i+1) == ((m.getSor() - 1) * 8 + m.getOszlop())){
                    temp = (JPanel) getComponent(i);
                    temp.add(m.getMezoPanel());
                }
            }
        }


        for (Mezo m : palya.getMezok()) {
            if (m.getBabu() != null && m.getBabu().getSzin().equals("feher"))
                System.out.println("feher: (" + m.getSor() + "," + m.getOszlop() + ")");
            if (m.getBabu() != null && m.getBabu().getSzin().equals("fekete"))
                System.out.println("fekete: (" + m.getSor() + "," + m.getOszlop() + ")");
        }


        /*
         ******************RANDOM KIIRATASOK**************************
         */
        for (Mezo m : palya.getMezok()) {
            int szam = (m.getSor() - 1) * 8 + m.getOszlop();
            System.out.print(szam + " ");

        }
        System.out.print("\n");
        System.out.print("\n");
    }




}
