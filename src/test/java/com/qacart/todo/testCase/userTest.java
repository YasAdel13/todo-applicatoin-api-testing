package com.qacart.todo.testCase;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import com.qacart.todo.testCase.Steps.UserSteps;
import com.qacart.todo.testCase.api.UserApi;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;

public class userTest {

    RequestSpecification request = given()
            .baseUri("https://qacart-todo.herokuapp.com")
            .log().all();

    @Test
    public void shouldBeAbleToRegister(){


        String body = "{\n" +
                "\t\"firstName\":\"yasmine\",\n" +
                "\t\"lastName\":\"Adel\",\n" +
                "  \"email\": \"yasmineadel1311995@gmail.com\",\n" +
                "  \"password\": \"123456\"\n" +
                "}";

     Response response = given().baseUri("https://qacart-todo.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/api/v1/users/register")
                .then().log().all().extract().response();

        assertThat(response.statusCode(),equalTo(201));
        assertThat(response.path("firstName"),equalTo("yasmine"));

        System.out.println(response.asString());
    }
    @Test
    public void shouldBeAbleToRegister2(){

        login2 body2 = new login2();
        body2.setFirstName("Huda");
        body2.setLastName("helmy");
        body2.setEmail("hudaH123@gmail.com");
        body2.setPassword("888888");

       Response response = given().baseUri("https://qacart-todo.herokuapp.com")
                .contentType(ContentType.JSON)
                .log().all()
                .body(body2)
                .when().post("/api/v1/users/register")
                .then().log().all().extract().response();

       assertThat(response.statusCode(),equalTo(201));
       assertThat(response.path("firstName"),equalTo("Huda"));
    }
    @Test
    public void shouldBeAbleToRegister3(){

        HashMap<String,String> body1 = new HashMap<>();
        body1.put("firstName","Ali");
        body1.put("lastName","Ali");
        body1.put("email","aliAli3@gmail.com");
        body1.put("password","34567");

       Response response = given().baseUri("https://qacart-todo.herokuapp.com")
                .contentType(ContentType.JSON)
                .log().all()
                .body(body1)
                .when().post("/api/v1/users/register")
                .then().log().all().extract().response();

        assertThat(response.statusCode(),is(equalTo(201)));
        assertThat(response.path("firstName"),equalTo("Ali"));
    }
    @Test
    public void shouldBeAbleToRegister4(){
        HashMap<String,String> body1 = new HashMap<>();
        body1.put("firstName","Hassan");
        body1.put("lastName","Ali");
        body1.put("email","HassanAli@gmail.com");
        body1.put("password","34567");

        given()
                .spec(request)
                .contentType(ContentType.JSON)
                .body(body1)
                .when().post("/api/v1/users/register")
                .then().log().all()
                .assertThat().statusCode(201)
                .assertThat().body("firstName",is(equalTo("Hassan")));
    }

    @Test
    public void shouldBeAbleToRegister5(){

        login body2 = new login();
        body2.setFirstName("Ahmed");
        body2.setLastName("Adel");
        body2.setEmail("AhmedAdell@gmail.com");
        body2.setPassword("65843");


        given()
                .spec(request)
                .contentType(ContentType.JSON)
                .body(body2)
                .when().post("/api/v1/users/register")
                .then().log().all()
                .assertThat().statusCode(201)
                .assertThat().body("firstName",is(equalTo("Ahmed")));
    }

    @Test
    public void shouldBeAbleToRegister7(){

        login2 body2 = new login2();
        body2.setFirstName("Ahmed");
        body2.setLastName("Adel");
        body2.setEmail("AhmedAdellll@gmail.com");
        body2.setPassword("65843");


       Response response = UserApi.register(body2);

       login returnedLogin = response.body().as(login.class);

       assertThat(response.statusCode(),equalTo(201));
       assertThat(returnedLogin.getFirstName(),equalTo(body2.getFirstName()));


    }

    @Test
    public void shouldBeAbleToRegister6(){

    login2 body3 = new  login2("mohamed","moustafa","mohamed123456@gmail.com","1234567");

       Response response = given()
                .spec(request)
                .contentType(ContentType.JSON)
                .body(body3)
                .when().post("/api/v1/users/register")
                .then().log().all().extract().response();

       login2 returnedlogin2 = response.body().as(login2.class);

       assertThat(response.statusCode(),equalTo(201));
       assertThat(returnedlogin2.getFirstName(),equalTo(body3.getFirstName()));



    }

    @Test
    public void shouldBeAbleToRegister8(){

        File file = new File("src/test/resources/login2.json");

        given()
                .spec(request)
                .contentType(ContentType.JSON)
                .body(file)
                .when().post("/api/v1/users/register")
                .then().log().all()
                .assertThat().statusCode(201)
                .assertThat().body("firstName",is(equalTo("farah")));
    }

    @Test
    public void shouldBeNotAbleToRegister9(){
        login2 body3 = new  login2("mohamed","moustafa","mohamed123455@gmail.com","1234567");

       Response response =  given()
                .spec(request)
                .contentType(ContentType.JSON)
                .body(body3)
                .when().post("/api/v1/users/register")
                .then().log().all().extract().response();

       Error returnedError = response.body().as(Error.class);

       assertThat(response.statusCode(),equalTo(400));
       assertThat(returnedError.getMessage(),is(equalTo( "Email is already exists in the Database")));
    }

    @Test
    public void shouldBeAbleToLogin(){
        login2 body3 = new login2("mohamed123456@gmail.com","1234567");

       Response response = UserApi.login(body3);

       login2 returnedLogin2 = response.body().as(login2.class);

       assertThat(response.statusCode(),equalTo(200));
       assertThat(returnedLogin2.getFirstName(),equalTo("mohamed"));
       assertThat(returnedLogin2.getAccessToken(),not(equalTo(null)));
    }
    @Test
    public void shouldBeNotAbleToLogin(){
        login2 body1 = new login2("mohamed123455@gmail.com","12345678");

       Response response = given()
                .spec(request)
                .contentType(ContentType.JSON)
                .body(body1)
                .when().post("/api/v1/users/login")
                .then().log().all().extract().response();

       Error returnedError = response.body().as(Error.class);

       assertThat(response.statusCode(),is(equalTo(401)));
       assertThat(returnedError.getMessage(),equalTo("The email and password combination is not correct, please fill a correct email and password"));
    }
    @Test
    public void registerLearn(){
        login2 body = new login2("hadeer","Mohamed","hadeerMohamed@gmail.com","98765");

       Response response = UserApi.learn( body);

       login2 returnedLogin2 = response.body().as(login2.class);

       assertThat(response.statusCode(),equalTo(201));
       assertThat(returnedLogin2.getFirstName(),equalTo(body.getFirstName()));
}
    @Test
    public void registerLearn2(){
        login2 body = UserSteps.generateUser();

        Response response = UserApi.learn( body);

        login2 returnedLogin2 = response.body().as(login2.class);

        assertThat(response.statusCode(),equalTo(201));
        assertThat(returnedLogin2.getFirstName(),equalTo(body.getFirstName()));
    }
    @Test
    public void registerLearn3(){
        //invalid data using the same email
        login2 body = UserSteps.getRegisterUser();

        Response response = UserApi.learn( body);


        Error returnedError = response.body().as(Error.class);

        assertThat(response.statusCode(),equalTo(400));
        assertThat(returnedError.getMessage(),equalTo("Email is already exists in the Database"));
    }
    @Test
    public void loginLearn(){

        login2 body = UserSteps.getRegisterUser();
        login2 loginData = new login2(body.getEmail(),body.getPassword());

        Response response = UserApi.login( loginData);


        login2 returnedLogin = response.body().as(login2.class);

        assertThat(response.statusCode(),equalTo(200));
        assertThat(returnedLogin.getFirstName(),equalTo(body.getFirstName()));
    }
    @Test
    public void loginLearn2(){
        //invalid data using the different password
        login2 body = UserSteps.getRegisterUser();
        login2 loginData = new login2(body.getEmail(),"12345");

        Response response = UserApi.login( loginData);


        Error returnedError = response.body().as(Error.class);

        assertThat(response.statusCode(),equalTo(401));
        assertThat(returnedError.getMessage(),equalTo("The email and password combination is not correct, please fill a correct email and password"));
    }


}
