package api.tests;

import io.restassured.http.Cookie;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.Map;

import static api.data.endPoints.EndPoints.getLoginToken;
import static io.restassured.RestAssured.get;



public class SSSE {

    public static void main(String[] args) throws IOException {
        HttpUriRequest request = new HttpGet(getLoginToken);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

    }
}
