import Mukodes.Babu;
import Mukodes.Mezo;
import Mukodes.Palya;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MezoTesztek {
    private Palya palya;
    private Mezo mezo;
    int mezoszam;

    @Before
    public void mezoInit(){
        palya = new Palya();
        for (Mezo m: palya.getMezok())
            m.setBabu(null);
    }

    @Test
    public void mezoSorszam(){
        mezo = new Mezo(1,1);

        mezoszam = 1;
        int elvartMezoszam = mezo.Mezoszam();

        Assert.assertEquals("Nem jól számolja ki a Mező a sorszámát a sor és az oszlop számából!",elvartMezoszam, mezoszam);
    }

    @Test
    public void kiralyLeszEAtalakit(){
        mezoszam = 1;
        for (Mezo m: palya.getMezok()) {
            if (m.Mezoszam() == mezoszam) {
                mezo = m;  //(1,1)
                break;
            }
        }
        Babu feketeBabu = new Babu("fekete");
        mezo.setBabu(feketeBabu);
        feketeBabu.setJelenlegiMezo(mezo);

        Babu kiraly = mezo.KiralyLeszE();

        Assert.assertNotNull("Nem alakítja át királlya, pedig fekete bábu van a fehér első sorába!",kiraly);

    }

    @Test
    public void kiralyLeszENemAlakitAt(){
        mezoszam = 1;
        for (Mezo m: palya.getMezok()) {
            if (m.Mezoszam() == mezoszam) {
                mezo = m;  //(1,1)
                break;
            }
        }
        Babu feherBabu = new Babu("feher");
        mezo.setBabu(feherBabu);
        feherBabu.setJelenlegiMezo(mezo);

        Babu NemKiraly = mezo.KiralyLeszE();

        Assert.assertNull("Atalakítja királlya, pedig fehér bábu van fehér elso sorába!",NemKiraly);

    }

}
