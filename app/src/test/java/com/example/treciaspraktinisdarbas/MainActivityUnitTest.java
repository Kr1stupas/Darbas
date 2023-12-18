import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

import com.example.treciaspraktinisdarbas.MainActivity;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, sdk = 24)
public class MainActivityUnitTest {

    @Test
    public void testGetResult_validExpression1() {
        MainActivity mainActivity = new MainActivity();
        String result = mainActivity.getResult("2+2");
        assertEquals("4", result);
    }

    @Test
    public void testGetResult_validExpression2() {
        MainActivity mainActivity = new MainActivity();
        String result = mainActivity.getResult("-25");
        assertEquals("-25", result);
    }
    @Test
    public void testGetResult_validExpression3() {
        MainActivity mainActivity = new MainActivity();
        String result = mainActivity.getResult("2*8");
        assertEquals("16", result);
    }
}

