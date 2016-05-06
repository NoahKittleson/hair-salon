import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class StylistTest {

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
  public void Stylist_instantiatesCorrectly_true() {
    Stylist myStylist = new Stylist("Su", "Afros");
    assertEquals(true, myStylist instanceof Stylist);
  }


  @Test
  public void Stylist_reviewInstantiesWithTitle_true() {
    Stylist myStylist = new Stylist("Su", "Afros");
    assertEquals("Su", myStylist.getName());
  }
  
  @Test
  public void all_emptyAtFirst() {
    assertEquals(Stylist.all().size(), 0);
  }
  //
  // @Test
  // public void equals_returnsTrueIfFieldsAretheSame() {
  //   Stylist firstStylist = new Stylist("Su", "Afros");
  //   Stylist secondStylist = new Stylist("Su", "Afros");
  //   assertTrue(firstStylist.equals(secondStylist));
  // }
  //
  // @Test
  // public void save_savesIntoDatabase_true() {
  //   Stylist myStylist = new Stylist("Su", "Afros");
  //   myStylist.save();
  //   assertTrue(Stylist.all().get(0).equals(myStylist));
  // }
  //
  // @Test
  // public void save_assignsIdToObject() {
  //   Stylist myStylist = new Stylist("Su", "Afros");
  //   myStylist.save();
  //   Stylist savedStylist = Stylist.all().get(0);
  //   assertEquals(myStylist.getId(), savedStylist.getId());
  // }
  //
  // @Test
  // public void find_findStylistInDatabase_true() {
  //   Stylist myStylist = new Stylist("Su", "Afros");
  //   myStylist.save();
  //   Stylist savedStylist = Stylist.find(myStylist.getId());
  //   assertTrue(myStylist.equals(savedStylist));
  // }
  //
  // @Test
  // public void getClients_retrievesAllClientsFromDataBase_reviewsList() {
  //   Stylist myStylist = new Stylist("Su", "Afros");
  //   myStylist.save();
  //   Client firstClient = new Client("Su rules", "yo but it totally does", myStylist.getId());
  //   firstClient.save();
  //   Client secondClient = new Client("Su sucks", "naw jk", myStylist.getId());
  //   secondClient.save();
  //   Client[] tasks = new Client[] { firstClient, secondClient };
  //   assertTrue(myStylist.getClients().containsAll(Arrays.asList(tasks)));
  // }

}
