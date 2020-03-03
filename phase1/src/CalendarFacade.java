import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDateTime;

public class CalendarFacade {

    private static User currentUser;
    private static CalendarSearch calendarSearch;
    private static CalendarShow calendarShow;
    //private static LoginSystem loginSystem = new LoginSystem();

    public static  User getterCurrentUser(){
        return currentUser;
    }

    public static void setCurrentUser(User para){
        CalendarFacade.currentUser = para;
    }

    // Call all the methods of Event

    // Constructor
    public CalendarFacade(){
        // no requirement
        calendarSearch = new CalendarSearch(currentUser);
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

    public static void showOngoingEvent(){
        CalendarShow.showOngoingEvent();
    }

    public static void showPastEvent(){
        CalendarShow.showPastEvent();
    }

    public static void showFutureEvent(){
        CalendarShow.showFutureEvent();
    }

    public static void showEvents(){
        CalendarShow.showEvents();
    }

    public static ArrayList<Event> showTodayEvents(){
        return CalendarShow.showTodayEvents();
    }

    public static void showMemo(){
        // border
        CalendarShow.showMemo();
    }

    public static void showSeries(){
        CalendarShow.showSeries();
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
        return CalendarSearch.searchEventByTag();
    }

    public static ArrayList<Event> searchEventByDate(){
        return CalendarSearch.searchEventByDate();
    }

    public static ArrayList<Event> searchEventByMemo(){
        return CalendarSearch.searchEventByMemo();
    }

    public static ArrayList<Event> searchEventByName(){
        return CalendarSearch.searchEventByName();
    }


    public static ArrayList<Event> searchEventBySeriesName() {
        return CalendarSearch.searchEventBySeriesName();
    }

    public static void searchAlerts(){
        CalendarSearch.searchAlerts();
    }
}
