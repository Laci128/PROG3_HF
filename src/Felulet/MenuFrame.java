package Felulet;

import Mukodes.Palya;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MenuFrame extends JFrame {
    private Palya betoltottPalya = null;
    public MenuFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Dáma");
        setSize(300, 200);
        setResizable(false);

        JButton ujJatek = new JButton("Játék");
        JButton betoltes = new JButton("Legutóbbi játekállás betöltése");

        JLayeredPane pane = new JLayeredPane();
        getContentPane().add(pane);
        pane.add(ujJatek,JLayeredPane.DEFAULT_LAYER);
        pane.add(betoltes,JLayeredPane.DEFAULT_LAYER);

        ujJatek.setBounds(20,20,240,50);
        betoltes.setBounds(20,90,240,50);

        ujJatek.addActionListener(new UjJatekActionListener());
        betoltes.addActionListener(new betoltesGombListener());


    }

    private class UjJatekActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            DamaFrame ujDamaFrame = new DamaFrame(betoltottPalya);
            ujDamaFrame.setVisible(true);
            //ujDamaFrame.setJelenlegiJatekosLabel(jatek.getKezdetiJatekos());
        }
    }


    public Palya palyaBetoltese(){
        try {
            Palya betoltottPalya;
            FileInputStream f = new FileInputStream("palya.txt");
            ObjectInputStream in = new ObjectInputStream(f);
            betoltottPalya = (Palya) in.readObject();
            in.close();
            return betoltottPalya;
        }
        catch(IOException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return null;
    }

    private class betoltesGombListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            betoltottPalya = palyaBetoltese();
        }
    }

}
