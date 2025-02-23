package steps.SMSModule;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.SMSModule.getConsent.GetSMSRequestModel;
import models.SMSModule.getConsent.GetSMSResponseModel;
import models.SMSModule.postConsent.PostSMSRequestModel;
import org.testng.Assert;

public class ConsentSteps {
    public PostSMSRequestModel postSMSRequestModel;
    public GetSMSResponseModel getSMSResponseModel;

    @Step
    public ConsentSteps getConsentFluent(GetSMSRequestModel getSMSRequestModel) {
        ConsentCalls consentCalls = new ConsentCalls();
        Response response = consentCalls.GetConsent1(getSMSRequestModel.getTelNumber());
        getSMSResponseModel = response.as(GetSMSResponseModel.class);
        return this;
    }

    @Step
    public ConsentSteps postConsentFluent(PostSMSRequestModel postSMSRequestModel) {
        ConsentCalls consentCalls = new ConsentCalls();
        Response response = consentCalls.postConsent1(postSMSRequestModel);
        getSMSResponseModel = response.as(GetSMSResponseModel.class);
        return this;
    }

    @Step
    public void compareConsentFluent(GetSMSResponseModel getSMSResponseModel, PostSMSRequestModel postSMSRequestModel) {
        String actualStatus = String.valueOf(getSMSResponseModel.getData().getConsentStatusId());
        Assert.assertEquals(actualStatus, postSMSRequestModel.getStatus());
    }
    @Step
    public void compareGetConsent(GetSMSResponseModel getSMSResponseModel, String expectedConsent) {
        String actualStatus = String.valueOf(getSMSResponseModel.getData().getConsentStatusId());
        Assert.assertEquals(actualStatus, expectedConsent);
    }
}
