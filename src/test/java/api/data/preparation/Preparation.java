package api.data.preparation;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public abstract class Preparation {

    public static RequestSpecification login (){
        setup();
        return given()
                .auth()
                .form("AutoTest@AutoTest.aa", "Login123!!!",
                        new FormAuthConfig(
                                "/portal/j_spring_security_check",
                                "j_username",
                                "j_password")
                                )
                .contentType(ContentType.JSON);
    }

    private static void setup() {
        RestAssured.baseURI = "http://192.168.102.162:9090";
        RestAssured.basePath = "/portal/api";

        RestAssured.filters(new Filter() {
            @Override
            public Response filter(FilterableRequestSpecification requestSpec,
                                   FilterableResponseSpecification responseSpec, FilterContext ctx) {
                String csrfToken = requestSpec.getCookies().getValue("XSRF-TOKEN");
                String sessionId = requestSpec.getCookies().getValue("JSESSIONID");
                if (sessionId == null || csrfToken == null) {
                    Response response = RestAssured.given().noFilters().get("/login");
                    sessionId = response.cookie("JSESSIONID");
                    csrfToken = response.cookie("XSRF-TOKEN");
                }
                requestSpec.replaceCookie("JSESSIONID", sessionId);
                requestSpec.replaceHeader("X-XSRF-TOKEN", csrfToken);
                return ctx.next(requestSpec, responseSpec);
            }
        });
    }

}
