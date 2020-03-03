import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

//import java.io.Serializable

public class Series implements java.io.Serializable {

    //name of the Series
    private String name;

    //list of events in this series
    private ArrayList<Event> events;

    public Series(String name){
        this.name = name;
        events = new ArrayList<Event>();
    }

    private void createEventInMiniute(String nameOfEvent, LocalDateTime t, long duration, String address, int num){
        LocalDateTime time;
        for (int i = 0; i < num; i++){
            time = t.plusMinutes(i);
            Event e = new Event(nameOfEvent, time.toString().replace("T", " "), duration, address);
            events.add(e);
        }
    }

    private void createEventInHour(String nameOfEvent, LocalDateTime t, long duration, String address, int num){
        LocalDateTime time;
        for (int i = 0; i < num; i++){
            time = t.plusHours(i);
            Event e = new Event(nameOfEvent, time.toString().replace("T", " "), duration, address);
            events.add(e);
        }
    }

    private void createEventInDay(String nameOfEvent, LocalDateTime t, long duration, String address, int num){
        LocalDateTime time;
        for (int i = 0; i < num; i++){
            time = t.plusDays(i);
            Event e = new Event(nameOfEvent, time.toString().replace("T", " "), duration, address);
            events.add(e);
        }
    }

    private void createEventInWeek(String nameOfEvent, LocalDateTime t, long duration, String address, int num){
        LocalDateTime time;
        for (int i = 0; i < num; i++){
            time = t.plusWeeks(i);
            Event e = new Event(nameOfEvent, time.toString().replace("T", " "), duration, address);
            events.add(e);
        }
    }

    private void createEventInMonth(String nameOfEvent, LocalDateTime t, long duration, String address, int num){
        LocalDateTime time;
        for (int i = 0; i < num; i++){
            time = t.plusMonths(i);
            Event e = new Event(nameOfEvent, time.toString().replace("T", " "), duration, address);
            events.add(e);
        }
    }

    private void createEventInYear(String nameOfEvent, LocalDateTime t, long duration, String address, int num){
        LocalDateTime time;
        for (int i = 0; i < num; i++){
            time = t.plusYears(i);
            Event e = new Event(nameOfEvent, time.toString().replace("T", " "), duration, address);
            events.add(e);
        }
    }

    public void createFrequencyEvent(String nameOfEvent, String startTime, long duration, String address,
                                     int num, Unit unit){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime t = LocalDateTime.parse(startTime, formatter);
        switch (unit){
            case MINUTE:
                createEventInMiniute(nameOfEvent, t, duration, address, num);
            case HOUR:
                createEventInHour(nameOfEvent, t, duration,  address, num);
                break;
            case DAY:
                createEventInDay(nameOfEvent, t, duration,  address, num);
                break;
            case WEEK:
                createEventInWeek(nameOfEvent, t, duration,  address, num);
                break;
            case MONTH:
                createEventInMonth(nameOfEvent, t, duration,  address, num);
                break;
            case YEAR:
                createEventInYear(nameOfEvent, t, duration,  address, num);
                break;
            default:
                Event e = new Event(nameOfEvent, t.toString().replace("T", " "), duration, address);
                events.add(e);
        }
    }

    public void addToSeries(String nameOfEvent, String startTime, long duration, String address){
        Event e = new Event(nameOfEvent, startTime, duration, address);
        events.add(e);
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<Event> getEvents(){
        return this.events;
    }

    public String toString(){
        String result = name + ": \n";
        for (Event e : events){
            result = result + e.getName() + " ";
        }
    }

}
