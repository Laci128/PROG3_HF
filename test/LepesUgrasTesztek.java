import Mukodes.Babu;
import Mukodes.Kiraly;
import Mukodes.Mezo;
import Mukodes.Palya;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LepesUgrasTesztek {

    private Palya palya;
    private int mezoszam;
    private Mezo jelenlegiMezo;
    private Mezo ugrottMezo;
    private Mezo celMezo;
    private Babu mozgoBabu;
    private Kiraly kiraly;
    private Babu ugrottBabu;

    @Before
    public void LepesUgrasInit(){
        palya = new Palya();
        for (Mezo m: palya.getMezok())
            m.setBabu(null);

        mezoszam = 1;
        for (Mezo m: palya.getMezok()) {
            if (m.Mezoszam() == mezoszam) {
                jelenlegiMezo = m;  //(1,1)
                break;
            }
        }

        mozgoBabu = new Babu("feher");
        ugrottBabu = new Babu("fekete");
        kiraly = new Kiraly("feher");
        kiraly.setPalya(palya);


    }

    @Test
    public void babuLepes(){
        jelenlegiMezo.setBabu(mozgoBabu);
        mozgoBabu.setJelenlegiMezo(jelenlegiMezo);

        mezoszam = 10;
        for (Mezo m: palya.getMezok()) {
            if (m.Mezoszam() == mezoszam){
                celMezo = m;  //(2,2)
                break;
            }
        }


        boolean siker = mozgoBabu.Lep(celMezo);
        Babu kezdetiBabu = jelenlegiMezo.getBabu();
        Babu celBabu = celMezo.getBabu();

        Assert.assertTrue("Nem sikerült a Babu lépése!", siker);
        Assert.assertNull("Nem törölte a Babut a kezdeti Mezőről!", kezdetiBabu);
        Assert.assertEquals("Nem került át a Babut a cél Mezőre!",mozgoBabu,celBabu);
    }



    @Test
    public void kiralyLepes(){
        jelenlegiMezo.setBabu(kiraly);
        kiraly.setJelenlegiMezo(jelenlegiMezo);

        mezoszam = 37;
        for (Mezo m: palya.getMezok()) {
            if (m.Mezoszam() == mezoszam) {
                celMezo = m;  //(5,5)
                break;
            }
        }


        boolean siker = kiraly.Lep(celMezo);
        Babu kezdetiBabu = jelenlegiMezo.getBabu();
        Babu celBabu = celMezo.getBabu();

        Assert.assertTrue("Nem sikerült a Kiraly lépése!", siker);
        Assert.assertNull("Nem törölte a Kiralyt a kezdeti Mezőről!", kezdetiBabu);
        Assert.assertEquals("Nem került át a Kiralyt a cél Mezőre!",kiraly,celBabu);
    }


    @Test
    public void babuUgras(){
        jelenlegiMezo.setBabu(mozgoBabu);
        mozgoBabu.setJelenlegiMezo(jelenlegiMezo);

        mezoszam = 10;
        for (Mezo m: palya.getMezok()) {
            if (m.Mezoszam() == mezoszam) {
                ugrottMezo = m;  //(2,2)
                break;
            }
        }
        ugrottMezo.setBabu(ugrottBabu);

        mezoszam = 19;
        for (Mezo m: palya.getMezok()) {
            if (m.Mezoszam() == mezoszam) {
                celMezo = m;  //(3,3)
                break;
            }
        }


        Mezo ugrottMezoTenyleges = mozgoBabu.Ugrik(celMezo);
        Babu kezdetiBabu = jelenlegiMezo.getBabu();
        Babu celBabu = celMezo.getBabu();
        Babu ugrottBabu = ugrottMezo.getBabu();

        Assert.assertNotNull("Nem sikerült a Babu ugrása",ugrottMezoTenyleges);
        Assert.assertEquals("Nem jó Mezőt ugrott a Babu!",ugrottMezo,ugrottMezoTenyleges);
        Assert.assertNull("Nem törölte a Babut a kezdeti Mezőről!",kezdetiBabu);
        Assert.assertEquals("Nem került át a Babut a cél Mezőre!",mozgoBabu,celBabu);
        Assert.assertNull("Nem törölte az ugrott Babut!",ugrottBabu);

    }

    @Test
    public void kiralyUgras(){
        jelenlegiMezo.setBabu(kiraly);
        kiraly.setJelenlegiMezo(jelenlegiMezo);

        mezoszam = 10;
        for (Mezo m: palya.getMezok()) {
            if (m.Mezoszam() == mezoszam) {
                ugrottMezo = m;  //(2,2)
                break;
            }
        }
        ugrottMezo.setBabu(ugrottBabu);

        mezoszam = 19;
        for (Mezo m: palya.getMezok()) {
            if (m.Mezoszam() == mezoszam) {
                celMezo = m;  //(3,3)
                break;
            }
        }


        Mezo ugrottMezoTenyleges = kiraly.Ugrik(celMezo);
        Babu kezdetiBabu = jelenlegiMezo.getBabu();
        Babu celBabu = celMezo.getBabu();
        Babu ugrottBabu = ugrottMezo.getBabu();
        boolean siker = kiraly.UgrottE();

        Assert.assertNotNull("Nem sikerült a Kiraly ugrása",ugrottMezoTenyleges);
        Assert.assertEquals("Nem jó Mezőt ugrott a Kiraly!",ugrottMezo,ugrottMezoTenyleges);
        Assert.assertNull("Nem törölte a Kiraly a kezdeti Mezőről!",kezdetiBabu);
        Assert.assertEquals("Nem került át a Kiraly a cél Mezőre!",kiraly,celBabu);
        Assert.assertNull("Nem törölte az ugrott Babut!",ugrottBabu);
        Assert.assertTrue("Ha ez előtt nincs hiba üzenet a Test-ben akkor hibás az UgrottE függvény",siker);

    }


    @Test
    public void kiralyUresUgras(){
        jelenlegiMezo.setBabu(kiraly);
        kiraly.setJelenlegiMezo(jelenlegiMezo);
        kiraly.setUgrott(true);

        mezoszam = 10;
        for (Mezo m: palya.getMezok()) {
            if (m.Mezoszam() == mezoszam) {
                ugrottMezo = m;  //(2,2)
                break;
            }
        }

        mezoszam = 19;
        for (Mezo m: palya.getMezok()) {
            if (m.Mezoszam() == mezoszam) {
                celMezo = m;  //(3,3)
                break;
            }
        }


        Mezo ugrottMezoTenyleges = kiraly.Ugrik(celMezo);
        Babu kezdetiBabu = jelenlegiMezo.getBabu();
        Babu celBabu = celMezo.getBabu();

        Assert.assertNotNull("Nem sikerült a Kiraly üres ugrása",ugrottMezoTenyleges);
        Assert.assertEquals("Nem jó üres Mezőt ugrott a Kiraly!",ugrottMezo,ugrottMezoTenyleges);
        Assert.assertNull("Nem törölte a Kiraly a kezdeti Mezőről!",kezdetiBabu);
        Assert.assertEquals("Nem került át a Kiraly a cél Mezőre!",kiraly,celBabu);

    }

}
