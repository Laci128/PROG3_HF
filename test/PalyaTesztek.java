import Mukodes.Mezo;
import Mukodes.Palya;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PalyaTesztek {
    private Palya palya;
    private int mezoszam;
    private Mezo Mezo1;
    private Mezo Mezo2;


    @Before
    public void palyaInit(){
        palya = new Palya();
    }

    @Test
    public void babuErtekek(){
        int osszertek = 24;
        int eredmeny = palya.getPalyaErteke();

        Assert.assertEquals("Nem jól számolja ki a Palyan lévő Babu értékének összegét!",osszertek,eredmeny);
    }


    @Test
    public void nyertEValakiTeszt(){
        for (Mezo m: palya.getMezok())
            if(m.getBabu() != null && m.getBabu().getSzin().equals("feher"))
                m.setBabu(null);
        String feketeNyertes = palya.nyertEValaki();

        palya = new Palya();
        for (Mezo m: palya.getMezok())
            if(m.getBabu() != null && m.getBabu().getSzin().equals("fekete"))
                m.setBabu(null);
        String feherNyertes = palya.nyertEValaki();

        palya = new Palya();
        String senkiSeNyert = palya.nyertEValaki();

        Assert.assertEquals("Nem a fekete nyert a függvény szerint, pedig csak fekete Babuk vannak fent, feher egy sincs!","fekete",feketeNyertes);
        Assert.assertEquals("Nem a feher nyert a függvény szerint, pedig csak feher Babuk vannak fent, fekete egy sincs!","feher",feherNyertes);
        Assert.assertNull("Nem null-t ad vissza a függvény pedig a Palya a kezdoallapotban van, nem nyerhetett egyik szín sem.",senkiSeNyert);
    }


    @Test
    public void szabadEAzUtTeszt(){
        for (Mezo m: palya.getMezok())
                m.setBabu(null);

        mezoszam = 28;
        for (Mezo m: palya.getMezok()) {
            if (m.Mezoszam() == mezoszam) {
                Mezo1 = m;  //(4,4)
                break;
            }
        }

        mezoszam = 55;
        for (Mezo m: palya.getMezok()) {
            if (m.Mezoszam() == mezoszam) {
                Mezo2 = m;  //(7,7)
                break;
            }
        }
        boolean jobbraLe = palya.szabadEAzUt(Mezo1, Mezo2); //+ +
        boolean balraFel = palya.szabadEAzUt(Mezo2, Mezo1); //- -

        mezoszam = 49;
        for (Mezo m: palya.getMezok()) {
            if (m.Mezoszam() == mezoszam) {
                Mezo2 = m;  //(7,1)
                break;
            }
        }
        boolean balraLe = palya.szabadEAzUt(Mezo1, Mezo2); //+ -
        boolean jobbraFel = palya.szabadEAzUt(Mezo2, Mezo1); //- +

        Assert.assertTrue("Nem jó a fuggyveny, ha a célmező jobbra-le irányban van!",jobbraLe);
        Assert.assertTrue("Nem jó a fuggyveny, ha a célmező balra-fel irányban van!",balraFel);
        Assert.assertTrue("Nem jó a fuggyveny, ha a célmező balra-le irányban van!",balraLe);
        Assert.assertTrue("Nem jó a fuggyveny, ha a célmező jobbra-fel irányban van!",jobbraFel);
    }


}
