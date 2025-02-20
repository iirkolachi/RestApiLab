package Models.SmsModule.GetConsent;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetSmsRequestModel {
    public String personId;
    public String telNumber;
    public int Consent;

}
