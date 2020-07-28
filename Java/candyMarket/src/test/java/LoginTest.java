import Server.Model.*;
import javafx.css.Match;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class LoginTest {

    public static Buyer buyer;

    @BeforeClass
    public static void createAManager() {
        buyer= new Buyer("alex", "alex", "alexi", "alex@yahoo.com", "091234567", "alex");
        ManageInfo.allBuyers.add(buyer);
    }

    @Test
    public void trueTestLogin() {
        Assert.assertEquals(buyer.isUsernameAndPasswordCorrect("alex", "alex"), true);
    }
    @Test
    public void wrongTestLogin() {
        Assert.assertEquals(buyer.isUsernameAndPasswordCorrect("alex", "al"), false);
    }

}

