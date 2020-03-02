import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDateTime;

public class Calendar {

    private User currentUser;
    private LoginSystem loginSystem = new LoginSystem();

    public User getterCurrentUser(){
        return currentUser;
    }

    public void setterCurrentUser(User para){
        this.currentUser = para;
    }

    // Constructor
    public Calendar(){

    }

    // Call login system and set user to currentUser
    public void callLogin() {
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Do you want to Sign up or Sign in?");
//        String res = scan.next();
//        if(res.equals("Sign up"))
//            ...
//        else
//            ...

    }

    public updater(){
        // Not sure what the func of update

    }

    public void logout() {
        setterCurrentUser(null);
    }

    public void ShowOngoingEvent(){

        for(Event event: currentUser.getterOngoingEvent())
            System.out.println(event);

    }

    public void showPastEvent(){

        for(Event event: currentUser.getterPastEvent())
            System.out.println(event);

    }

    public void ShowFutureEvent(){

        for(Event event: currentUser.getterFutureEvent())
            System.out.println(event);
    }

    public void showEvents(){

        for(Event event: currentUser.getterEvent())
            System.out.println(event);

    }

    public void showMemo(){
        //边框
        for(Memo memo: currentUser.getterMemo())
            System.out.println(memo);

    }

    public void showNotification(){

    }

    public void alert(){

    }

    public void getEvent(){

    }

    public Event searchEventByTag(){
        Scanner scan = new Scanner(System.in);
        String input = scan.next();
        for(Event event: currentUser.getterEvent()){
            if(event.getterTag().contains(input))
                System.out.println(event);
            else
                System.out.println("No result");
        }
    }

    public Event searchEventByDate(){
        System.out.println("Please enter a date with the format yyyy MM dd");
        Scanner scan = new Scanner(System.in);
        String input = scan.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
        LocalDateTime search = LocalDateTime.parse(input, formatter);
        for(Event event: currentUser.getterEvent()){
            LocalDateTime eventYear = event.getterStartTime().format(formatter);
            LocalDateTime eventDate = LocalDateTime.parse()
            if(eventYear = search)
                System.out.println(event);
                return event;
            else
                System.out.println("No result");
        }
    }


    public Event searchEventByDuration(){

    }

    public Event searchEventByMemo(){
        System.out.println("Please enter a string to search memo");


    }

    public Event searchEventBySeriesName(){

    }






}




































