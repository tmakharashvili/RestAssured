package dataController;

import dataBaseAccessSQL.DataBaseAccessSQL;
import models.SMSModule.getConsent.GetSMSRequestModel;
import models.SMSModule.postConsent.PostSMSRequestModel;

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
    public static String queryPostNumber = """
            IF OBJECT_ID('tempdb..#tmp') IS NOT NULL
            DROP TABLE #tmp
            select *, ROW_NUMBER() over (order by a.PersonId asc ) as rowNum
            into #tmp
            from
            (
            SELECT top 3 a.PersonId , cast(a.TelNumber as varchar) as TelNumber , iif (a.Consent =1, 3, 1) as consent, a.Channel FROM [SMSModuleDB].[dbo].[AdSMSConsent] as a where a.PersonId is not null and a.TelNumber is not null
            union
            select top 1 a.PersonId , a.Contact , (select distinct top 1 a.Consent from [SMSModuleDB].[dbo].[AdSMSConsent] as a ) as Consent , '106102' as Channel from CredoBnk.person.Contact as a
            left join [SMSModuleDB].[dbo].[AdSMSConsent] as b on a.PersonId = b.PersonId
            left join [SMSModuleDB].[dbo].[AdSMSConsent] as c on a.Contact = c.TelNumber
            where b.PersonId is null
            and c.PersonId is null
            ) as a
            update a set a.PersonId = null from #tmp as a where a.rowNum = 1
            update a set a.TelNumber = null from #tmp as a where a.rowNum = 2
            update a set a.Channel = null from #tmp as a where a.rowNum = 3
            
            select * from #tmp as a
            """;

    public static List<GetSMSRequestModel> getSMSRequestModels(String query) throws SQLException {
        List<GetSMSRequestModel> getSMSRequestModels = new ArrayList<>();
        Connection dataBaseAccessSql = DataBaseAccessSQL.getConnectionSMS();
        ResultSet resultSet;
        PreparedStatement preparedStatement = dataBaseAccessSql.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            GetSMSRequestModel getSMSRequestModel = new GetSMSRequestModel();
            getSMSRequestModel.setPersonId(resultSet.getString("PersonId"));
            getSMSRequestModel.setTelNumber(resultSet.getString("TelNumber"));
            getSMSRequestModel.setConsent(resultSet.getString("Consent"));
            getSMSRequestModels.add(getSMSRequestModel);
        }
        return getSMSRequestModels;
    }

    public static Object[][] getSMSRequestModelsIndividualObjects(List<GetSMSRequestModel> getSMSRequestModels) {
        Object[][] data = new Object[getSMSRequestModels.size()][3];
        for (int i = 0; i < getSMSRequestModels.size(); i++) {
            data[i][0] = getSMSRequestModels.get(i).getTelNumber();
            data[i][1] = getSMSRequestModels.get(i).getPersonId();
            data[i][2] = getSMSRequestModels.get(i).getConsent();
        }
        return data;
    }

    public static List<PostSMSRequestModel> postSMSRequestModels(String query) throws SQLException {
        List<PostSMSRequestModel> postSMSRequestModels = new ArrayList<>();
        Connection dataBaseAccessSql = DataBaseAccessSQL.getConnectionSMS();
        ResultSet resultSet;
        PreparedStatement preparedStatement = dataBaseAccessSql.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            PostSMSRequestModel postSMSRequestModel = new PostSMSRequestModel();
            postSMSRequestModel.setPersonId(resultSet.getString("PersonId"));
            postSMSRequestModel.setTelNumber(resultSet.getString("TelNumber"));
            postSMSRequestModel.setStatus(resultSet.getString("consent"));
            postSMSRequestModel.setChannelId(resultSet.getString("Channel"));
            postSMSRequestModels.add(postSMSRequestModel);
        }
        return postSMSRequestModels;
    }

    public static Object[][] postSMSRequestModelsIndividualObjects(List<PostSMSRequestModel> postSMSRequestModels) {

        Object[][] data = new Object[postSMSRequestModels.size()][4];
        for (int i = 0; i < postSMSRequestModels.size(); i++) {
            data[i][0] = postSMSRequestModels.get(i).getPersonId();
            data[i][1] = postSMSRequestModels.get(i).getTelNumber();
            data[i][2] = postSMSRequestModels.get(i).getStatus();
            data[i][3] = postSMSRequestModels.get(i).getChannelId();
        }
        return data;
    }
}
