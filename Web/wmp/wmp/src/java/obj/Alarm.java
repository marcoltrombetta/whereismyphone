package obj;

/**
 *
 * @author marco
 */
public class Alarm {
    private int id;
    private int idPhoneInfo;
    private int idLocationAlarm;
    private int idAlarmAccion;
    private int schedulePeriod;

    public Alarm() {
    }

    public Alarm(int id, int idPhoneInfo, int idLocationAlarm, int idAlarmAccion, int schedulePeriod) {
        this.id = id;
        this.idPhoneInfo = idPhoneInfo;
        this.idLocationAlarm = idLocationAlarm;
        this.idAlarmAccion = idAlarmAccion;
        this.schedulePeriod = schedulePeriod;
    }

    public int getId() {
        return id;
    }

    public int getIdPhoneInfo() {
        return idPhoneInfo;
    }

    public int getIdLocationAlarm() {
        return idLocationAlarm;
    }

    public int getIdAlarmAccion() {
        return idAlarmAccion;
    }

    public int getSchedulePeriod() {
        return schedulePeriod;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdPhoneInfo(int idPhoneInfo) {
        this.idPhoneInfo = idPhoneInfo;
    }

    public void setIdLocationAlarm(int idLocationAlarm) {
        this.idLocationAlarm = idLocationAlarm;
    }

    public void setIdAlarmAccion(int idAlarmAccion) {
        this.idAlarmAccion = idAlarmAccion;
    }

    public void setSchedulePeriod(int schedulePeriod) {
        this.schedulePeriod = schedulePeriod;
    }
    
}
