package Felulet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {
    public MenuFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Dáma");
        setSize(260, 200);
        setResizable(false);

        JButton ujJatek = new JButton("Új Játék");
        JButton betoltes = new JButton("Játekállás betöltése");

        JLayeredPane pane = new JLayeredPane();
        getContentPane().add(pane);
        pane.add(ujJatek,JLayeredPane.DEFAULT_LAYER);
        pane.add(betoltes,JLayeredPane.DEFAULT_LAYER);

        ujJatek.setBounds(20,20,200,50);
        betoltes.setBounds(20,90,200,50);

        ujJatek.addActionListener(new UjJatekActionListener());

    }

    private class UjJatekActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            DamaFrame ujDamaFrame = new DamaFrame();
            ujDamaFrame.setVisible(true);
            //ujDamaFrame.setJelenlegiJatekosLabel(jatek.getKezdetiJatekos());
        }
    }

}
