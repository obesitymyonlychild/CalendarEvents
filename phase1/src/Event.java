import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
//import java.io.Serializable

public class Event implements java.io.Serializable, Comparable<Event> {

    //name of the event
    String name;

    //start time of the event
    LocalDateTime startTime;

    //duration of the event in minute
    long duration;

    //address of the event
    String address;

    //list of tags of the event
    ArrayList<String> tags;

    //list of memos of the event
    ArrayList<Memo> memos;

    //the alert of the event
    ArrayList<Alert> alerts = new ArrayList<Alert>();

    //a boolean indicates whether the alert of the event is on or off
    boolean alertOn = true;

    public Event(String name, String startTime, long duration, String address){
        this.name = name;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.startTime = LocalDateTime.parse(startTime, formatter);
        this.duration = duration;
        this.address = address;
        this.tags = new ArrayList<String>();
        this.memos = new ArrayList<Memo>();
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public long getDuration() {
        return duration;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public ArrayList<Memo> getMemos() {
        return memos;
    }

    public ArrayList<Alert> getAlerts() {
        return alerts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartTime(String startTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.startTime = LocalDateTime.parse(startTime, formatter);
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private LocalDateTime getStartTimeForAlert(LocalDateTime t, int increment, Unit unit){
        switch (unit){
            case MINUTE:
                return t.plusMinutes(increment);
            case HOUR:
                return t.plusHours(increment);
            case DAY:
            case WEEK:
                return t.plusWeeks(increment);
            case MONTH:
                return t.plusMonths(increment);
            case YEAR:
                return t.plusYears(increment);
            default:
                return t;
        }
    }

    public void setAlert(String startTime, int num, Unit unit) {
        // no alert at same time
        Alert a;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime t = LocalDateTime.parse(startTime, formatter);
        for (int i = 0; i<num; i++){
            t = getStartTimeForAlert(t, i, unit);
            a = new Alert(this, t);
            alerts.add(a);
        }
    }

    public void deleteAlert(String startTime){
        //leads to unreferenced Alert object?
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime t = LocalDateTime.parse(startTime, formatter);
        for (Alert a : alerts) {
            if (t.isEqual(a.getStartTime())) {
                alerts.remove(a);
            }
        }
    }

    public void turnOnAlert(){
        if (! alertOn){
            alertOn = true;
        }
    }

    public void turnOffAlert(){
        if (alertOn) {
            alertOn = false;
        }
    }


    public void addTag(String tag) {
        tags.add(tag);
    }

    public void deleteTag(String tag) {
        tags.remove(tag);
    }

    public void addNewMemo(String name, String content){
        Memo m = new Memo(name, content);
        memos.add(m);
        m.addEvent(this.name, this.startTime.toString().replace("T", " "), this.duration, this.address);
    }

    public void deleteMemo(String nameOfMemo) {
        for (Memo m : memos){
            if (m.getName().equals(nameOfMemo)){
                m.deleteEvent(this.name, this.startTime.toString().replace("T", " "), duration, address);
                memos.remove(m);
            }
        }
    }

    public String toString(){
        String result = name + ": " + "from " + startTime.toString() + " to ";
        LocalDateTime endTime = startTime.plusMinutes(duration);
        result = result + endTime.toString() + " at " + address;
        return result;
    }

    public void orderAlert(){
        Collections.sort(this.alerts);
    }

    @Override
    public int compareTo(Event e1) {
        if (this.startTime.isEqual(e1.startTime)) {
            return 0;
        }else if (this.startTime.isBefore(e1.startTime)) {
            return -1;
        }else{
            return 1;
        }

    }

}
