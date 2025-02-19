package Steps.SMSModule;

import Models.SmsModule.GetConsent.GetSmsRequestModel;
import Models.SmsModule.PostConsent.PostSmsRequestModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class ConsentCalls {
    public Response GetConsent(GetSmsRequestModel getSmsRequestModel) {
        return given()
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)

                .when()
                .get("http://10.195.105.66:7000/api/Consent?TelNumber=" + getSmsRequestModel.getTelNumber());
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
