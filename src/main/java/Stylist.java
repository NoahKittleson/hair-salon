import org.sql2o.*;
import java.util.List;

public class Stylist {
  private int id;
  private String name;
  private String specialty;


  public Stylist (String name, String specialty) {
    this.name = name;
    this.specialty = specialty;
  }

  public String getName() {
    return name;
  }

  public String getSpecialty () {
    return specialty;
  }

  public int getId() {
    return id;
  }

  public static List<Stylist> all() {
    String sql = "SELECT id, name, specialty FROM stylists";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  @Override
  public boolean equals(Object otherStylist) {
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName())
      && this.getId() == newStylist.getId()
      && this.getSpecialty().equals(newStylist.getSpecialty());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists (name, specialty) VALUES (:name, :specialty)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("specialty", this.specialty)
        .executeUpdate()
        .getKey();
    }
  }

  public static Stylist find (int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists where id=:id";
      Stylist stylist = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Stylist.class);
      return stylist;
    }
  }

  public List<Client> getClients() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients where stylist_id=:stylist_id";
      return con.createQuery(sql)
        .addParameter("stylist_id", this.id)
        .executeAndFetch(Client.class);
    }
  }
}
