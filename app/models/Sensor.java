package models;

import java.util.HashMap;
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
	public String url;
	@Required
	public String name;
	@Required @ManyToOne
	public Server server;
	
	public static Finder<String,Sensor> find = new Finder<String,Sensor>(String.class, Sensor.class);
	
	public Sensor(String url) {
		this.url = url;
		this.name = getName(url);
		this.server = getServer(url);
	}
	
	private static Server getServer(String sensorURL) {
		String[] baseURL = sensorURL.split(":\\d*/sensapp/registry/sensors/.*$");
		return Server.findByURL(baseURL[0]);
	}

	private static String getName(String sensorURL) {
		String[] name = sensorURL.split(".*/sensapp/registry/sensors/");
		return name[1];
	}
	
	public static List<Sensor> all() {
		return find.all();
	}
	
	public static List<Sensor> all(Server server) {
		return find.where().eq("server", server).findList();
	}
	
	
	
	public static Sensor findByName(String name, String server) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("name", name);
		map.put("server", server);
		return find.where().allEq(map).findUnique();
	}	
}
