import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDateTime;

public class CalendarShow {

    private static User currentUser;
    public CalendarShow(){
    }

    public static  User getCurrentUser(){
        return currentUser;
    }

    public static void setCurrentUser(User para){
        CalendarShow.currentUser = para;
    }

    public static ArrayList<Event> showOngoingEvent(){

        ArrayList<Event> eventList = currentUser.getOngoingEvents();
        int index = 1;
        for(Event event: eventList){
            System.out.println(index + ". " + event.toString());
            index++;
        }
        if (index == 1){
            System.out.println("No ongoing event");
        }
        return eventList;

    }

    public static ArrayList<Event> showPastEvent(){

        ArrayList<Event> eventList = currentUser.getPastEvents();
        int index = 1;
        for(Event event: eventList){
            System.out.println(index + ". " + event.toString());
            index++;
        }
        if (index == 1){
            System.out.println("No past event");
        }

        return eventList;

    }

    public static ArrayList<Event> showFutureEvent(){

        ArrayList<Event> eventList = currentUser.getFutureEvents();
        int index = 1;
        for(Event event: eventList){
            System.out.println(index + ". " + event.toString());
            index++;
        }
        if (index == 1){
            System.out.println("No future event");
        }
        return eventList;

    }

    public static ArrayList<Event> showEvents(){

        ArrayList<Event> eventList = currentUser.getEvents();
        int index = 1;
        for(Event event: eventList){
            System.out.println(index + ". " + event.toString());
            index++;
        }
        if (index == 1){
            System.out.println("No event");
        }
        return eventList;

    }

    public static ArrayList<Event> showTodayEvents(){

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String nowString = now.format(formatter);
        ArrayList<Event> eventList = new ArrayList<Event>();
        boolean detective = false;

        for (Event event: currentUser.getEvents()) {
            if (event.getStartTime().format(formatter).equals(nowString)) {
                detective = true;
                eventList.add(event);
            }
        }

        if (!detective){
            System.out.println("No task today");
        } else{
            int index = 1;
            for (Event event: eventList){
                System.out.println(index + ". " + event.toString());
                index++;
            }
        }
        return eventList;

    }

    public static ArrayList<Memo> showMemo(){
        // border
        // border
        ArrayList<Memo> memoList = currentUser.getMemos();
        int index = 1;
        for(Memo memo: memoList){
            System.out.println(index + ". " + memo.toString());
            index++;
        }
        if (index == 1){
            System.out.println("No memo");
        }
        return memoList;
    }

    public static ArrayList<Series> showSeries(){
        ArrayList<Series> seriesList = currentUser.getSeries();
        int index = 1;
        for(Series series: seriesList){
            System.out.println(index + ". " + series.toString());
            index++;
        }
        if (index == 1){
            System.out.println("No series");
        }
        return seriesList;
    }

    public static String getMemoContent(String memoName) {
        String res = "No result";
        for (Memo memo: currentUser.getMemos()){
            if (memo.getName().equals(memoName))
                return memo.getContent();
        }
        return res;

    }
}
