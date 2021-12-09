package Felulet;

import Mukodes.Mezo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A teljes Dáma játék kerete
 */
public class DamaFrame extends JFrame {
    private TablaPanel tabla;

    public DamaFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Dáma");
        setSize(1200,900);
        setResizable(false);

        JLayeredPane pane = new JLayeredPane();
        getContentPane().add(pane);

        tabla = new TablaPanel();

        JButton mentes = new JButton("Játékállás mentése");
        JButton dontetlen = new JButton("Kiegyezés döntetlenben");
        JButton passz = new JButton("Passz");

        JPanel labelPanel = new JPanel();
        labelPanel.add(new JLabel("Jelenlegi Jatekos: "), BorderLayout.NORTH);
        labelPanel.add(jelenlegiJatekosLabel, BorderLayout.SOUTH);

        mentes.setBounds(20,10,200,50);
        dontetlen.setBounds(20,120,200,50);
        passz.setBounds(20,230,200,50);
        labelPanel.setBounds(20,340,200,50);


        /*
            Pálya széli számok ás betűk
         */

        JPanel szam;
        int x = 280;
        int y = 50;
        for (int i = 8; i >= 1; i--){
            szam = new JPanel();
            szam.add(new JLabel(String.valueOf(i)));
            szam.setBounds(x,y,20,20);
            y+=100;
            pane.add(szam,JLayeredPane.DEFAULT_LAYER);
        }

        JPanel betu;
        x = 340;
        y = 820;
        for (char i = 'a'; i <= 'h'; i++){
            betu = new JPanel();
            betu.add(new JLabel(String.valueOf(i)));
            betu.setBounds(x,y,20,20);
            x+=100;
            pane.add(betu,JLayeredPane.DEFAULT_LAYER);
        }



        pane.add(tabla,JLayeredPane.DEFAULT_LAYER);
        pane.add(mentes,JLayeredPane.DEFAULT_LAYER);
        pane.add(dontetlen,JLayeredPane.DEFAULT_LAYER);
        pane.add(passz,JLayeredPane.DEFAULT_LAYER);
        pane.add(labelPanel,JLayeredPane.DEFAULT_LAYER);

    }

    public void setJelenlegiJatekosLabel(String jelenlegi_j) {
        jelenlegiJatekosLabel.setText(jelenlegi_j);
    }

    private JLabel jelenlegiJatekosLabel = new JLabel();


}
