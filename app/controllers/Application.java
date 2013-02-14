package controllers;

import models.Server;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {
  
    public static Result index() {
    	if (Server.all().size() == 0) {
    		Server server = new Server("http://demo.sensapp.org", 80, "Demo server");
    		server.save();
    	}
        return ok(index.render("Your new application is ready."));
    }
  
}
