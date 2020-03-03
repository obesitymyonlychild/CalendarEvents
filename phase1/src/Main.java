import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        boolean flag = false;
        while (!flag) {
            while (true) {
                System.out.println("Log in[type log]\nCreate new account[type create]");
                String a = input.next();
                if (a.equals("log")) {
                    flag = LoginSystem.login();
                    break;
                } else if (a.equals("create")) {
                    LoginSystem.createAccount();
                    break;
                } else System.out.println("wrong input");
            }
        }
        System.out.println("Successfully log in\n\n\n");
        System.out.println("Today's Event!!!");
        ArrayList todayEvent = Calendar.showTodayEvents();
        System.out.println(todayEvent);
        if (todayEvent.size()>0){
        for (Object o : todayEvent) {
            System.out.println(o);
        }}
        System.out.println("Category\n");
        for (int i=0;i<20;i++){
            System.out.println("=");
        }
        System.out.println("| Past Event     |");
        System.out.println("| Ongoing Event  |");
        System.out.println("| Future Event   |");
        System.out.println("| All Event      |");
        System.out.println("| Series         |");
        System.out.println("| Memo           |");
        System.out.println("| Search         |");
        System.out.println("Type in command:");
        String command = input.next();
        switch (command) {
            case "Past Event": case "past event": case "pastevent": case"PastEvent":{
                Calendar.showPastEvent();}
            case "Ongoing Event": case "ongoing event": case "ongoingevent": case "OngoingEvent":
                Calendar.showOngoingEvent();
            case "Future Event": case "futureevent": case "FutureEvent": case "future event":
                Calendar.showFutureEvent();
            case "All Event": case "allevent": case "AllEvent": case "all event":
                    Calendar.showEvents();
            case "Series": case "series":
                Calendar.showSeries();
            case "Memo": case"memo":
                Calendar.showMemo();
            case "Search": case "search":{
                System.out.println("====================");
                System.out.println("|Search by tag[type in tag]          |");
                System.out.println("|Search by date[type in date]        |");
                System.out.println("|Search by memo[type in memo]        |");
                System.out.println("|Search by series[type in series]    |");
                System.out.println("|Search by event[type in event name] |");
                // search by alert not implemented
                String search = input.next();
                switch (search){
                    case "tag":
                        Calendar.searchEventByTag();
                    case "date":
                        Calendar.searchEventByDate();
                    case "memo":
                        Calendar.searchEventByMemo();
                    case"eventname": case"event name":
                        Calendar.searchEventByName();
                    case"series":
                        Calendar.searchEventBySeriesName();
                }

            }
            default:
                System.out.println("Wrong input!!!");


        }
    }

}
