package com.qacart.todo.testCase.api;

import com.qacart.todo.testCase.login;
import com.qacart.todo.testCase.login2;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApi {
    public static Response register(login2 body2){
      return given()
                .baseUri("https://qacart-todo.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(body2)
                .when().post("/api/v1/users/register")
                .then().log().all().extract().response();
    }

    public static Response login(login2 body){
        return given().baseUri("https://qacart-todo.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/api/v1/users/login")
                .then().log().all().extract().response();

    }

    public static Response learn(login2 body){
        return given().baseUri("https://qacart-todo.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/api/v1/users/register")
                .then().log().all().extract().response();
    }
}
