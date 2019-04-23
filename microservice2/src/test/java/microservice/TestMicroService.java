package microservice;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public class TestMicroService {

	@Test
	@DirtiesContext
	public void testFindFormations() throws ClientProtocolException, IOException {
		// Given
		HttpUriRequest request = new HttpGet("http://localhost:8080/api/v1/formations/");

		// When
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		// Then
		assertTrue(httpResponse.getStatusLine().getStatusCode() == 200);
	}

	@Test
	@DirtiesContext
	public void testFindByID() throws ClientProtocolException, IOException {
		// Given
		HttpUriRequest request = new HttpGet("http://localhost:8080/api/v1/formations/1");

		// When
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		// Then
		assertTrue(httpResponse.getStatusLine().getStatusCode() == 200);
	}

	@Test
	@DirtiesContext
	public void testCreateTheme() throws ClientProtocolException, IOException {

		// Instanciation d'un objet json
		JSONObject obj = new JSONObject();
		obj.put("theme", "Ninja");

		HttpClient httpClient = HttpClientBuilder.create().build();

		HttpPost request = new HttpPost("http://localhost:8080/api/v1/formations/");
		StringEntity params = new StringEntity(obj.toString());
		request.addHeader("content-type", "application/json");
		request.setEntity(params);
		HttpResponse response = httpClient.execute(request);

		// Vérification du code retours
		assertTrue(response.getStatusLine().getStatusCode() == 200);

	}

	@Test
	@DirtiesContext
	public void testCreateManyTheme() throws ClientProtocolException, IOException {

		// Instanciation d'un objet json
		JSONObject obj = new JSONObject();
		obj.put("theme", "Ninja");

		JSONObject obj2 = new JSONObject();
		obj.put("theme", "Killer");

		HttpClient httpClient = HttpClientBuilder.create().build();

		HttpPost request = new HttpPost("http://localhost:8080/api/v1/formations/");
		HttpPost request2 = new HttpPost("http://localhost:8080/api/v1/formations/");
		StringEntity params = new StringEntity(obj.toString());
		StringEntity params2 = new StringEntity(obj2.toString());
		request.addHeader("content-type", "application/json");
		request.setEntity(params);
		request2.setEntity(params2);

		HttpResponse response = httpClient.execute(request);
		HttpResponse response2 = httpClient.execute(request2);

		// Vérification du code retours
		assertTrue(response.getStatusLine().getStatusCode() == 200);
		// assertTrue(response2.getStatusLine().getStatusCode() == 200);

	}

	@Test
	@DirtiesContext
	public void testUpdateTheme() throws ClientProtocolException, IOException {

		// Instanciation d'un objet json
		JSONObject obj = new JSONObject();
		obj.put("id", "1");
		obj.put("theme", "Samourai");

		HttpClient httpClient = HttpClientBuilder.create().build();

		HttpPut request = new HttpPut("http://localhost:8080/api/v1/formations/");
		StringEntity params = new StringEntity(obj.toString());
		request.addHeader("content-type", "application/json");
		request.setEntity(params);
		HttpResponse response = httpClient.execute(request);

		// Vérification du code retours
		assertTrue(response.getStatusLine().getStatusCode() == 200);

	}

	@Test
	@DirtiesContext
	public void testDeleteTheme() throws ClientProtocolException, IOException {

		// Instanciation d'un objet json
		JSONObject obj = new JSONObject();
		obj.put("theme", "Ninja");
		

		HttpClient httpClient = HttpClientBuilder.create().build();

		HttpPost request = new HttpPost("http://localhost:8080/api/v1/formations/");
		StringEntity params = new StringEntity(obj.toString());
		request.addHeader("content-type", "application/json");
		request.setEntity(params);
		HttpResponse response = httpClient.execute(request);
		
		

		// Vérification du code retours
		assertTrue(response.getStatusLine().getStatusCode() == 200);

		// HttpClient httpClient = HttpClientBuilder.create().build();
		HttpDelete request3 = new HttpDelete("http://localhost:8080/api/v1/formations/2");
		HttpResponse response3 = httpClient.execute(request3);

		// Vérification du code retours
		assertTrue(response3.getStatusLine().getStatusCode() == 200);

	}
}
