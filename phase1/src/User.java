import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class User implements Serializable{
    private String name;
    private String password;
    private ArrayList<Event> pastEvents = new ArrayList<>();
    private ArrayList<Event> futureEvents = new ArrayList<>();
    private ArrayList<Event> ongoingEvents = new ArrayList<>();
    private ArrayList<Event> events = new ArrayList<>();
    private ArrayList<Memo> memos = new ArrayList<>();
    private boolean alertOn;
    private ArrayList<Alert> alertList = new ArrayList<>();
    private ArrayList<Series> series = new ArrayList<>();

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
        return this.pastEvents;
    }

    public void setPastEvents(ArrayList<Event> pastEvents) {
        this.pastEvents = pastEvents;
    }

    public ArrayList<Event> getFutureEvents() {
        return this.futureEvents;
    }

    public void setFutureEvents(ArrayList<Event> futureEvents) {
        this.futureEvents = futureEvents;
    }

    public ArrayList<Event> getOngoingEvents() {
        return ongoingEvents;
    }

    public void setOngoingEvent(ArrayList<Event> ongoingEvents) {
        this.ongoingEvents = ongoingEvents;
    }

    public ArrayList<Event> getEvents(){
        return this.events;
    }

    public void setEvents(ArrayList<Event> evts){
        this.events = evts;
    }

    public boolean getAlertOn(){
        return this.alertOn;
    }

    public void setAlertOn() {
        this.alertOn = true;
    }

    public void setAlertOff(){
        this.alertOn = false;
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

    public void setMemos(ArrayList<Memo> mem) {
        this.memos = mem;
    }

    public ArrayList<Series> getSeries(){
        return this.series;
    }

    public void setSeries(ArrayList<Series> series){
        this.series = series;
    }

    public void OrderEvents(){
        Collections.sort(this.events);
    }

    public void OrderPastEvents(){
        Collections.sort(this.pastEvents);
    }

    public void OrderFutureEvents(){
        Collections.sort(this.futureEvents);
    }

    public void OrderOngoingEvents(){
        Collections.sort(this.ongoingEvents);
    }

    public void createMemo(String name, String content){
        Memo mm = new Memo(name, content);
        this.memos.add(mm);
        System.out.println("Memo added successfully!");
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

    public void createEvent(String name, String startTime, int duration, String address){
        Event evt = new Event(name, startTime, duration, address);
        this.events.add(evt);
        System.out.println(this.events);
        LocalDateTime start = evt.getStartTime();
        LocalDateTime end = start.plusMinutes(duration);
        LocalDateTime now = LocalDateTime.now();
        if(start.isAfter(now)){
            this.futureEvents.add(evt);
            System.out.println(this.futureEvents);
        }
        else if(start.isBefore(now)){
            if (end.isBefore(now)){
                this.pastEvents.add(evt);
                System.out.println(this.pastEvents);
            }
            else{this.ongoingEvents.add(evt);
                System.out.println(this.ongoingEvents);
            }
        }
        else{
            this.ongoingEvents.add(evt);
            System.out.println(this.ongoingEvents);
        }
        System.out.println("Event added successfully!");
    }

    public void deleteEvent(String name){
        boolean res = false;
        for (Event evt: this.events)
            if (evt.getName().equals(name)) {
                this.events.remove(evt);
                res = true;
            }
        for (int i=0; i < this.ongoingEvents.size(); i++){
            if (this.ongoingEvents.get(i).getName().equals(name)){
                this.ongoingEvents.remove(i);
                break;
            }
        }
        for (int i=0; i < this.pastEvents.size(); i++){
            if (this.pastEvents.get(i).getName().equals(name)){
                this.pastEvents.remove(i);
                break;
            }
        }
        for (int i=0; i < this.futureEvents.size(); i++){
            if (this.futureEvents.get(i).getName().equals(name)){
                this.futureEvents.remove(i);
                break;
            }
        }

        if (!res){
            System.out.println("No such event exists!");
        }
    }

    public void moveToNow(Event evt) {
        deleteEvent(evt.getName());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String str =  formatter.format(evt.getStartTime());
        createEvent(evt.getName(), str, evt.getDuration(), evt.getAddress());
    }

    public void createSeries(String name){
        Series sir  = new Series(name);
        this.series.add(sir);
    }

    public ArrayList<Event> searchEventsBySeries(String name){
        for(Series series: this.series){
            if (series.getName().equals(name)){
                return series.getEvents();
            }
        }
        System.out.println("Series does not exists!");
        return null;
    }
}