package me.Krzysztofz01.BetterList;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.mojang.authlib.yggdrasil.response.Response;

public class Geolocation {
	private HttpClient client;
	
	public Geolocation() {
		this.client = HttpClient.newBuilder().build();
	}
	
	/*public String GetLocation(String ipAddress) {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://freegeoip.app/json/"))
				.GET()
				.setHeader("accept", "application/json")
				.setHeader("content-type", "application/json")
				.build();
		
		HttpResponse<GeoResponse> response = this.client.send(request, BodyHandlers.replacing(GeoResponse));
		return null;
	}*/
}
