package DataController;

import DBAccessSQL.DBAccessSMSModule;
import Models.SmsModule.GetSmsRequestModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataControllerSMSModule {
    public static String queryGetTelNumbers = """
            USE SMSModuleDB

            IF OBJECT_ID('tempdb..#temp') IS NOT NULL
                DROP TABLE #temp

            SELECT
            	a.lenTelNumber,
            	MAX(a.PersonId) AS PersonId
            INTO #temp
            FROM (
            SELECT DISTINCT
            	LEN(a.TelNumber) AS lenTelNumber,
            	a.PersonId
            FROM AdSMSConsent AS a (NOLOCK)
            WHERE a.PersonId IS NOT NULL) AS a
            GROUP BY a.lenTelNumber
            ORDER BY a.lenTelNumber ASC

            SELECT
            	a.*,
            	b.TelNumber,
            	b.Consent
            FROM #temp AS a
            INNER JOIN dbo.AdSMSConsent AS b (NOLOCK) ON a.PersonId = b.PersonId AND a.lenTelNumber = LEN(b.TelNumber)
            ORDER BY 1 ASC
            """;

            public static List<GetSmsRequestModel> getUserRequestModel (String query) throws SQLException {
                List<GetSmsRequestModel> getUserRequestModel = new ArrayList<>();
                Connection dataBaseAccess = DBAccessSMSModule.getSMSModule();
                PreparedStatement preparedStatement =  dataBaseAccess.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    GetSmsRequestModel getSmsRequestModel = new GetSmsRequestModel();
                    getSmsRequestModel.setPersonId(resultSet.getString("PersonId"));
                    getSmsRequestModel.setTelNumber(resultSet.getString("TelNumber"));
                    getSmsRequestModel.setConsent(Integer.parseInt(resultSet.getString("Consent")));
                    getUserRequestModel.add(getSmsRequestModel);
                }

                return getUserRequestModel;
            }

    public static Object[][] getDataObjects (List<GetSmsRequestModel> getSmsRequestModels) {
        //List<GetSmsRequestModel> getSmsRequestModels = DataControllerSMSModule.getUserRequestModel(DataControllerSMSModule.queryGetTelNumbers);
        Object[][] data = new Object[getSmsRequestModels.size()][1];
        for (int i = 0; i < getSmsRequestModels.size(); i++) {
            data[i][0] = getSmsRequestModels.get(i);
        }
        return data;
    }

    public static Object[][] getDataIndividual(List<GetSmsRequestModel> getSmsRequestModels) {
        //List<GetSmsRequestModel> getSmsRequestModels = DataControllerSMSModule.getUserRequestModel(DataControllerSMSModule.queryGetTelNumbers);
        Object[][] data = new Object[getSmsRequestModels.size()][3];
        for (int i = 0; i < getSmsRequestModels.size(); i++) {
            data[i][0] = getSmsRequestModels.get(i).getPersonId();
            data[i][1] = getSmsRequestModels.get(i).getTelNumber();
            data[i][2] = getSmsRequestModels.get(i).getConsent();
        }
        return data;
    }
}
