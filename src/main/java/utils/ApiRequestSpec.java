package utils;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class ApiRequestSpec {
    private static String BASE_URL = "http://10.195.105.66:7000";

    public static RequestSpecification getRequestSpec(){
        return given()
                .baseUri(BASE_URL)
                .header("content-type", "application/json");
    }
    public static ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType("application/json")
                .build();
    }
    public static RequestSpecification postRequestSpec() {
        return given()
                .baseUri(BASE_URL)
                .header("content-type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }

    public static ResponseSpecification postResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }
}
