package com.qacart.todo.testCase.Steps;

import com.github.javafaker.Faker;
import com.qacart.todo.testCase.api.UserApi;
import com.qacart.todo.testCase.login2;
import io.restassured.response.Response;

public class UserSteps {

    public static login2 generateUser(){
        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

       return new login2(firstName,lastName,email,password);

    }

    public static login2 getRegisterUser(){
        //invalid data using the same email
        login2 body = generateUser();
        UserApi.register(body);
        return body;
    }

    public static String getUserToken(){
        login2 body = generateUser();
        Response response = UserApi.register(body);
        return response.body().path("access_token");

    }

}
