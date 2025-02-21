package steps.SMSModule;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.SMSModule.postConsent.PostSMSRequestModel;

import static io.restassured.RestAssured.given;

public class ConsentCalls {
    public Response getSMSRequests(String telNum) {
        return  given().
                header("content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                when().
                get("http://10.195.105.66:7000/api/Consent?TelNumber=" + telNum);

    }
    public Response postSMSRequests(PostSMSRequestModel postSMSRequestModel) {
        return given().
                header("content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                when().
                body(postSMSRequestModel).
                post("http://10.195.105.66:7000/api/Consent");
    }
}
