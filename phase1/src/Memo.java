import java.util.ArrayList;
import java.util.List;



public class Memo {

    private String name;
    private String content;
    private ArrayList<Event> event;



    public Memo(String name, String content){
        this.name = name;
        this.content = content;

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

    public void addEvent(Event event){
        if(User.getPastEvent.contain(event) ||
                User.getFutureEvent.contain(event) ||
                User.getOngoingEvent.contain(event) ){

            event.addMemos(); // here is so strange
            event.getMemos().content.add(event);
        }else{
            System.out.println("No such event! Please try again.");
        }
    }

    public void deleteEvent(Event event){
        if(User.getPastEvent.contain(event) ||
                User.getFutureEvent.contain(event) ||
                User.getOngoingEvent.contain(event) ){

            event.getMemos().content.remove(event);
            event.deleteMemos();
        }else{
            System.out.println("No such event! Please try again.");
        }
    }

    public void modifyNote(Event original_event, Event new_event){
        if(User.getPastEvent.contain(original_event) ||
                User.getFutureEvent.contain(original_event) ||
                User.getOngoingEvent.contain(original_event) ){

            original_event.getMemos().content.remove(original_event);
            original_event.getMemos().content.add(new_event);
        }else{
            System.out.println("No such event! Please try again.");
        }
    }

    public String toString(){

        return "This is a memo named as" + name + "with content" + content +".";

    }



}
