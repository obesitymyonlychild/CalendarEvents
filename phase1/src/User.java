import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
public class User implements Serializable {
    private String name;
    private String password;
    private ArrayList<Event> pastEvents;
    private ArrayList<Event> futureEvents;
    private ArrayList<Event> ongoingEvents;
    private ArrayList<Memo> memos;
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

    public ArrayList<Event> getPastEvents() {
        return pastEvents;
    }

    public void setPastEvents(ArrayList<Event> pastEvents) {
        this.pastEvents = pastEvents;
    }

    public ArrayList<Event> getFutureEvents() {
        return futureEvents;
    }

    public void setFutureEvents(ArrayList<Event> futureEvents) {
        this.futureEvents = futureEvents;
    }

    public ArrayList<Event> getOngoingEvents() {
        return ongoingEvents;
    }

    public ArrayList<Event> getEvents(){
        ArrayList<Event> all = new ArrayList<>();
        all.addAll(this.pastEvents);
        all.addAll(this.ongoingEvents);
        all.addAll(this.futureEvents);
        return all;
    }

    public void setOngoingEvent(ArrayList<Event> ongoingEvents) {
        this.ongoingEvents = ongoingEvents;
    }


    public ArrayList<Alert> getAlertList() {
        return alertList;
    }

    public void setAlertList(ArrayList<Alert> alertList) {
        this.alertList = alertList;
    }

    public ArrayList<Memo> getMemos() {
        return memos;
    }

    public void setMemo(ArrayList<Memo> mem) {
        this.memos = mem;
    }

    public Memo createMemo(String name, String content){
        Memo mm = new Memo(name, content);
        this.memos.add(mm);
        return mm;
    }

    public void deleteMemos(String name){
        boolean res = false;
        for (int i=0; i < this.memos.size(); i++){
            if(this.memos.get(i).getName().equals(name)){
                this.memos.remove(i);
                res = true;
                break;
            }
        }
        if (!res){
            System.out.println("No such memo exists!");
        }
    }

    public void createEvent(String name, String startTime, long duration, String address){
        Event evt = new Event(name, startTime, duration, address);
        LocalDateTime start = evt.getStartTime();
        LocalDateTime end = start.plusMinutes(duration);
        LocalDateTime now = LocalDateTime.now();
        if(start.isAfter(now)){
            this.futureEvents.add(evt);
        }
        else if(start.isBefore(now)){
            if (end.isBefore(now)){
                this.pastEvents.add(evt);
            }
            else{this.ongoingEvents.add(evt);
            }
        }
        else{
            this.ongoingEvents.add(evt);
        }
    }

    public void deleteEvent(String name){
        boolean res = false;
        for (int i=0; i < this.ongoingEvents.size(); i++){
            if (this.ongoingEvents.get(i).getName().equals(name)){
                this.ongoingEvents.remove(i);
                res = true;
                break;
            }
        }
        for (int i=0; i < this.pastEvents.size(); i++){
            if (this.pastEvents.get(i).getName().equals(name)){
                this.pastEvents.remove(i);
                res = true;
                break;
            }
        }
        for (int i=0; i < this.futureEvents.size(); i++){
            if (this.futureEvents.get(i).getName().equals(name)){
                this.futureEvents.remove(i);
                res = true;
                break;
            }
        }
        if (!res){
            System.out.println("No such event exists!");
        }
    }

}
