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
        ManageInfo.allUsers.add(buyer);
    }

    @Test
    public void trueTestLogin() {
        Assert.assertEquals(true, buyer.isUsernameAndPasswordCorrect("alex", "alex"));
    }
    @Test
    public void wrongTestLogin() {
        Assert.assertEquals(false, buyer.isUsernameAndPasswordCorrect("alex", "al"));
    }

}

