package obj;

import model.UserValidationModel;

/**
 *
 * @author marco
 */
public class UserValidation {
    private int id;
    private int idPhoneInfo;
    private int number;
    private boolean validated;
    private PhoneInfo phoneInfo;

    public UserValidation(int id, int idPhoneInfo, int number, boolean validated, PhoneInfo phoneInfo) {
        this.id = id;
        this.idPhoneInfo = idPhoneInfo;
        this.number = number;
        this.validated = validated;
        this.phoneInfo = phoneInfo;
    }

    public UserValidation() {
    }
        
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPhoneInfo() {
        return idPhoneInfo;
    }

    public void setIdPhoneInfo(int idPhoneInfo) {
        this.idPhoneInfo = idPhoneInfo;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }    

    public PhoneInfo getPhoneInfo() {
        return phoneInfo;
    }

    public void setPhoneInfo(PhoneInfo phoneInfo) {
        this.phoneInfo = phoneInfo;
    }

     public UserValidationModel toModel(){
        return new UserValidationModel(
                this.id,
                this.idPhoneInfo,
                this.number,
                this.validated,
                phoneInfo.toModel()
        );
    }
}
