package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Sensor extends Model {

	private static final long serialVersionUID = -3004761492156995070L;

	@Id 
	public String id;
	@Required
	public String url;
	@Required
	public String name;
	@Required @ManyToOne
	public Server server;
	public String description;
	public String datasetURL;
	
	public static Finder<String,Sensor> find = new Finder<String,Sensor>(String.class, Sensor.class);
	
	public Sensor(String url) {
		this.id = url.replaceAll("http:|/|\\.|:", "");
		this.url = url;
		this.name = getName(url);
		this.server = getServer(url);
	}
	
	private static Server getServer(String sensorURL) {
		String[] baseURL = sensorURL.split(":\\d*/sensapp/registry/sensors/.*$");
		if (baseURL == null) {
			return null;
		}
		return Server.find.byId(baseURL[0]);
	}

	private static String getName(String sensorURL) {
		String[] name = sensorURL.split(".*/sensapp/registry/sensors/");
		if (name == null) {
			return "Unknown";
		}
		return name[1];
	}
	
	public static List<Sensor> all() {
		return find.all();
	}
	
	public static List<Sensor> all(Server server) {
		return find.where().eq("server", server).findList();
	}
	
	public static Sensor findByURL(String url) {
		return find.where().eq("url", url).findUnique();
	}	
}
