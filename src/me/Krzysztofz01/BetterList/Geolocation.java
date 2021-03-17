package me.Krzysztofz01.BetterList;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Geolocation {
	private static HttpClient client = HttpClient.newBuilder().build();

	public static String GetLocation(String ipAddress) {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://freegeoip.app/json/" + ipAddress))
				.GET()
				.setHeader("accept", "application/json")
				.setHeader("content-type", "application/json")
				.build();
		
		try {
			var response = client.send(request, BodyHandlers.ofString());
			ObjectMapper mapper = new ObjectMapper();
			
			Geoip geoip = mapper.readValue(response.body().toString(), Geoip.class);
			return geoip.country_name;
			
		} catch (IOException e) {
			return "No data!";
		} catch (InterruptedException e) {
			return "No data!";
		}
	}
}
