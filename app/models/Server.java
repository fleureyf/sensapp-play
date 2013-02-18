package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Server extends Model {

	public static final String SENSOR_REGISTRED_PATH = "/sensapp/registry/sensors";
	
	private static final long serialVersionUID = -8598242191027063099L;

	@Id
	public String baseURL;
	@Required 
	public int port;
	public String name;
	@OneToMany(mappedBy="server", cascade=CascadeType.ALL)
	public List<Sensor> sensors;
	
	public static Finder<String,Server> find = new Finder<String,Server>(String.class, Server.class); 
	
	public Server(String baseURL, int port) {
		this.baseURL = baseURL;
		this.port = port;
	}
	
	public Server(String baseURL, int port, String name) {
		this.baseURL = baseURL;
		this.port = port;
		this.name = name;
	}
	
	public static List<Server> all() {
		return find.all();
	}
}
