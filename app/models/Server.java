package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Constraint;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Server extends Model {

	@Id
	public String baseURL;
	public String name;
	
	@OneToMany(mappedBy="server", cascade=CascadeType.ALL)
	public List<Sensor> sensors;
	
	public static Finder<String,Server> find = new Finder<String,Server>(String.class, Server.class); 
	
	public Server(String baseURL) {
		this.baseURL = baseURL;
	}
	
	public Server(String name, String baseURL) {
		this.baseURL = baseURL;
		this.name = name;
	}
	
	public static List<Server> all() {
		return find.all();
	}
	
	public static Server findByURL(String baseURL) {
		return find.where().eq("baseURL", baseURL).findUnique();
	}
}
