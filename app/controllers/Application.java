package controllers;

import models.Sensor;
import models.Server;
import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
  
    public static Result index() {
    	Server server = new Server("testSensapp", "http://www.google.fr");
    	server.save();
    	new Sensor("sensorTest", server).save();
        return ok(index.render("Your new application is ready."));
    }
  
}
