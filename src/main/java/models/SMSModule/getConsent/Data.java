package models.SMSModule.getConsent;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)

public class Data {
    public int consentStatusId;
    public String consentStatus;
    public String dateUpdated;
    public Boolean success;
}
