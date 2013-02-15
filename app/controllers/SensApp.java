package controllers;

import java.util.Iterator;

import models.Sensor;
import models.Server;

import org.codehaus.jackson.JsonNode;

import play.libs.F.Promise;
import play.libs.WS;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.sensor_list;

public class SensApp extends Controller {

	public static Result sensors() {
		//updateSensorAvailable();
		return ok(sensor_list.render(Sensor.all()));
	}
	 
	public static void updateSensorAvailable() {
		for (Server server : Server.all()) {
			Promise<WS.Response> promise = WS.url(server.baseURL + Server.SENSOR_REGISTRED_PATH).get();
			Iterator<JsonNode> sensors = promise.get().asJson().getElements();
			while (sensors.hasNext()) {
				JsonNode sensor = sensors.next();
				if (Sensor.find.byId(sensor.getTextValue()) == null) {
					System.out.println("Add sensor: " + sensor.getTextValue());
					new Sensor(sensor.getTextValue()).save();
				}
			}
		}
	}
}
