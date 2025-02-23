import DataController.DataControllerSMSModule;
import Models.SmsModule.GetConsent.GetSmsRequestModel;
import Models.SmsModule.PostConsent.PostSmsRequestModel;
import org.testng.annotations.DataProvider;
import java.sql.SQLException;
import java.util.List;

public class ConfigTest {


    @DataProvider(name = "postSmsRequestModels")
    public Object[][] postSmsRequestModels() throws SQLException {
        List<PostSmsRequestModel> getSmsRequestModels = DataControllerSMSModule.postSmsRequestModels(DataControllerSMSModule.queryPostNumber);
        Object[][] data = DataControllerSMSModule.postSmsRequestModelsObjects(getSmsRequestModels);
        return data;
    }

    @DataProvider(name = "getSmsRequestModels")
    public Object[][] getSmsRequestModels() throws SQLException {
        List<GetSmsRequestModel> getSmsRequestModels = DataControllerSMSModule.getSmsRequestModels(DataControllerSMSModule.queryGetTelNumbers);
        Object[][] data = DataControllerSMSModule.getSmsRequestModelsObjects(getSmsRequestModels);
        return data;
    }
}
