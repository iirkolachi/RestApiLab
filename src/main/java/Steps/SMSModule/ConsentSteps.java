package Steps.SMSModule;

import Models.SmsModule.GetConsent.GetSmsRequestModel;
import Models.SmsModule.GetConsent.GetSmsResponseModel;
import Models.SmsModule.PostConsent.PostSmsRequestModel;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;

public class ConsentSteps {

    @Step
    public GetSmsResponseModel GetConsent (GetSmsRequestModel getSmsRequestModel){
        ConsentCalls consentCalls = new ConsentCalls();
        GetSmsResponseModel getSmsResponseModel;
        Response response = consentCalls.GetConsent(getSmsRequestModel);
        getSmsResponseModel = response.as(GetSmsResponseModel.class);

        return getSmsResponseModel;
    }

   @Step
    public void PostConsent (PostSmsRequestModel postSmsRequestModel){
        ConsentCalls consentCalls = new ConsentCalls();
        Response response = consentCalls.PostConsent(postSmsRequestModel);
    }

  @Step
    public void CompareConsent(GetSmsResponseModel getSmsResponseModel, PostSmsRequestModel postSmsRequestModel) {
        Assert.assertEquals(getSmsResponseModel.getData().getConsentStatusId(), postSmsRequestModel.getStatus());
    }


    //fluent interface

    public GetSmsResponseModel getSmsResponseModel = new GetSmsResponseModel();

    @Step
    public ConsentSteps GetConsentFluent (GetSmsRequestModel getSmsRequestModel){
        ConsentCalls consentCalls = new ConsentCalls();
        Response response = consentCalls.GetConsent(getSmsRequestModel);
        getSmsResponseModel = response.as(GetSmsResponseModel.class);

        return this;
    }

    @Step
    public ConsentSteps PostConsentFluent (PostSmsRequestModel postSmsRequestModel){
        ConsentCalls consentCalls = new ConsentCalls();
        Response response = consentCalls.PostConsent(postSmsRequestModel);

        return this;
    }

    @Step
    public void CompareConsentFluent (GetSmsResponseModel getSmsResponseModel, PostSmsRequestModel postSmsRequestModel) {
        Assert.assertEquals(getSmsResponseModel.getData().getConsentStatusId(), postSmsRequestModel.getStatus());
    }
}
