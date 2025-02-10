import DataController.DataControllerSMSModule;
import Models.SmsModule.GetSmsRequestModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.sql.SQLException;
import java.util.List;
import static io.restassured.RestAssured.given;

public class TestSmsModule {

    @Test
    public void testGet () throws SQLException {
        List<GetSmsRequestModel> getSmsRequestModels = DataControllerSMSModule.getUserRequestModel(DataControllerSMSModule.queryGetTelNumbers);

        Response response = null;
        for (int i = 0; i < getSmsRequestModels.size(); i++) {
            response = given()
                    .header("Content-type", "application/json")
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)

                    .when()
                    .get("http://10.195.105.66:7000/api/Consent?TelNumber=" + getSmsRequestModels.get(i).getTelNumber());

            int consentStatusId = response.jsonPath().getInt("data.consentStatusId");

            Assert.assertEquals(consentStatusId, getSmsRequestModels.get(i).getConsent());
        }
    }
}
