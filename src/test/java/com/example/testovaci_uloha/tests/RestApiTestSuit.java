package com.example.testovaci_uloha.tests;
import enums.User;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.MySqlUtil;
import utils.Utils;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class RestApiTestSuit {
    MySqlUtil mySqlUtil = new MySqlUtil();

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost:8087/api/v1";
        RestAssured.authentication = RestAssured.basic(User.REST.getName(), User.REST.getPassword());
        RestAssured.filters(new RequestLoggingFilter(LogDetail.ALL), new ResponseLoggingFilter(LogDetail.ALL));

    }
    @Test
    public void getAllUsersShouldReturnSuccess() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("$", hasSize(greaterThan(0)))
                .body("id", everyItem(isA(Integer.class)))
                .body("username", everyItem(isA(String.class)))
                .body("firstname", everyItem(isA(String.class)))
                .body("lastname", everyItem(isA(String.class)))
                .body("balance", everyItem(isA(Integer.class)));
    }

    @Test
    public void getUserByIdShouldReturnSuccess() {
        // Get user ID from database
        int userId = mySqlUtil.gerRandomUserId();
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", userId)
                .when()
                .get("/users/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(userId));
    }

    @Test
    public void createUserThenDeleteShouldReturnSuccess() {
        RestAssured.baseURI = "http://localhost:8087/api/v1";


        JSONObject requestParams = new JSONObject();
        requestParams.put("username", Utils.generateRandomLetters(10));
        requestParams.put("password", "heslo");
        requestParams.put("firstname", "ToBeDeleted");
        requestParams.put("lastname", "ToBeDeleted");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestParams.toString())
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract().response();

        int userId = response.jsonPath().getInt("id");

        given()
                .when()
                .delete("/users/{id}", userId)
                .then()
                .statusCode(204);
    }

    @Test
    public void updateRecordShouldBeSuccessful() {
        int userId = mySqlUtil.gerRandomUserId();

        // Store original value
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/users/" + userId)
                .then()
                .statusCode(200)
                .extract().response();

        String originalLastName =  response.jsonPath().getString("lastname");

        JSONObject originalBody = new JSONObject();
        originalBody.put("username", response.jsonPath().getString("username"));
        originalBody.put("firstname", response.jsonPath().getString("firstname"));
        // Update originalBody with new record
        originalBody.put("lastname", "Pepa");

        given()
                .contentType(ContentType.JSON)
                .body(originalBody.toString())
                .when()
                .put("/users/" + userId)
                .then()
                .statusCode(204);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/users/" + userId)
                .then()
                .statusCode(200)
                .body("lastname", equalTo("Pepa"));

        // Restore original name
        originalBody.put("lastname", originalLastName);
        given()
                .contentType(ContentType.JSON)
                .body(originalBody.toString())
                .when()
                .put("/users/" + userId)
                .then()
                .statusCode(204);
    }

}



