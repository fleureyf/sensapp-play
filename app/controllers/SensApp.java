package controllers;

import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.WS;
import play.mvc.Controller;
import play.mvc.Result;

public class SensApp extends Controller {

	public static Result sensors() {
		Promise<WS.Response> sensorsList = WS.url("http://demo.sensapp.org/sensapp/registry/sensors").get();
		Promise<Result> result = sensorsList.map(new Function<WS.Response, Result>() {
			public Result apply(WS.Response response) {
				return ok(response.asJson()); 
			}
		});
		return async(result);
	}
	
}
