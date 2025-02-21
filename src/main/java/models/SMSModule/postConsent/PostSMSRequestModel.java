package models.SMSModule.postConsent;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostSMSRequestModel {
    private String personId;
    private String telNumber;
    private String status;
    private String channelId;
}
