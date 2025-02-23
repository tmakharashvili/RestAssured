import dataController.DataControllerSMSModule;
import models.SMSModule.getConsent.GetSMSRequestModel;
import models.SMSModule.postConsent.PostSMSRequestModel;
import org.testng.annotations.DataProvider;

import java.sql.SQLException;
import java.util.List;

public class ConfigTest {
    @DataProvider(name = "getSMSRequestModelsIndividuals")
    public Object[][] getSMSRequestModelsIndividuals() throws SQLException {
        List<GetSMSRequestModel> getSMSRequestModels = DataControllerSMSModule.getSMSRequestModels(DataControllerSMSModule.queryGetNumber);
        Object[][] data = new Object[getSMSRequestModels.size()][1];
        for (int i = 0; i < getSMSRequestModels.size(); i++) {
            data[i][0] = getSMSRequestModels.get(i);
        }
        return data;
    }

    @DataProvider(name = "postSMSRequestModelsIndividuals")
    public Object[][] postSMSRequestModelsIndividualObjects() throws SQLException {
        List<PostSMSRequestModel> getSMSRequestModels = DataControllerSMSModule.postSMSRequestModels(DataControllerSMSModule.queryPostNumber);
        Object[][] data = new Object[getSMSRequestModels.size()][1];
        for (int i = 0; i < getSMSRequestModels.size(); i++) {
            data[i][0] = getSMSRequestModels.get(i);
        }
        return data;
    }
}