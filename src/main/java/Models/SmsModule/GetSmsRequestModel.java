package Models.SmsModule;

public class GetSmsRequestModel {
    public String personId;
    public String telNumber;
    public int Consent;

    //getter methods
    public String getPersonId() {
        return personId;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public int getConsent() {
        return Consent;
    }

    //setter methods
    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public void setConsent(int consent) {
        Consent = consent;
    }
}
