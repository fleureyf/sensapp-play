package controllers;

import java.io.IOException;
import java.util.Iterator;

import models.JsonData;
import models.Sensor;
import models.Server;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import play.libs.F.Promise;
import play.libs.WS;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.data_table;
import views.html.sensor_list;

public class SensApp extends Controller {

	private static final ObjectMapper mapper = new ObjectMapper();
	
	public static Result sensors() {
		updateSensorAvailable();
		return ok(sensor_list.render(Sensor.all()));
	}
	 
	public static void updateSensorAvailable() {
		for (Server server : Server.all()) {
			Promise<WS.Response> promise = WS.url(server.baseURL + Server.SENSOR_REGISTRED_PATH).get();
			Iterator<JsonNode> sensors = promise.get().asJson().getElements();
			while (sensors.hasNext()) {
				JsonNode sensor = sensors.next();
				if (Sensor.findByURL(sensor.getTextValue()) == null) {
					System.out.println("Add sensor: " + sensor.getTextValue());
					new Sensor(sensor.getTextValue()).save();
				}
			}
		}
	}
	
	public static void updateSensorDetails(Sensor sensor) {
		Promise<WS.Response> promise = WS.url(sensor.url).get();
		JsonNode root = promise.get().asJson();
		sensor.description = root.get("descr").getTextValue();
		sensor.datasetURL = root.get("backend").get("dataset").getTextValue();
		sensor.save();
	}
	
	public static Result getData(String sensorId) {
		Promise<WS.Response> promise = WS.url(Sensor.find.byId(sensorId).datasetURL).get();
		JsonNode root = promise.get().asJson();
		JsonData data = null;
		try {
			data = mapper.readValue(root, JsonData.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ok(data_table.render(data));
	}
}