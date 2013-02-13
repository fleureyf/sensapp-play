package controllers;

import java.util.concurrent.Callable;

import play.libs.Akka;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.WS;
import play.libs.WS.Response;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.BodyParser;

public class SensApp extends Controller {

	public static Result sensors() {
		Promise<WS.Response> sensorsList = WS.url("http://demo.sensapp.org/sensapp/registry/sensors").get();
		Response response = sensorsList.get();
		return ok(response.asJson());
//		Promise<Result> result = sensorsList.map(new Function<WS.Response, Result>() {
//			public Result apply(WS.Response response) {
//				return ok(response.asJson()); 
//			}
//		});
//		return async(result);
	}
	
}
