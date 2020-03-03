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

    // Constructor
    public Calendar(){
        // no requirement
    }

    // Call login system and set user to currentUser
    public static boolean callLogin() throws IOException, ClassNotFoundException {

        return LoginSystem.login();

    }

    public static void updater(){
        // Not sure what the func of update
        //ArrayList<Event> events = currentUser.getEvents();
        LocalDateTime now = LocalDateTime.now();

        for (Event event: currentUser.getEvents()){
            LocalDateTime startTime = event.getStartTime();
            LocalDateTime endTime = event.getEndTime();

        }





    }

    public static void logout() {
        setCurrentUser(null);
    }

    public static void ShowOngoingEvent(){

        for(Event event: currentUser.getOngoingEvents())
            System.out.println(event);

    }

    public static void showPastEvent(){

        for(Event event: currentUser.getPastEvents())
            System.out.println(event);

    }

    public static void ShowFutureEvent(){

        for(Event event: currentUser.getFutureEvents())
            System.out.println(event);
    }

    public static void showEvents(){

        for(Event event: currentUser.getEvents())
            System.out.println(event);

    }

    public static ArrayList<Event> showTodayEvents(){

    }

    public static void showMemo(){
        //边框
        for(Memo memo: currentUser.getMemos())
            System.out.println(memo);

    }

    public static void showNotification(){
        //println alerts with time range(input)
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the start time with the format yyyy-MM-dd HH:mm");
        String


        String startTime = scan.nextLine();


    }

    public static void alert(){

        LocalDateTime now = LocalDateTime.now();
        boolean detective = false;
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

    public static ArrayList<Event> getEvents(){

        return currentUser.getEvents();


    }

    public static Event searchEventByTag(){
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        for(Event event: currentUser.getEvents()){
            if(event.getTags().contains(input))
                System.out.println(event);
            else
                System.out.println("No result");
        }
    }

    public static Event searchEventByDate(){
        // how to compare
        System.out.println("Please enter a date with the format yyyy-MM-dd HH:mm ");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime search = LocalDateTime.parse(input, formatter);
        for(Event event: currentUser.getEvents()){
            LocalDateTime eventYear = event.getStartTime().format(formatter);
            LocalDateTime eventDate = LocalDateTime.parse();
            if(eventYear == search){
                System.out.println(event);
                return event;
            }
            else
                System.out.println("No result");
        }
    }


    public static Event searchEventByDuration(){
        //no requirement
    }

    public static Memo searchEventByMemo(){
        System.out.println("Please enter a keyword to search memo");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        //ArrayList memoList = currentUser.getMemo();
        for(Memo memo: currentUser.getMemos()){
            if(memo.getContent().contains(input)){
                System.out.println(memo);
                return memo;
            }
            else
                System.out.println("No result");
        }
    }

    public static Event searchEventBySeriesName(){
        // From User get the Arraylist of series


    }






}




































