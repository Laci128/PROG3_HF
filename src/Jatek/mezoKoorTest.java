package Jatek;

import Mukodes.*;


public class mezoKoorTest {
    public static void main(String[] args) {
        Palya palya = new Palya();

        int szam = 0;
        for(int sor = 1; sor <= 8; sor++){
            for(int oszlop = 1; oszlop <= 8; oszlop++){
                szam = (sor-1)*8+oszlop;
                System.out.print("|"+szam+"|");
                if(oszlop%8 == 0)
                    System.out.print("\n");
            }
        }


        //int i = 1;
        for (Mezo m : palya.getMezok()) {
            if (m.getBabu() != null && m.getBabu().getSzin().equals("feher"))
                System.out.println("feher: (" + m.getSor() + "," + m.getOszlop() + ")");
            if (m.getBabu() != null && m.getBabu().getSzin().equals("fekete"))
                System.out.println("fekete: (" + m.getSor() + "," + m.getOszlop() + ")");
            //System.out.println("Mező száma: " + i);
            //i++;
        }
    }
}
