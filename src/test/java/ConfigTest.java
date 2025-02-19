import DataController.DataControllerSMSModule;
import Models.SmsModule.PostConsent.PostSmsRequestModel;
import org.testng.annotations.DataProvider;

import java.sql.SQLException;
import java.util.List;

public class ConfigTest {


    @DataProvider(name = "postSmsRequestModels")
    public Object[][] postSmsRequestModels() throws SQLException {
        List<PostSmsRequestModel> postSmsRequestModels = DataControllerSMSModule.postSmsRequestModels(DataControllerSMSModule.queryPostNumber);
        Object[][] data = DataControllerSMSModule.postSmsRequestModelsObjects(postSmsRequestModels);
        return data;
    }
}
