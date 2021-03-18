package me.Krzysztofz01.BetterList;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

public class Geolocation {
	private static HttpClient client = HttpClient.newBuilder().build();

	public static String GetLocation(String ipAddress) {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://freegeoip.app/json/" + ipAddress.trim()))
				.GET()
				.setHeader("accept", "application/json")
				.setHeader("content-type", "application/json")
				.build();
		
		try {
			var response = client.send(request, BodyHandlers.ofString());
			return GetCountryName(response.body().toString());	
		} catch (IOException e) {
			return "No data!";
		} catch (InterruptedException e) {
			return "No data!";
		}
	}
	
	private static String GetCountryName(String response) {
		String[] parts = response.split("\"");
		for(int i=0; i < parts.length; i++) {
			if(parts[i].equals("country_name")) {
				int countryIndex = i + 2;
				if(countryIndex <= parts.length) {
					return parts[countryIndex];
				}
			}
		}
		return "No data!";
	}
}
