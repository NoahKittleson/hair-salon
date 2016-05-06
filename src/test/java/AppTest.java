import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.sql2o.*;
import org.junit.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Before
    public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteTableQuery = "DELETE FROM stylists *;";
      String deleteOtherTableQuery = "DELETE FROM clients *;";
      con.createQuery(deleteTableQuery).executeUpdate();
      con.createQuery(deleteOtherTableQuery).executeUpdate();
    }
  }

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Welcome!");
  }

  @Test
  public void addNewStylist() {
    Stylist testStylist = new Stylist("Mike", "Mohawks");
    testStylist.save();
    goTo("http://localhost:4567/stylists");
    assertThat(pageSource()).contains("Mike");
  }
}
