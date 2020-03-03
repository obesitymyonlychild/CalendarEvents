import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class CalendarSearch {

    private static User currentUser;

    public CalendarSearch(User user) {
        currentUser = user;
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
