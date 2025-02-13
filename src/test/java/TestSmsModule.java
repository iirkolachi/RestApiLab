import DataController.DataControllerSMSModule;
import Models.SmsModule.GetSmsRequestModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import java.util.List;
import static io.restassured.RestAssured.given;

public class TestSmsModule {

    @DataProvider(name = "getData")
    public Object[][] getData() throws SQLException {
        List<GetSmsRequestModel> getSmsRequestModels = DataControllerSMSModule.getUserRequestModel(DataControllerSMSModule.queryGetTelNumbers);
        Object[][] data = DataControllerSMSModule.getDataObjects(getSmsRequestModels);
        return data;
    }

    @Test(dataProvider = "getData", priority = 1)
    public void testSMSRequestModel(GetSmsRequestModel getSmsRequestModel) {
        Response response = given()
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)

                .when()
                .get("http://10.195.105.66:7000/api/Consent?TelNumber=" + getSmsRequestModel.getTelNumber());

        int consentStatusId = response.jsonPath().getInt("data.consentStatusId");

        Assert.assertEquals(consentStatusId, getSmsRequestModel.getConsent());
    }

    @DataProvider (name = "getDataIndividual")
    public Object[][] getDataIndividual() throws SQLException {
        List<GetSmsRequestModel> getSmsRequestModels = DataControllerSMSModule.getUserRequestModel(DataControllerSMSModule.queryGetTelNumbers);
        Object[][] data = DataControllerSMSModule.getDataIndividual(getSmsRequestModels);
        return data;
    }

    @Test (dataProvider = "getDataIndividual", priority = 2)
    public void testSMSRequestModelIndividuals(String personId, String telNumber, int consent) {
        Response response = given()
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("http://10.195.105.66:7000/api/Consent?TelNumber=" + telNumber);

        int consentStatusId = response.jsonPath().getInt("data.consentStatusId");

        Assert.assertEquals(consentStatusId, consent);
    }
}