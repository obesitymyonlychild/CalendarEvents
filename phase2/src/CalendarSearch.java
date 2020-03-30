import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CalendarSearch {

    private static User currentUser;
    private static Calendar currentCalendar;

    public CalendarSearch() {
    }

    public static  User getCurrentUser(){
        return currentUser;
    }
    public static  Calendar getCurrent(){
        return currentCalendar;
    }

    public static void setCurrentUser(User para){
        CalendarSearch.currentUser = para;
    }
    public static void setCurrentCalendar(Calendar para){
        CalendarSearch.currentCalendar = para;
    }

    public static ArrayList<Event> searchEventByTag(){
        System.out.println("Please enter a tag name");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        ArrayList<Event> eventList = new ArrayList<Event>();
        boolean detective = false;
        for(Event event: currentCalendar.getEvents()){
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

        System.out.println("Please enter a date with the format yyyy-MM-dd");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate search = LocalDate.parse(input, formatter);
        ArrayList<Event> eventList = new ArrayList<Event>();
        boolean detective = false;
        for(Event event: currentCalendar.getEvents()){

            String startTimeString = event.getStartTime().format(formatter);
            LocalDate startTime = LocalDate.parse(startTimeString, formatter);
            String endTimeString = event.getEndTime().format(formatter);
            LocalDate endTime = LocalDate.parse(endTimeString, formatter);

            if(startTime.compareTo(search)<=0 && endTime.compareTo(search)>=0){

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
        for(Memo memo: currentCalendar.getMemos()){
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
        System.out.println("Please enter event name");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        ArrayList<Event> eventList = new ArrayList<Event>();
        boolean detective = false;

        for (Event event: currentCalendar.getEvents()){
            if (event.getName().contains(input)){
                detective = true;
                eventList.add(event);
            }
        }
        if (!detective){
            System.out.println("No event with this name");

        } else
            for (Event event: eventList){
                System.out.println("====================");
                System.out.println(event);
            }
        System.out.println("====================");
        return (ArrayList<Event>) eventList.stream().distinct().collect(Collectors.toList());

    }


    public static ArrayList<Event> searchEventBySeriesName(){

        // get rid of repetitive ele in arraylist
        System.out.println("Please enter a series name");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        ArrayList<Event> eventList = new ArrayList<Event>();
        boolean detective = false;
        for(Series series: currentCalendar.getSeries()){
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

        for(Alert alert: currentCalendar.getAlertList()){
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
