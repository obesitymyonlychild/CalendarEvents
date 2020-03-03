import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.Serializable;



public class Memo implements java.io.Serializable{

    private String name;
    private String content;
    private ArrayList<Event> events;



    public Memo(String name, String content){
        this.name = name;
        this.content = content;
        this.events = new ArrayList<Event>();
    }


    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getContent(){
        return content;
    }

    public ArrayList<Event> getEvents(){
        return events;
    }


    public void addEvent(String event_name, String startTime, long duration, String address){

        Event event1 = new Event(event_name, startTime, duration, address);
        event1.addNewMemo(this.name, this.content);
        this.events.add(event1);
    }


    public void deleteEvent(String event_name, String startTime, long duration, String address){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime t = LocalDateTime.parse(startTime, formatter);

        for (int i=0; i < this.events.size(); i++){
            if(this.events.get(i).getName().equals(event_name) &&
                    this.events.get(i).getStartTime() == t &&
                    this.events.get(i).getDuration() == duration &&
                    this.events.get(i).getAddress().equals(address)){

                this.events.get(i).deleteMemo(this.name);
                this.events.remove(this.events.get(i));
            }
        }

    }

    //public void checkAssociation()


    public String toString(){

        StringBuilder s = new StringBuilder();
        for (Event event : this.events) {
            s.append(event.toString()).append("\n");
        }
        return "This is a memo named as" + name + "with content" + content + "about" + s;

    }



}
