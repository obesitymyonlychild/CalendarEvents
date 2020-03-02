import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
public class User implements Serializable {
    private String name;
    private String password;
    private ArrayList<Event> pastEvent;
    private Arraylist<Event> futureEvent;
    private ArrayList<Event> ongoingEvent;
    private ArrayList<Memo> memo;
    boolean alertOn;
    private ArrayList<Alert> alertList;

    public User(String name, String psw){
        this.name = name;
        this.password = psw;
        this.alertOn = true;
    }
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String psw){
        this.password = psw;
    }

    public ArrayList<Event> getPastEvent() {
        return pastEvent;
    }

    public void setPastEvent(ArrayList<Event> pastEvent) {
        this.pastEvent = pastEvent;
    }

    public Arraylist<Event> getFutureEvent() {
        return futureEvent;
    }

    public void setFutureEvent(Arraylist<Event> futureEvent) {
        this.futureEvent = futureEvent;
    }

    public ArrayList<Event> getOngoingEvent() {
        return ongoingEvent;
    }

    public void setOngoingEvent(ArrayList<Event> ongoingEvent) {
        this.ongoingEvent = ongoingEvent;
    }

    public ArrayList<Alert> getAlertList() {
        return alertList;
    }

    public void setAlertList(ArrayList<Alert> alertList) {
        this.alertList = alertList;
    }

    public ArrayList<Memo> getMemo() {
        return memo;
    }

    public void setMemo(ArrayList<Memo> mem) {
        this.memo = mem;
    }

    public Memo createMemo(String name, List<String> content){
        Memo mm = new Memo(name, content);
        this.memo.add(mm);
        return mm;
    }

    public void deleteMemo(String name){
        for (int i=0; i < this.memo.size(); i++){
            if(this.memo.get(i).getName().equals(name)){
                this.memo.remove(i);
                break;
            }
        }
    }

    public Event createEvent(String name, String startTime, long duration, String address){
        Event evt = new Event(name, startTime, duration, address);
        LocalDateTime start = evt.getStartTime();
        LocalDateTime end = start.plusMinutes(duration);
        LocalDateTime now = LocalDateTime.now();
        if(start.isAfter(now)){
            this.futureEvent.add(evt);
        }
        else if(start.isBefore(now)){
            if (end.isBefore(now)){
                this.pastEvent.add(evt);
            }
            else{this.ongoingEvent.add(evt);
            }
        }
        else{
            this.ongoingEvent.add(evt);
        }
    }

    public void deleteEvent(String name){
        for (int i=0; i < this.ongoingEvent.size(); i++){
            if (this.ongoingEvent.get(i).getName().equals(name)){
                this.ongoingEvent.remove(i);
                break;
            }
        }
        for (int i=0; i < this.pastEvent.size(); i++){
            if (this.pastEvent.get(i).getName().equals(name)){
                this.pastEvent.remove(i);
                break;
            }
        }
        for (int i=0; i < this.futureEvent.size(); i++){
            if (this.futureEvent.get(i).getName().equals(name)){
                this.futureEvent.remove(i);
                break;
            }
        }
    }

    public static void main(String[] args) {


    }


}
