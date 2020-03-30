import javax.swing.*;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDateTime;

public class CalendarFacade {

    private static User currentUser;
    private static Calendar currentCalendar;

    //private static LoginSystem loginSystem = new LoginSystem();

    public static String timeAppeared = "0";

    public static  User getCurrentUser(){
        return currentUser;
    }
    public static  Calendar getCurrentCalendar(){
        return currentCalendar;
    }


    public static void setCurrentUser(User para){
        CalendarFacade.currentUser = para;
        CalendarShow.setCurrentUser(para);
        CalendarSearch.setCurrentUser(para);
    }

    public static void setCurrentCalendar(Calendar para){
        CalendarFacade.currentCalendar = para;
        CalendarShow.setCurrentCalendar(para);
        CalendarSearch.setCurrentCalendar(para);
    }

    // Call all the methods of Event

    // Constructor
    public CalendarFacade(){
        // no requirement
    }

    // Call login system and set user to currentUser
    public static void callLogin() throws IOException, ClassNotFoundException {
        LoginSystem.login();
    }

    public static void updater(){

        LocalDateTime now = LocalDateTime.now();

        for (Event event: currentCalendar.getEvents()){
            LocalDateTime startTime = event.getStartTime();
            LocalDateTime endTime = event.getEndTime();

            // case1 move futureEvent
            if (startTime.isAfter(now) && currentCalendar.getFutureEvents().contains(event))
                currentCalendar.moveToNow(event);
            // case2 move ongoingEvent
            if (endTime.isBefore(now) && currentCalendar.getOngoingEvents().contains(event))
                currentCalendar.moveToNow(event);
        }

    }

    public static void logout() {
        setCurrentUser(null);
        setCurrentCalendar(null);
    }

    public static ArrayList<Event> showOngoingEvent(){
        return CalendarShow.showOngoingEvent();
    }

    public static ArrayList<Event> showPastEvent(){
        return CalendarShow.showPastEvent();
    }

    public static ArrayList<Event> showFutureEvent(){
        return CalendarShow.showFutureEvent();
    }

    public static ArrayList<Event> showEvents(){
        return CalendarShow.showEvents();
    }

    public static ArrayList<Event> showTodayEvents(){
        return CalendarShow.showTodayEvents();
    }

    public static String getMemoContent(String memoName) { return CalendarShow.getMemoContent(memoName);  }

    public static ArrayList<Memo> showMemo(){
        // border
        return CalendarShow.showMemo();
    }

    public static ArrayList<Series> showSeries(){
        return CalendarShow.showSeries();
    }

    public static void alert(){


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String nowString = LocalDateTime.now().format(formatter);
        //LocalDateTime now = LocalDateTime.parse(nowString, formatter);

        boolean detective = false;
        // check user alert on and event alert on
        if (currentUser.getAlertOn()){
            for (Event event: currentCalendar.getEvents())
                for(Alert alert: event.getAlerts()){
                    //if (alert.getStartTime().isEqual(now)) {
                    if ((alert.getStartTime().format(formatter)).equals(nowString) && !nowString.equals(timeAppeared)) {
                        //System.out.println(alert);
                        JOptionPane.showMessageDialog(null, alert.toString());
                        detective = true;
                        timeAppeared = nowString;
                        //alert.closeAlert();
                    }else {
                        if (detective)
                            break;
                    }
                }
        }

    }

    public static ArrayList<Event> getEvents(){
        return currentCalendar.getEvents();
    }

    public static void searchEventByTag(){
        CalendarSearch.searchEventByTag();
    }

    public static void searchEventByDate(){
        CalendarSearch.searchEventByDate();
    }

    public static void searchEventByMemo(){
        CalendarSearch.searchEventByMemo();
    }

    public static void searchEventByName(){
        CalendarSearch.searchEventByName();
    }


    public static void searchEventBySeriesName() {
        CalendarSearch.searchEventBySeriesName();
    }

    public static void searchAlerts(){
        CalendarSearch.searchAlerts();
    }


}
