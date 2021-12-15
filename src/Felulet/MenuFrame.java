package Felulet;

import Mukodes.Palya;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * A felület "piramisának csúcsa", maga a menü kerete, amiből minden más meghívódik.
 */
public class MenuFrame extends JFrame {
    /**
     * Ezt kapja meg a DamaFrame, hogy melyik pályán folyik a játék.
     * Ha nincs betöltes (aza null marad) akkor új Palya generálódik a DamaFrame-ben
     */
    private Palya betoltottPalya = null;

    /**
     * MenuFrame konstruktora
     */
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

        ujJatek.addActionListener(new JatekInditasaActionListener());
        betoltes.addActionListener(new betoltesGombListener());


    }

    /**
     * ActionListenner a játék indításához, a "Játék" gombhoz van hozzáadva.
     * Létrehoz egy DamaFrame-t betoltottPalya paraméterrel és bezárja a menüt
     */
    private class JatekInditasaActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            dispose();
            DamaFrame ujDamaFrame = new DamaFrame(betoltottPalya);
            ujDamaFrame.setVisible(true);
        }
    }

    /**
     * A pálya amin játszunk szerialzálható, ha van már korábban elmentett pályánk akkor azt visszaadja
     * @return Elmentett pálya, ha nincs mentve, akkor null-t ad vissza.
     */
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

    /**
     * ActionListener, "Legutóbbi játekállás betöltése" gombhoz van hozzáadva.
     * a betoltottPalya tagváltozót beállítja a palyaBetoltese visszatérési értékére
     */
    private class betoltesGombListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            betoltottPalya = palyaBetoltese();
        }
    }

}
