import Model.Buyer;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FilterAndSort {
    @BeforeClass
    public static void createServers_B() throws IOException {
        Buyer buyer = new Buyer("a","a","a","a@a.com","1321","32132");
        assertEquals(buyer.getUsername(),"b");
    }
    @Test
    public void testClientCreation_B() throws IOException {
        
    }
}
