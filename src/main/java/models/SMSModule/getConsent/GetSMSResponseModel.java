package models.SMSModule.getConsent;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)

public class GetSMSResponseModel {
    public Data data;
    public Object message;
    public Object detailsMessage;
    public int externalState;
    public int state;
    public Object errorCode;
    public Object validationErrors;

}
