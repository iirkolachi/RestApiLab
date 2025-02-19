import Models.SmsModule.GetConsent.GetSmsRequestModel;
import Models.SmsModule.GetConsent.GetSmsResponseModel;
import Models.SmsModule.PostConsent.PostSmsRequestModel;
import Steps.SMSModule.ConsentSteps;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSmsModule extends ConfigTest{

    ConsentSteps consentSteps = new ConsentSteps();

    @Test (dataProvider = "postSmsRequestModels", priority = 1)
    public void postConsent(PostSmsRequestModel postSmsRequestModel) {
        consentSteps.PostConsent(postSmsRequestModel);
        GetSmsRequestModel getSmsRequestModel = new GetSmsRequestModel();
        getSmsRequestModel.setTelNumber(postSmsRequestModel.getTelNumber());
        GetSmsResponseModel getSmsResponseModel = consentSteps.GetConsent(getSmsRequestModel);
        consentSteps.CompareConsent(getSmsResponseModel, postSmsRequestModel); //aq tel.number tu consent unda shemowmdes?
        System.out.println("warmatebulia");
    }
}