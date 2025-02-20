package DataController;

import DBAccessSQL.DBAccessSMSModule;
import Models.SmsModule.GetConsent.GetSmsRequestModel;
import Models.SmsModule.PostConsent.PostSmsRequestModel;
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
    public static String queryPostNumber = """
            IF OBJECT_ID('tempdb..#tmp') IS NOT NULL
            DROP TABLE #tmp
            
            SELECT
            	*,
            	ROW_NUMBER() OVER (ORDER BY a.PersonId ASC) AS rowNum
            INTO #tmp
            FROM (
            SELECT
            	TOP 3 a.PersonId,
            	CAST (a.TelNumber AS VARCHAR) AS TelNumber,
            	IIF(a.Consent = 1, 3, 1) AS Consent,
            	a.Channel
            FROM [SMSModuleDB].[dbo].[AdSMSConsent] (NOLOCK) AS a
            WHERE a.PersonId IS NOT NULL
            	AND a.TelNumber IS NOT NULL
            	AND a.Channel IS NOT NULL
            UNION
            SELECT
            	TOP 1 a.PersonId,
            	a.Contact,
            	'1' AS Consent,
            	'106102' AS Channel
            FROM CredoBnk.person.Contact (NOLOCK) AS a
            LEFT JOIN [SMSModuleDB].[dbo].[AdSMSConsent] (NOLOCK) AS b on a.PersonId = b.PersonId
            LEFT JOIN [SMSModuleDB].[dbo].[AdSMSConsent] (NOLOCK) AS c on a.Contact = b.TelNumber
            WHERE b.PersonId IS NULL
            	AND c.PersonId IS NULL
            	) AS a
            
            UPDATE a
            SET a.PersonId = NULL
            FROM #tmp AS a
            WHERE a.rowNum = 1
            
            UPDATE a
            SET a.TelNumber = NULL
            FROM #tmp AS a
            WHERE a.rowNum = 2
            
            UPDATE a
            SET a.Channel = NULL
            FROM #tmp AS a
            WHERE a.rowNum = 3
            
            SELECT
            	*
            FROM #tmp AS a
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
        Object[][] data = new Object[getSmsRequestModels.size()][1];
        for (int i = 0; i < getSmsRequestModels.size(); i++) {
            data[i][0] = getSmsRequestModels.get(i);
        }
        return data;
    }


    public static List<PostSmsRequestModel> postSmsRequestModels (String query) throws SQLException {
        List<PostSmsRequestModel> postSmsRequestModels = new ArrayList<>();
        Connection dataBaseAccess = DBAccessSMSModule.getSMSModule();
        PreparedStatement preparedStatement =  dataBaseAccess.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            PostSmsRequestModel postSmsRequestModel = new PostSmsRequestModel();
            postSmsRequestModel.setPersonId(resultSet.getString("PersonId"));
            postSmsRequestModel.setTelNumber(resultSet.getString("TelNumber"));
            postSmsRequestModel.setStatus(resultSet.getString("Consent"));
            postSmsRequestModel.setChannelId(resultSet.getString("Channel"));
            postSmsRequestModels.add(postSmsRequestModel);
        }

        return postSmsRequestModels;
    }

    public static Object[][] postSmsRequestModelsObjects (List<PostSmsRequestModel> postSmsRequestModels) {
        Object[][] data = new Object[postSmsRequestModels.size()][1];
        for (int i = 0; i < postSmsRequestModels.size(); i++) {
            data[i][0] = postSmsRequestModels.get(i);
        }
        return data;
    }
}
