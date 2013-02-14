package controllers;

import java.util.Iterator;

import models.Sensor;
import models.Server;

import org.codehaus.jackson.JsonNode;

import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.WS;
import play.mvc.Controller;
import play.mvc.Result;

public class SensApp extends Controller {

	public static Result sensors() {
		updateSensorAvailable();
		Promise<WS.Response> sensorsList = WS.url("http://demo.sensapp.org/sensapp/registry/sensors").get();
		Promise<Result> result = sensorsList.map(new Function<WS.Response, Result>() {
			public Result apply(WS.Response response) {
				return ok(response.asJson()); 
			}
		});
		return async(result);
	}
	 
	public static void updateSensorAvailable() {
		for (Server server : Server.all()) {
			Promise<WS.Response> promise = WS.url(server.baseURL + Server.SENSOR_REGISTRED_PATH).get();
			Iterator<JsonNode> sensors = promise.get().asJson().getElements();
			while (sensors.hasNext()) {
				JsonNode sensor = sensors.next();
				System.out.println("Add sensor: " + sensor.getTextValue());
				new Sensor(sensor.getTextValue()).save();
			}
		}
	}
}
