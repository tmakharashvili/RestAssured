package models.SMSModule.getConsent;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetSMSRequestModel {
    public String PersonId;
    public String TelNumber;
    public String Consent;

}
