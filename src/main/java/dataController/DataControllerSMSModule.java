package dataController;

import dataBaseAccessSQL.DataBaseAccessSQL;
import models.SMSModule.GetSMSRequestModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataControllerSMSModule {
    public static String queryGetNumber = """
            use SMSModuleDB
            IF OBJECT_ID('tempdb..#tmp') IS NOT NULL
                DROP TABLE #tmp
            select a.TelLength, max(a.PersonId) as PersonId
            into #tmp
            from(
            select distinct
            	len(a.TelNumber) as TelLength,
            	a.PersonId
            from dbo.AdSMSConsent as a
            where a.PersonId is not null
            ) as a
            group by a.TelLength
            select a.*, b.TelNumber, b.Consent
            from #tmp as a
            inner join dbo.AdSMSConsent as b on a.PersonId = b.PersonId and a.TelLength = len(b.TelNumber)
            order by 1asc
            """;
    public static List<GetSMSRequestModel>getSMSRequestModels(String query) throws SQLException {
        List<GetSMSRequestModel>getSMSRequestModels = new ArrayList<>();
        Connection dataBaseAccessSql = DataBaseAccessSQL.getConnectionSMS();
        ResultSet resultSet;
        PreparedStatement preparedStatement = dataBaseAccessSql.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            GetSMSRequestModel getSMSRequestModel = new GetSMSRequestModel();
            getSMSRequestModel.setPersonId(resultSet.getString("PersonId"));
            getSMSRequestModel.setTelNumber(resultSet.getString("TelNumber"));
            getSMSRequestModel.setConsent(resultSet.getString("Consent"));
            getSMSRequestModels.add(getSMSRequestModel);
        }
        return getSMSRequestModels;
    }
    public static Object[][] getSMSRequestModelsIndividualObjects(List<GetSMSRequestModel>getSMSRequestModels) throws SQLException {
        Object[][] data = new Object[getSMSRequestModels.size()][3];
        for (int i = 0; i < getSMSRequestModels.size(); i++) {
            data[i][0] = getSMSRequestModels.get(i).getTelNumber();
            data[i][1] = getSMSRequestModels.get(i).getPersonId();
            data[i][2] = getSMSRequestModels.get(i).getConsent();
        }
        return data;
    }
}
