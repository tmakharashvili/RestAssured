package calls.SMSrequests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class SMSGetRequestCalls {
    public Response getSMSRequests(String TelNum) throws SQLException {
        Response response = given().
                header("content-type", "application-json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                when().
                get("http://10.195.105.66:7000/api/Consent?TelNumber=" + TelNum);
        return response;
    }
}
