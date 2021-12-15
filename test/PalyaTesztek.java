import Mukodes.Mezo;
import Mukodes.Palya;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PalyaTesztek {
    private Palya palya;
    private Mezo mezo;
    int mezoszam;

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

    }

}
