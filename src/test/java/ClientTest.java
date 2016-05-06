import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class ClientTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteStylistsQuery = "DELETE FROM stylists *;";
      String deleteClientsQuery = "DELETE FROM clients *;";
      con.createQuery(deleteStylistsQuery).executeUpdate();
      con.createQuery(deleteClientsQuery).executeUpdate();
    }
  }

  @Test
  public void Client_instantiatesCorrectly_true() {
    Client myClient = new Client("Harold", "1999-01-01");
    assertEquals(true, myClient instanceof Client);
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Client.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfFieldsAretheSame() {
    Client firstClient = new Client("Harold", "1999-01-01");
    Client secondClient = new Client("Harold", "1999-01-01");
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void equals_findsClientById() {
    Client originalClient = new Client("Harold", "1999-01-01");
    originalClient.save();
    Client copyClient = Client.find(originalClient.getId());
    assertEquals(copyClient.getAppointment(), originalClient.getAppointment());
    assertTrue(copyClient.equals(originalClient));
  }
}
