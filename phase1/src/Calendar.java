import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDateTime;

public class Calendar {

    private static User currentUser;
    //private static LoginSystem loginSystem = new LoginSystem();

    public static  User getterCurrentUser(){
        return currentUser;
    }

    public static void setCurrentUser(User para){
        Calendar.currentUser = para;
    }

    // Call all the methods of Event

    // Constructor
    public Calendar(){
        // no requirement
    }

    // Call login system and set user to currentUser
    public static boolean callLogin() throws IOException, ClassNotFoundException {

        return LoginSystem.login();

    }

    public static void updater(){

        LocalDateTime now = LocalDateTime.now();

        for (Event event: currentUser.getEvents()){
            LocalDateTime startTime = event.getStartTime();
            LocalDateTime endTime = event.getEndTime();

            // case1 move futureEvent
            if (startTime.isAfter(now) && currentUser.getFutureEvents().contains(event))
                currentUser.moveToNow(event);
            // case2 move ongoingEvent
            if (endTime.isBefore(now) && currentUser.getOngoingEvents().contains(event))
                currentUser.moveToNow(event);
        }

    }

    public static void logout() {
        setCurrentUser(null);
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


    public static void alert(){

        LocalDateTime now = LocalDateTime.now();
        boolean detective = false;
        // check user alert on and event alert on
        if (currentUser.getAlertOn()){
            for(Alert alert: currentUser.getAlertList()){
                if (alert.getStartTime().compareTo(now) == 0) {
                    System.out.println(alert);
                    detective = true;
                }else {
                    if (detective)
                        break;
                }
            }
        }

    }

    public static ArrayList<Event> getEvents(){

        return currentUser.getEvents();

    }

    public static ArrayList<Event> searchEventByTag(){

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        ArrayList<Event> events = new ArrayList<Event>();
        boolean detective = false;
        for(Event event: currentUser.getEvents()){
            if(event.getTags().contains(input)){
                detective = true;
                events.add(event);
            }
        }
        if (!detective){
            System.out.println("No event with this tag");
            return null;
        } else
            for (Event event: events)
                System.out.println(event);
            return events;

    }

    public static ArrayList<Event> searchEventByDate(){

        System.out.println("Please enter a date with the format yyyy-MM-dd HH:mm");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime search = LocalDateTime.parse(input, formatter);
        ArrayList<Event> events = new ArrayList<Event>();
        boolean detective = false;
        for(Event event: currentUser.getEvents()){
            if(event.getStartTime().isBefore(search)  &&  event.getEndTime().isAfter(search)){
                detective = true;
                events.add(event);
            }
        }
        if (!detective){
            System.out.println("No event with this date");
            return null;
        } else
            for (Event event: events)
                System.out.println(event);
            return events;

    }


    public static ArrayList<Event> searchEventByMemo(){
        System.out.println("Please enter a keyword to search memo");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        ArrayList<Event> events = new ArrayList<Event>();
        boolean detective = false;
        for(Memo memo: currentUser.getMemos()){
            if(memo.getContent().contains(input)){
                detective = true;
                events.addAll(memo.getEvents());
            }
        }
        if (!detective){
            System.out.println("No event with this memo");
            return null;
        } else
            for (Event event: events)
                System.out.println(event);
            return events;
    }

    public static ArrayList<Event> searchEventByName(){

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        ArrayList<Event> events = new ArrayList<Event>();
        boolean detective = false;

        for (Event event: currentUser.getEvents()){
            if (event.getName().contains(input))
                detective = true;
                events.add(event);
        }
        if (!detective){
            System.out.println("No event with this name");
            return null;
        } else
            for (Event event: events)
                System.out.println(event);
        return events;

    }


    public static ArrayList<Event> searchEventBySeriesName(){

        // get rid of repetitive ele in arraylist

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        ArrayList<Event> events = new ArrayList<Event>();
        boolean detective = false;
        for(Series series: currentUser.getSeries()){
            if(series.getName().contains(input)){
                detective = true;
                events.addAll(series.getEvents());
            }
        }
        if (!detective){
            System.out.println("No event with this name");
            return null;
        } else
            for (Event event: events)
                System.out.println(event);
        return events;


    }

    public static void searchAlerts(){
        //println alerts with time range(input)
        Scanner scan = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.println("Please enter the start time with the format yyyy-MM-dd HH:mm");
        LocalDateTime startTime = LocalDateTime.parse(scan.nextLine(), formatter);
        System.out.println("Please enter the end time with the format yyyy-MM-dd HH:mm");
        LocalDateTime endTime = LocalDateTime.parse(scan.nextLine(), formatter);

        for(Alert alert: currentUser.getAlertList()){
            if (alert.getStartTime().isAfter(startTime) &&  alert.getStartTime().isBefore(endTime))
                System.out.println(alert);
        }

    }








}




































