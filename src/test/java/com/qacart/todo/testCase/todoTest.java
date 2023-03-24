package com.qacart.todo.testCase;

import com.qacart.todo.testCase.Steps.TodoSteps;
import com.qacart.todo.testCase.Steps.UserSteps;
import com.qacart.todo.testCase.api.TodoApi;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

public class todoTest {

    RequestSpecification request = given()
            .baseUri("https://qacart-todo.herokuapp.com")
            .log().all();

    @Test
    public void shouldBeAbleToAddTodo(){
        HashMap<String,String> body = new HashMap<>();
        body.put("isCompleted","true");
        body.put("item","learn selenium");

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYzZTI5NjNlMmYxNDA1MDAxNGYyMWE0NSIsImZpcnN0TmFtZSI6Inlhc21pbmUiLCJsYXN0TmFtZSI6IkFkZWwiLCJpYXQiOjE2NzU3OTQwMTl9.gmX2IGMUg2U63vtEbDoegK3hE7Hd7Mzutx7dl41Q-TQ";

        given()
                .spec(request)
                .contentType(ContentType.JSON)
                .body(body)
                .auth().oauth2(token)
                .when().post("/api/v1/tasks")
                .then().log().all()
                .assertThat().statusCode(201)
                .assertThat().body("item",equalTo("learn selenium"));
    }


    @Test
    public void shouldBeAbleToAddTodo2(){
        HashMap<String,String> body = new HashMap<>();
        body.put("isCompleted","false");
        body.put("item","learn manualTest");

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYzZTQxZWJmM2IyMzNkMDAxNDBjOWE4OCIsImZpcnN0TmFtZSI6Ikh1ZGEiLCJsYXN0TmFtZSI6ImhlbG15IiwiaWF0IjoxNjc1ODk0NDYzfQ.gCHpe31cy_kudzbnQ9cg8TT4lENHfQk9b8KeHLAsp3c";

        given()
                .spec(request)
                .contentType(ContentType.JSON)
                .body(body)
                .auth().oauth2(token)
                .when().post("/api/v1/tasks")
                .then().log().all()
                .assertThat().statusCode(201)
                .assertThat().body("item",equalTo("learn manualTest"));

    }
    @Test
    public void shouldBeAbleToAddTodo3(){
        HashMap<String,String> body = new HashMap<>();
        body.put("isCompleted","true");
        body.put("item","learn selenium");

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYzZTQxZWJmM2IyMzNkMDAxNDBjOWE4OCIsImZpcnN0TmFtZSI6Ikh1ZGEiLCJsYXN0TmFtZSI6ImhlbG15IiwiaWF0IjoxNjc1ODk0NDYzfQ.gCHpe31cy_kudzbnQ9cg8TT4lENHfQk9b8KeHLAsp3c";

        given()
                .spec(request)
                .contentType(ContentType.JSON)
                .body(body)
                .auth().oauth2(token)
                .when().post("/api/v1/tasks")
                .then().log().all()
                .assertThat().statusCode(201)
                .assertThat().body("item",equalTo("learn selenium"));
    }
    @Test
    public void shouldBeAbleToAddTodo4(){
        Todo todo = new Todo(false,"learn Mobile");

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYzZTk5YmU5YmFmN2ZhMDAxNDlmYmNmNSIsImZpcnN0TmFtZSI6Inlhc21pbmUiLCJsYXN0TmFtZSI6IkFkZWwiLCJpYXQiOjE2NzYyNTQxODZ9.8oZ-qSUPr5IGDkEGRpr7ApHByhdG7uQqOfG6SaayxT0";
        ;

       Response response = TodoApi.addTodo(token,todo);

       Todo returnedTodo = response.body().as(Todo.class);

       assertThat(response.statusCode(),equalTo(201));
       assertThat(returnedTodo.getItem(),equalTo(todo.getItem()));


    }

    @Test
    public void shouldBeNotAbleToAddTodo(){
        HashMap<String,String> body = new HashMap<>();
        body.put("isCompleted","true");

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYzZTI5NjNlMmYxNDA1MDAxNGYyMWE0NSIsImZpcnN0TmFtZSI6Inlhc21pbmUiLCJsYXN0TmFtZSI6IkFkZWwiLCJpYXQiOjE2NzU3OTQwMTl9.gmX2IGMUg2U63vtEbDoegK3hE7Hd7Mzutx7dl41Q-TQ";

        given()
                .spec(request)
                .contentType(ContentType.JSON)
                .body(body)
                .auth().oauth2(token)
                .when().post("/api/v1/tasks")
                .then().log().all()
                .assertThat().statusCode(400)
                .assertThat().body("message",equalTo("\"item\" is required"));

    }
    @Test
    public void shouldBeNotAbleToAddTodo2(){
        Todo todo = new Todo("learn performance");

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYzZTQxZWJmM2IyMzNkMDAxNDBjOWE4OCIsImZpcnN0TmFtZSI6Ikh1ZGEiLCJsYXN0TmFtZSI6ImhlbG15IiwiaWF0IjoxNjc1ODk0NDYzfQ.gCHpe31cy_kudzbnQ9cg8TT4lENHfQk9b8KeHLAsp3c";

        given()
                .spec(request)
                .contentType(ContentType.JSON)
                .body(todo)
                .auth().oauth2(token)
                .when().post("/api/v1/tasks")
                .then().log().all()
                .assertThat().statusCode(400)
                .assertThat().body("message",equalTo("\"isCompleted\" is required"));
    }
    @Test
    public void shouldBeNotAbleToAddTodo3(){
        Todo todo = new Todo("learn bugReport");

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYzZTQxZWJmM2IyMzNkMDAxNDBjOWE4OCIsImZpcnN0TmFtZSI6Ikh1ZGEiLCJsYXN0TmFtZSI6ImhlbG15IiwiaWF0IjoxNjc1ODk0NDYzfQ.gCHpe31cy_kudzbnQ9cg8TT4lENHfQk9b8KeHLAsp3c";

        given()
                .spec(request)
                .contentType(ContentType.JSON)
                .body(todo)
                .auth().oauth2(token)
                .when().post("/api/v1/tasks")
                .then().log().all()
                .assertThat().statusCode(400)
                .assertThat().body("message",equalTo( "\"isCompleted\" is required"));
    }
    @Test
    public void getTodo(){

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYzZTQxZWJmM2IyMzNkMDAxNDBjOWE4OCIsImZpcnN0TmFtZSI6Ikh1ZGEiLCJsYXN0TmFtZSI6ImhlbG15IiwiaWF0IjoxNjc1ODk0NDYzfQ.gCHpe31cy_kudzbnQ9cg8TT4lENHfQk9b8KeHLAsp3c";


        given()
                .spec(request)
                .auth().oauth2(token)
                .when().get("/api/v1/tasks")
                .then().log().all()
                .assertThat().statusCode(200);
    }
    @Test
    public void getTodo2(){

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYzZTI5NjNlMmYxNDA1MDAxNGYyMWE0NSIsImZpcnN0TmFtZSI6Inlhc21pbmUiLCJsYXN0TmFtZSI6IkFkZWwiLCJpYXQiOjE2NzU3OTQwMTl9.gmX2IGMUg2U63vtEbDoegK3hE7Hd7Mzutx7dl41Q-TQ";

        given()
                .spec(request)
                .auth().oauth2(token)
                .when().get("/api/v1/tasks/63e2afba2f14050014f21bd1")
                .then().log().all()
                .assertThat().statusCode(200);

    }
    @Test
    public void shouldBeAbleToUpdateTodo(){
        Todo todo1 = new Todo(false,"learn regressionTesting");

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYzZTQxZWJmM2IyMzNkMDAxNDBjOWE4OCIsImZpcnN0TmFtZSI6Ikh1ZGEiLCJsYXN0TmFtZSI6ImhlbG15IiwiaWF0IjoxNjc1ODk0NDYzfQ.gCHpe31cy_kudzbnQ9cg8TT4lENHfQk9b8KeHLAsp3c";

        given()
                .spec(request)
                .contentType(ContentType.JSON)
                .body(todo1)
                .auth().oauth2(token)
                .when().put("/api/v1/tasks/63e42d023b233d00140c9ab5")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("item",equalTo("learn regressionTesting"));
    }
    @Test
    public void shouldBeAbleToUpdateTodo2(){
        HashMap<String,String> body = new HashMap<>();
        body.put("isCompleted","true");
        body.put("item","learn Automation");

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYzZTI5NjNlMmYxNDA1MDAxNGYyMWE0NSIsImZpcnN0TmFtZSI6Inlhc21pbmUiLCJsYXN0TmFtZSI6IkFkZWwiLCJpYXQiOjE2NzU3OTQwMTl9.gmX2IGMUg2U63vtEbDoegK3hE7Hd7Mzutx7dl41Q-TQ";

        given()
                .spec(request)
                .contentType(ContentType.JSON)
                .body(body)
                .auth().oauth2(token)
                .when().put("/api/v1/tasks/63e2afba2f14050014f21bd1")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("item",equalTo("learn Automation"));
    }
    @Test
    public void getTodo3(){

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYzZTI5NjNlMmYxNDA1MDAxNGYyMWE0NSIsImZpcnN0TmFtZSI6Inlhc21pbmUiLCJsYXN0TmFtZSI6IkFkZWwiLCJpYXQiOjE2NzU3OTQwMTl9.gmX2IGMUg2U63vtEbDoegK3hE7Hd7Mzutx7dl41Q-TQ";

        given()
                .spec(request)
                .auth().oauth2(token)
                .when().get("/api/v1/tasks")
                .then().log().all()
                .assertThat().statusCode(200);
    }
    /*@Test
    public void deleteTodo(){
        String taskId ="63e42d023b233d00140c9ab5";
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYzZTQxZWJmM2IyMzNkMDAxNDBjOWE4OCIsImZpcnN0TmFtZSI6Ikh1ZGEiLCJsYXN0TmFtZSI6ImhlbG15IiwiaWF0IjoxNjc1ODk0NDYzfQ.gCHpe31cy_kudzbnQ9cg8TT4lENHfQk9b8KeHLAsp3c";

        given()
                .spec(request)
                .auth().oauth2(token)
                .when().delete("/api/v1/tasks"+taskId)
                .then().log().all()
                .assertThat().statusCode(200);
    }*/
    @Test
    public void deleteTodo2(){

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYzZTQxZWJmM2IyMzNkMDAxNDBjOWE4OCIsImZpcnN0TmFtZSI6Ikh1ZGEiLCJsYXN0TmFtZSI6ImhlbG15IiwiaWF0IjoxNjc1ODk0NDYzfQ.gCHpe31cy_kudzbnQ9cg8TT4lENHfQk9b8KeHLAsp3c";

        given()
                .spec(request)
                .auth().oauth2(token)
                .when().delete("/api/v1/tasks/63e42d023b233d00140c9ab5")
                .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test
    public void addTodo(){
        Todo body = TodoSteps.generateTodo();
        String token = UserSteps.getUserToken();

        Response response = TodoApi.addTodo(token, body);

        Todo returnedAddTodo = response.body().as(Todo.class);

        assertThat(response.statusCode(),equalTo(201));
        assertThat(returnedAddTodo.getItem(),equalTo(body.getItem()));
    }
    @Test
    public void notAddTodo(){
        Todo body = new Todo("learn bugReport");
        String token = UserSteps.getUserToken();

        Response response = TodoApi.addTodo(token, body);

        Error returnedError = response.body().as(Error.class);

        assertThat(response.statusCode(),equalTo(400));
        assertThat(returnedError.getMessage(),equalTo("\"isCompleted\" is required"));
    }
    @Test
    public void getTodo4(){

        String token = UserSteps.getUserToken();
        Todo todo = TodoSteps.generateTodo();
        String taskId = TodoSteps.getTodo(token,todo);
         Response response = TodoApi.GetTodo(token,taskId);
         Todo returned = response.body().as(Todo.class);
         assertThat(response.statusCode(),equalTo(200));
        assertThat(returned.getItem(),equalTo(todo.getItem()));


    }



}
