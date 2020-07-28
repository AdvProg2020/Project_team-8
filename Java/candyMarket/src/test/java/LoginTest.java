import Client.Model.Buyer;
import Client.Model.Manager;
import Client.Model.Seller;
import Client.Model.User;
import javafx.css.Match;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class LoginTest {

    public static Seller seller;

    @BeforeClass
    public static void createAManager() {
        seller= new Seller("alex", "alex", "alexi", "alex@yahoo.com", "091234567", "alex","31232");
    }

    @Test
    public void trueTestLogin() {
        Assert.assertEquals(seller.isUsernameAndPasswordCorrect("alex", "alex"), true);
    }
    @Test
    public void wrongTestLogin() {
        Assert.assertEquals(seller.isUsernameAndPasswordCorrect("alex", "al"), false);
    }

}

