package api.tests;

import tests.userPageTests.userPageTestData.User;

import static api.baseApiMethods.UserApi.createUserApi;

public class test2 {
    public static void main(String[] args) {
        User user = new User();

        createUserApi(user.getJson());

    }
}
