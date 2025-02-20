package Steps.SMSModule;

import Models.SmsModule.GetConsent.GetSmsRequestModel;
import Models.SmsModule.PostConsent.PostSmsRequestModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class ConsentCalls {
    public Response GetConsent(GetSmsRequestModel getSmsRequestModel) {

        String url;
        if (getSmsRequestModel.getTelNumber() != null){
            url = "http://10.195.105.66:7000/api/Consent?TelNumber=" + getSmsRequestModel.getTelNumber();
        } else {
            url = "http://10.195.105.66:7000/api/Consent?PersonId=" + getSmsRequestModel.getPersonId();
        }

        return given()
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get(url);
    }

    public Response PostConsent(PostSmsRequestModel postSmsRequestModel) {
        return given()
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .body(postSmsRequestModel)
                .post("http://10.195.105.66:7000/api/Consent");
    }
}
