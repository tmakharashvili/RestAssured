package steps.SMSModule;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.SMSModule.getConsent.GetSMSRequestModel;
import models.SMSModule.getConsent.GetSMSResponseModel;
import models.SMSModule.postConsent.PostSMSRequestModel;
import org.testng.Assert;
import utils.TestListener;


public class ConsentSteps {
    @Step
    public GetSMSResponseModel GetConsent(GetSMSRequestModel getSMSRequestModel) {
        ConsentCalls consentCalls = new ConsentCalls();
        GetSMSResponseModel getSMSResponseModel = new GetSMSResponseModel();
        Response response = consentCalls.getSMSRequests (getSMSRequestModel.getTelNumber());

        int statusCode = response.statusCode();
        if (statusCode ==200){
            getSMSResponseModel = response.as(GetSMSResponseModel.class);
            Assert.assertEquals(statusCode, 200);
        } else {
            Assert.assertNotEquals(statusCode, 200);
        }
        return getSMSResponseModel;
    }
    @Step
    public void postConsent(PostSMSRequestModel postSMSRequestModel){
        ConsentCalls consentCalls = new ConsentCalls();

        Response response = consentCalls.postSMSRequests(postSMSRequestModel);

        int statusCode = response.statusCode();
        if (statusCode ==200){
            new TestListener().MessageText("პოსტ მეთოდი წარმატებით შესრულდა, postSMSRequestModel " + postSMSRequestModel);
            Assert.assertEquals(statusCode, 200);
        } else {
            new TestListener().MessageText("პოსტ მეთოდი წარუმატებელია, response " + response.asPrettyString());
            Assert.assertNotEquals(statusCode, 200);
        }
    }
    @Step
    public void compareConsent(GetSMSResponseModel getSMSResponseModel, PostSMSRequestModel postSMSRequestModel) {
        String actualStatus = String.valueOf(getSMSResponseModel.getData().getConsentStatusId());
        Assert.assertEquals(actualStatus, postSMSRequestModel.getStatus());
    }
}
