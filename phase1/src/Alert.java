
//package ???
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Alert implements java.io.Serializable {
    private Event event;
    private LocalDateTime startTime;
    private Frequency frequency;
    private  int num;
    private Unit unit;
    public enum Unit {
        MINUTE,
        HOUR,
        DAY,
        WEEK,
        MONTH,
        YEAR
    }


    //Constructor
    //setter and getter
    //toString

    public Alert(Event event, String startTime, int num, Unit unit ){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime t = LocalDateTime.parse(startTime, formatter);
        this.event = event;
        this.startTime = t;
        this.num = num;
        this.unit = unit;
    }

    public Event getEvent() {
        return this.event;
    }

    public void setEvent(Event e) {
        this.event = e;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(LocalDateTime t){
        this.startTime = t;
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int n) {
        this.num = n;
    }

    public Unit getUnit() {
        return this.unit;
    }

    public void setUnit(Unit u) {
        this.unit = u;
    }


    // toString example: workout on 2020-March-1 13:00
    public String toString(){
        String s = this.event.name + " " + "on" + " " + this.event.startTime;
        return String.format("Alert: %s",s);

    }
}

