package Calls.CallsSMSModule;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class SMSRequestCalls {
    public Response getSmsRequest(String telNumber) {
        Response response = given()
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)

                .when()
                .get("http://10.195.105.66:7000/api/Consent?TelNumber=" + telNumber);

        return response;
    }

    public Response getSmsRequestIndividual(String telNumber) {
        Response response = given()
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("http://10.195.105.66:7000/api/Consent?TelNumber=" + telNumber);

        return response;
    }
}
