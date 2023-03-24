package com.qacart.todo.testCase.Steps;

import com.github.javafaker.Faker;
import com.qacart.todo.testCase.Todo;
import com.qacart.todo.testCase.api.TodoApi;
import io.restassured.response.Response;

public class TodoSteps {
    public static Todo generateTodo(){
        Faker faker = new Faker();
        Boolean isCompleted = false;
        String item = faker.book().title();

        return  new Todo(isCompleted,item);
    }

    public static String getTodo(String token,Todo todo){

      Response response = TodoApi.addTodo(token,todo);
      return response.body().path("_id");
    }
}
