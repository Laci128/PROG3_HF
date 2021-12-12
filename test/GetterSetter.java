import Mukodes.Mezo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GetterSetter {
    private Mezo mezo;

    @Before
     public void init() {
        mezo = new Mezo(1,2);
    }

    @Test
    public void XYGetterTest(){
        int x = mezo.getSor();
        int y = mezo.getOszlop();
        Assert.assertEquals("Nem jó a Mező sor koordinátája",1,x);
        Assert.assertEquals("Nem jó a Mező oszlop koordinátája",2,y);
    }

    @Test
    public void XYSetterTest(){
        int x = 2;
        mezo.setSor(x);
        int sor = mezo.getSor();

        int y = 3;
        mezo.setOszlop(y);
        int oszlop = mezo.getOszlop();
        Assert.assertEquals("Nem változott a Mező sor koordinátája",x, sor);
        Assert.assertEquals("Nem változott a Mező oszlop koordinátája",y,oszlop);
    }
}
