package models;

import java.util.HashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Sensor extends Model {

	@Id 
	public long id;
	@Required
	public String name;
	@Required @ManyToOne
	public Server server;
	
	public static Finder<Long,Sensor> find = new Finder<Long,Sensor>(Long.class, Sensor.class);
	
	public Sensor(String name, Server server) {
		this.name = name;
		this.server = server;
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
