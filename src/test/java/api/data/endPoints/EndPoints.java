package api.data.endPoints;

import io.restassured.RestAssured;

public abstract class EndPoints {

    private static final String baseApi = "http://192.168.102.162:9090/portal";

    public final static String getLoginToken = baseApi+"/api";
    public final static String postLogin = baseApi+"/j_spring_security_check";
    public final static String postCreateUser = "/users/create";
    public final static String deleteDeleteUser = "/users/{id}";
    public final static String getUsersList = "/users/search";

}
