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

    public static void setCurrentUser(User para){
        CalendarFacade.currentUser = para;
    }

    public static  Calendar getCurrentCalendar(){
        return currentCalendar;
    }

    public static void setCurrentCalendar(Calendar para){
        CalendarFacade.currentCalendar = para;
    }

    // Call all the methods of Event

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
        return CalendarShow.showOngoingEvent(currentCalendar);
    }

    public static ArrayList<Event> showPastEvent(){
        return CalendarShow.showPastEvent(currentCalendar);
    }

    public static ArrayList<Event> showFutureEvent(){
        return CalendarShow.showFutureEvent(currentCalendar);
    }

    public static ArrayList<Event> showEvents(){
        return CalendarShow.showEvents(currentCalendar);
    }

    public static ArrayList<Event> showTodayEvents(){
        return CalendarShow.showTodayEvents(currentCalendar);
    }

    public static String getMemoContent(String memoName) { return CalendarShow.getMemoContent(currentCalendar, memoName);  }

    public static ArrayList<Memo> showMemo(){
        // border
        return CalendarShow.showMemo(currentCalendar);
    }

    public static ArrayList<Series> showSeries(){
        return CalendarShow.showSeries(currentCalendar);
    }


    public static ArrayList<Event> getEvents(){
        return currentCalendar.getEvents();
    }

    public static ArrayList<Event> searchEventByTag(String para){
        return CalendarSearch.searchEventByTag(currentCalendar, para);
    }

    public static ArrayList<Event> searchEventByDate(String para){
        return CalendarSearch.searchEventByDate(currentCalendar, para);
    }

    public static ArrayList<Event> searchEventByMemo(String para){
        return CalendarSearch.searchEventByMemo(currentCalendar, para);
    }

    public static ArrayList<Event> searchEventByName(String para){
        return CalendarSearch.searchEventByName(currentCalendar, para);
    }


    public static ArrayList<Event> searchEventBySeriesName(String para) {
        return CalendarSearch.searchEventBySeriesName(currentCalendar, para);
    }

    public static ArrayList<Alert> searchAlerts(String para1, String para2){
        return CalendarSearch.searchAlerts(currentCalendar, para1, para2);
    }

    public static void alert(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String nowString = LocalDateTime.now().format(formatter);
        //LocalDateTime now = LocalDateTime.parse(nowString, formatter);

        boolean detective = false;
        // check user alert on and event alert on
        if (currentCalendar.getAlertOn()){
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


}
