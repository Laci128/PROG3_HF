package Felulet;

import Mukodes.Palya;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

/**
 * A teljes dámajáték kerete, maga a dáma megjelenítése.
 */
public class DamaFrame extends JFrame {
    /**
     * A 8x8-as pálya közvetlen grafikus megjelenítése, a DamaFrame-en belül
     */
    private TablaPanel tabla;

    /**
     * JLabel, ami mindig a jelenleg soron lévő játékos színét írja ki.
     * A TablaPanel-től kapja meg konstruktorban.
     */
    private JLabel jelenlegiJatekosLabel;

    /**
     * ArrayList amiben, benne van a jelenleg döntetlenben kiegyezni akaró játékosok színe.
     */
    private ArrayList<String> dontetlentSzeretne = new ArrayList<String>();

    /**
     * A DamaFrame konstruktora
     * @param betoltottPalya Palya, ami továbbadódik paraméterként a TablaPanelnek
     */
    public DamaFrame(Palya betoltottPalya){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Dáma");
        setSize(1200,900);
        setResizable(false);


        JLayeredPane pane = new JLayeredPane();
        getContentPane().add(pane);

        tabla = new TablaPanel(betoltottPalya);
        jelenlegiJatekosLabel = tabla.getJelenlegiJatekosLabel();

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


        mentes.addActionListener(new mentesGombListener());
        passz.addActionListener(tabla.passzGombListenerAdo());
        dontetlen.addActionListener(new dontetlenGombListener());

    }


    /**
     * A jelenlegi pályát menti el, szerializálja.
     */
    public void palyaMentese() {
        try{
            FileOutputStream f = new FileOutputStream("palya.txt");
            ObjectOutputStream out = new ObjectOutputStream(f);

            //jelenlegiJatekos kimentese
            tabla.getPalya().setJelenlegiJatekos(tabla.getJelenlegiJatekosLabel().getText());
            //kivalaszottBabu kimentese
            tabla.getPalya().setKivalasztottBabu(tabla.getKivalasztottBabuTabla());

            out.writeObject(tabla.getPalya());
            out.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }

    }

    /**
     * Hozzáadja a jelenlegi játékos színét a dontetlentSzeretne listához
     * @return Igazat ad vissza, ha mindkét játékos színe benne van már.
     */
    public Boolean dontetlenHozzaad(){
        if(!dontetlentSzeretne.contains(jelenlegiJatekosLabel.getText()))
            dontetlentSzeretne.add(jelenlegiJatekosLabel.getText());
        else
            dontetlentSzeretne.remove(jelenlegiJatekosLabel.getText());

        return dontetlentSzeretne.contains("feher") && dontetlentSzeretne.contains("fekete");
    }


    /**
     * ActionListener mentéshez. Lementi a jelentlegi pályát.
     * "Játékállás mentése" gombhoz van hozzáadva.
     * Bezárja a dámát és létrehoz egy új menüt.
     */
    private class mentesGombListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            palyaMentese();
            dispose();
            MenuFrame menuFrame = new MenuFrame();
            menuFrame.setVisible(true);
        }
    }


    /**
     * ActionListener döntetlenben való kiegyezéshez. Meghívja a dontetlenHozzaad függvényt.
     * "Kiegyezés döntetlenben" gombhoz van hozzáadva.
     * Ha az igazat ad vissza akkor egy Message ablakban kiírja, hogy a játéknak vége és az okát.
     */
    private class dontetlenGombListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(dontetlenHozzaad()){
                JOptionPane.showMessageDialog(null,
                        "A játéknak vége. A játékosok kiegyeztek döntetlenben.","Döntetlen",
                        JOptionPane.PLAIN_MESSAGE);
            }

        }
    }
}
