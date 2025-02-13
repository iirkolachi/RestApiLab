package Calls.CallsSMSModule;

import Models.SmsModule.GetSmsRequestModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class SMSRequestCalls {
    public Response getSmsRequest() {
        GetSmsRequestModel getSmsRequestModel = null;
        Response response = given()
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)

                .when()
                .get("http://10.195.105.66:7000/api/Consent?TelNumber=" + getSmsRequestModel.getTelNumber());

        int consentStatusId = response.jsonPath().getInt("data.consentStatusId");

        return response;
    }

    public Response getSmsRequestIndividual(String telNumber) {
        Response response = given()
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("http://10.195.105.66:7000/api/Consent?TelNumber=" + telNumber);

        int consentStatusId = response.jsonPath().getInt("data.consentStatusId");

        return response;
    }
}
