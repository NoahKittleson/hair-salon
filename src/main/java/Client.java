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

  public Client (String name, String appointment) {
    //Timestamp timestamp = new Timestamp(0,0,0,0,0,0,0);
    try {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      this.appointment = dateFormat.parse(appointment);
      //timestamp = new java.sql.Timestamp(parsedDate.getTime());
    }
    catch(Exception e) {

    }
    this.name = name;
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

  @Override
  public boolean equals(Object otherClient) {
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName())
      && this.getId() == newClient.getId()
      && this.getAppointment().equals(newClient.getAppointment());
    }
  }


}
