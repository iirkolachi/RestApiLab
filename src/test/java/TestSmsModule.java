import Models.SmsModule.GetConsent.GetSmsRequestModel;
import Models.SmsModule.GetConsent.GetSmsResponseModel;
import Models.SmsModule.PostConsent.PostSmsRequestModel;
import Steps.SMSModule.ConsentSteps;
import org.testng.annotations.Test;

public class TestSmsModule extends ConfigTest{

    ConsentSteps consentSteps = new ConsentSteps();

    @Test (dataProvider = "postSmsRequestModels")
    public void postConsent(PostSmsRequestModel postSmsRequestModel) {
        consentSteps.PostConsent(postSmsRequestModel);
        GetSmsRequestModel getSmsRequestModel = new GetSmsRequestModel();
        getSmsRequestModel.setPersonId(postSmsRequestModel.getPersonId());
        getSmsRequestModel.setTelNumber(postSmsRequestModel.getTelNumber());
        GetSmsResponseModel getSmsResponseModel = consentSteps.GetConsent(getSmsRequestModel);
        consentSteps.CompareConsent(getSmsResponseModel, postSmsRequestModel);
    }

    @Test (dataProvider = "getSmsRequestModels")
    public void getConsent(GetSmsRequestModel getSmsRequestModel) {
        consentSteps.GetConsentFluent(getSmsRequestModel);
    }


    //fluent interface

    @Test (dataProvider = "postSmsRequestModels")
    public void postConsentFluent(PostSmsRequestModel postSmsRequestModel) {
        GetSmsRequestModel getSmsRequestModel = new GetSmsRequestModel();
        getSmsRequestModel.setTelNumber(postSmsRequestModel.getTelNumber());
        getSmsRequestModel.setPersonId(postSmsRequestModel.getPersonId());

        consentSteps.PostConsentFluent(postSmsRequestModel)
                .GetConsentFluent(getSmsRequestModel)
                .CompareConsentFluent(consentSteps.getSmsResponseModel, postSmsRequestModel);
    }

    @Test (dataProvider = "getSmsRequestModels")
    public void getConsentFluent(GetSmsRequestModel getSmsRequestModel) {
        consentSteps.GetConsentFluent(getSmsRequestModel);
    }
}