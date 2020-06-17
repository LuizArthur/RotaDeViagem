package com.rotadeviagem;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.rotadeviagem.utils.JsonUtils;

public class Http {
	private final static CloseableHttpClient httpClient = HttpClients.createDefault();
	
	public static JsonNode get(String departureAirportIataCode, String arrivalAirportIataCode, String inputsPath) {
		HttpGet request = new HttpGet(
			String
			.format(
				"http://localhost:8080/query/route?departureAirportCode=%s&arrivalAirportCode=%s&inputsPath=%s",
				departureAirportIataCode,
				arrivalAirportIataCode,
				inputsPath
			));
		
		try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            String resultString = EntityUtils.toString(entity);
            
            JsonNode result = JsonUtils.stringToJson(resultString);

			return result;

        } catch (Exception e) {
			System.out.println("Request Error");
			return null;
		}		
	}
}
