package Models.SmsModule.GetConsent;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetSmsResponseModel {
    public Data data;
    public Object message;
    public Object detailsMessage;
    public int externalState;
    public int state;
    public Object errorCode;
    public Object validationErrors;
}
