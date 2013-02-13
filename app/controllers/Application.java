package controllers;

import models.Sensor;
import models.Server;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {
  
    public static Result index() {
    	if (Server.all().size() == 0) {
    		Server server = new Server("testSensapp", "http://www.google.fr");
    		server.save();
    		new Sensor("sensorTest", server).save();
    	}
        return ok(index.render("Your new application is ready."));
    }
  
}
