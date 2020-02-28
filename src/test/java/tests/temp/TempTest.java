package tests.temp;

import flow.BaseTestMethods;
import org.testng.annotations.Test;

public class TempTest extends BaseTestMethods {

    @Test
    public void ttt(){
        login();
        deleteAllAbbrevNumbers();
    }
}
