package me.Krzysztofz01.BetterList;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Geolocation {
	private static HttpClient client = HttpClient.newBuilder().build();
	private static Pattern jsonCountryPattern = Pattern.compile(".*?country_name\"\\s?:\\s?\"([\\w\\s]+)");

	//Make a GET request to the freegeoip API to get the country based on IP
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
	
	//I don't wanna use external lib's, so i wrote a simple regex to extract the country name from the JSON respond
	private static String GetCountryName(String response) {
		Matcher match = jsonCountryPattern.matcher(response);
		while(match.find()) {
			return match.group(1);
		}
		return "No data!";
	}
}
