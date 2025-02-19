package Steps.SMSModule;

import Models.SmsModule.GetConsent.GetSmsRequestModel;
import Models.SmsModule.GetConsent.GetSmsResponseModel;
import Models.SmsModule.PostConsent.PostSmsRequestModel;
import Utils.TestListener;
//import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;

public class ConsentSteps {

   // @Step
    public GetSmsResponseModel GetConsent (GetSmsRequestModel getSmsRequestModel){
        ConsentCalls consentCalls = new ConsentCalls();
        GetSmsResponseModel getSmsResponseModel = new GetSmsResponseModel();
        Response response = consentCalls.GetConsent(getSmsRequestModel);
        int statusCode = response.getStatusCode();
        if(statusCode == 200) {
            getSmsResponseModel = response.as(GetSmsResponseModel.class);
            Assert.assertEquals(statusCode, 200);
        }
        else {
            Assert.assertNotEquals(statusCode, 200);
        }
        return getSmsResponseModel;
    }
   // @Step
    public void PostConsent (PostSmsRequestModel postSmsRequestModel){
        ConsentCalls consentCalls = new ConsentCalls();
        Response response = consentCalls.PostConsent(postSmsRequestModel);
        int statusCode = response.getStatusCode();
        if(statusCode == 200) {
            new TestListener().messageText("პოსტ მეთოდი წარმატებულია, postRequestModel = " + postSmsRequestModel);
            Assert.assertEquals(statusCode, 200);
        }
        else {
            new TestListener().messageText("პოსტ მეთოდი წარუმატებელია, response = " + response.asPrettyString());
            Assert.assertNotEquals(statusCode, 200);
        }
    }

  //  @Step
    public void CompareConsent(GetSmsResponseModel getSmsResponseModel, PostSmsRequestModel postSmsRequestModel) {
        Assert.assertEquals(getSmsResponseModel.getData().getConsentStatus(), postSmsRequestModel.getStatus());
    }
}
