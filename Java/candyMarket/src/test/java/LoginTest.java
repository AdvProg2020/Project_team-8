import Client.Model.Buyer;
import Client.Model.Manager;
import Client.Model.User;
import javafx.css.Match;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class LoginTest {

    public static Manager manager;

    @BeforeClass
    public static void createAManager() {
        manager = new Manager("alex", "alex", "alexi", "alex@yahoo.com", "091234567", "alex");
    }


    @Test
    public void testLogin() {
        Assert.assertEquals(manager.isUsernameAndPasswordCorrect("alex", "alex"), true);
    }

}

