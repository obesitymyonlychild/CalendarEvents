import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDateTime;

public class CalendarShow {

    private static User currentUser;
    public CalendarShow(User user){
        currentUser = user;
    }

    public static ArrayList<Event> showOngoingEvent(){

        ArrayList<Event> events = currentUser.getOngoingEvents();
        int index = 0;
        for(Event event: currentUser.getOngoingEvents()){
            System.out.println(index + event.toString());
            index++;
        }
        return events;
    }

    public static ArrayList<Event> showPastEvent(){
        ArrayList<Event> events = currentUser.getOngoingEvents();
        int index = 0;
        for(Event event: currentUser.getPastEvents()){
            System.out.println(index + event.toString());
            index++;
        }
        return events;
    }

    public static ArrayList<Event> showFutureEvent(){
        ArrayList<Event> events = currentUser.getOngoingEvents();
        int index = 0;
        for(Event event: currentUser.getFutureEvents()){
            System.out.println(index + event.toString());
            index++;
        }
        return events;
    }

    public static ArrayList<Event> showEvents(){
        ArrayList<Event> events = currentUser.getOngoingEvents();
        int index = 0;
        for(Event event: currentUser.getEvents()){
            System.out.println(index + event.toString());
            index++;
        }
        return events;
    }

    public static ArrayList<Event> showTodayEvents(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String nowString = now.format(formatter);
        ArrayList<Event> events = new ArrayList<Event>();
        boolean detective = false;

        for (Event event: currentUser.getOngoingEvents()) {
            if (event.getStartTime().format(formatter).equals(nowString)) {
                detective = true;
                events.add(event);
            }
        }

        if (!detective){
            System.out.println("No task today");
            return null;
        } else{
            int index = 0;
            for (Event event: events){
                System.out.println(index + event.toString());
                index++;
            }
            return events;
        }
    }

    public static void showMemo(){
        // border
        for(Memo memo: currentUser.getMemos())
            System.out.println(memo);
    }

    public static void showSeries(){
        for(Series series: currentUser.getSeries())
            System.out.println(series);
    }
}
