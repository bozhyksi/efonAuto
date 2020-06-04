package api.data.endPoints;

public final class EndPoints {

    private static final String baseApi = "http://192.168.102.162:9090/portal";

    public final static String getLoginToken = baseApi+"/login";
    public final static String postLogin = baseApi+"/j_spring_security_check";
    public final static String postCreateUser = baseApi+"/api/users/create";
    public final static String deleteDeleteUser = baseApi+"/api/users/{id}";

}
