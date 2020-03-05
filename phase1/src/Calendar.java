import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class Calendar {

    private static User currentUser;
    //private static LoginSystem loginSystem = new LoginSystem();

    public static  User getCurrentUser(){
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
        ArrayList<Event> eventList = new ArrayList<Event>();
        boolean detective = false;
        for(Event event: currentUser.getEvents()){
            if(event.getTags().contains(input)){
                detective = true;
                eventList.add(event);
            }
        }
        if (!detective){
            System.out.println("No event with this tag");

        } else
            for (Event event: eventList)
                System.out.println(event);
        return (ArrayList<Event>) eventList.stream().distinct().collect(Collectors.toList());

    }

    public static ArrayList<Event> searchEventByDate(){

        System.out.println("Please enter a date with the format yyyy-MM-dd HH:mm");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime search = LocalDateTime.parse(input, formatter);
        ArrayList<Event> eventList = new ArrayList<Event>();
        boolean detective = false;
        for(Event event: currentUser.getEvents()){
            if(event.getStartTime().isBefore(search)  &&  event.getEndTime().isAfter(search)){
                detective = true;
                eventList.add(event);
            }
        }
        if (!detective){
            System.out.println("No event with this date");

        } else
            for (Event event: eventList){
                System.out.println("====================");
                System.out.println(event);
            }

        return (ArrayList<Event>) eventList.stream().distinct().collect(Collectors.toList());

    }


    public static ArrayList<Event> searchEventByMemo(){
        System.out.println("Please enter a keyword to search memo");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        ArrayList<Event> eventList = new ArrayList<Event>();
        boolean detective = false;
        for(Memo memo: currentUser.getMemos()){
            if(memo.getContent().contains(input)){
                detective = true;
                eventList.addAll(memo.getEvents());
            }
        }
        if (!detective){
            System.out.println("No event with this memo");

        } else
            for (Event event: eventList){
                System.out.println("====================");
                System.out.println(event);
            }
        return (ArrayList<Event>) eventList.stream().distinct().collect(Collectors.toList());
    }

    public static ArrayList<Event> searchEventByName(){

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        ArrayList<Event> eventList = new ArrayList<Event>();
        boolean detective = false;

        for (Event event: currentUser.getEvents()){
            if (event.getName().contains(input))
                detective = true;
            eventList.add(event);
        }
        if (!detective){
            System.out.println("No event with this name");

        } else
            for (Event event: eventList){
                System.out.println("====================");
                System.out.println(event);
            }
        return (ArrayList<Event>) eventList.stream().distinct().collect(Collectors.toList());

    }


    public static ArrayList<Event> searchEventBySeriesName(){

        // get rid of repetitive ele in arraylist

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        ArrayList<Event> eventList = new ArrayList<Event>();
        boolean detective = false;
        for(Series series: currentUser.getSeries()){
            if(series.getName().contains(input)){
                detective = true;
                eventList.addAll(series.getEvents());
            }
        }
        if (!detective){
            System.out.println("No event with this name");

        } else
            for (Event event: eventList){
                System.out.println("====================");
                System.out.println(event);
            }
        return (ArrayList<Event>) eventList.stream().distinct().collect(Collectors.toList());


    }

    public static ArrayList<Alert> searchAlerts(){
        //println alerts with time range(input)
        Scanner scan = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        ArrayList<Alert> alertList = new ArrayList<Alert>();
        boolean detective = false;

        System.out.println("Please enter the start time with the format yyyy-MM-dd HH:mm");
        LocalDateTime startTime = LocalDateTime.parse(scan.nextLine(), formatter);
        System.out.println("Please enter the end time with the format yyyy-MM-dd HH:mm");
        LocalDateTime endTime = LocalDateTime.parse(scan.nextLine(), formatter);

        for(Alert alert: currentUser.getAlertList()){
            if (alert.getStartTime().isAfter(startTime) &&  alert.getStartTime().isBefore(endTime)){
                alertList.add(alert);
                detective = true;
            }
        }

        if (!detective){
            System.out.println("No alert with this duration");

        } else
            for (Alert alert: alertList){
                System.out.println("====================");
                System.out.println(alert);
            }
        return (ArrayList<Alert>) alertList.stream().distinct().collect(Collectors.toList());





    }








}




































