package Models.SmsModule.PostConsent;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostSmsRequestModel {
    public String personId;
    public String telNumber;
    public String status;
    public String channelId;
}