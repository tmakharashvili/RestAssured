package steps.SMSModule;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.SMSModule.postConsent.PostSMSRequestModel;
import utils.ApiRequestSpec;

public class ConsentCalls {
    RequestSpecification requestSpecification = ApiRequestSpec.getRequestSpec();

    public Response GetConsent1(String telNum){
        Response response = requestSpecification
                .when()
                .get("/api/Consent?TelNumber=" + telNum);
        response.then().spec(ApiRequestSpec.getResponseSpec());
        return response;
    }

    public Response postConsent1(PostSMSRequestModel postSMSRequestModel) {
        Response response = requestSpecification
                .body(postSMSRequestModel)
                .when()
                .post("/api/Consent");
        response.then().spec(ApiRequestSpec.getResponseSpec());
        return response;
    }
}
