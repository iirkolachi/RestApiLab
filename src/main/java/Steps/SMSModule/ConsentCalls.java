package Steps.SMSModule;

import Models.SmsModule.GetConsent.GetSmsRequestModel;
import Models.SmsModule.PostConsent.PostSmsRequestModel;
import Utils.ApiRequestSpec;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class ConsentCalls {
    RequestSpecification requestSpecification = ApiRequestSpec.getRequestSpec();

    public Response GetConsent(GetSmsRequestModel getSmsRequestModel) {

        String url;
        if (getSmsRequestModel.getTelNumber() != null) {
            url = ApiRequestSpec.baseUrl + "/api/Consent?TelNumber=" + getSmsRequestModel.getTelNumber();
        } else {
            url = ApiRequestSpec.baseUrl + "/api/Consent?PersonId=" + getSmsRequestModel.getPersonId();
        }

        Response response = given()
                .when()
                .spec(requestSpecification)
                .when()
                .get(url);

        response.then().spec(ApiRequestSpec.getResponseSpec());
        return response;
    }

    public Response PostConsent(PostSmsRequestModel postSmsRequestModel) {
        Response response = given()
                .when()
                .spec(requestSpecification)
                .when()
                .body(postSmsRequestModel)
                .post("/api/Consent");

        response.then().spec(ApiRequestSpec.getResponseSpec());
        return response;
    }
}
