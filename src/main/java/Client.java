import org.sql2o.*;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {
  private int id;
  private String name;
  private Date appointment;
  private int stylist_id;

  public Client (String name, String appointment, int stylist_id) {
    try {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      this.appointment = dateFormat.parse(appointment);
    }
    catch(Exception e) {

    }
    this.name = name;
    this.stylist_id = stylist_id;
  }

  public String getName() {
    return name;
  }

  public Date getAppointment() {
    return appointment;
  }

  public int getId() {
    return id;
  }

  public int getStylistId() {
    return stylist_id;
  }

  @Override
  public boolean equals(Object otherClient) {
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName())
      && this.getId() == newClient.getId()
      && this.getStylistId() == newClient.getStylistId()
      && this.getAppointment().equals(newClient.getAppointment());
    }
  }

  public static List<Client> all() {
    String sql = "SELECT id, name, appointment, stylist_id FROM clients";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (name, appointment, stylist_id) VALUES (:name, :appointment, :stylist_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("appointment", this.appointment)
        .addParameter("stylist_id", this.stylist_id)
        .executeUpdate()
        .getKey();
    }
  }

  public static Client find (int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients where id=:id";
      Client client = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
      return client;
    }
  }


}
