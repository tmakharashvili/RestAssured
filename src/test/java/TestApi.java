import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class TestApi {
    @Test
    void test() {
        given().
                header("content-type", "application-json").
                get("https://reqres.in/api/users?page=2").
                then().
                statusCode(200).
                header("Content-Type", containsString("application/json"));

        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        System.out.println("size of data massive is " + response.jsonPath().getList("data").size());

        List<LinkedHashMap<String, String>> emailList = response.jsonPath().getList("data");
        for (int i = 0; i < emailList.size(); i++) {
            Assert.assertTrue(emailList.get(i).get("email").contains("@"), "test allert");
            System.out.println(emailList.get(i).get("email"));
        }

    }
}
