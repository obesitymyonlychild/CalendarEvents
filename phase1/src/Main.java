import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.sun.tools.internal.xjc.reader.xmlschema.BindPurple;
import jdk.nashorn.internal.runtime.ECMAException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main1 {
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
        logInSimulation();
    }

    public static void logInSimulation() {
        System.out.println("Today's Event!!!");
        ArrayList todayEvent = Calendar.showTodayEvents();
        System.out.println(todayEvent);
        assert todayEvent != null;
        if (todayEvent.size() > 0) {
            for (Object o : todayEvent) {
                System.out.println(o);
            }
        }
        while (true){
        System.out.println("Category\n");
        for (int i = 0; i < 20; i++) {
            System.out.println("=");
        }
        System.out.println("| Past Event     |");
        System.out.println("| Ongoing Event  |");
        System.out.println("| Future Event   |");
        System.out.println("| All Event      |");
        System.out.println("| Series         |");
        System.out.println("| Memo           |");
        System.out.println("| Search         |");
        System.out.println("| Add Event      |");
        System.out.println("Type in command:");
        Scanner scan = new Scanner(System.in);
        String command = scan.next();
        switch (command) {
            case "Past Event":
            case "past event":
            case "pastevent":
            case "PastEvent": {
                pastEventSimulation();
            }
            case "Ongoing Event":
            case "ongoing event":
            case "ongoingevent":
            case "OngoingEvent": {
                onGoingEventSimulation();
            }
            case "Future Event":
            case "futureevent":
            case "FutureEvent":
            case "future event": {
                futureEventSimulation();
            }
            case "All Event":
            case "allevent":
            case "AllEvent":
            case "all event": {
                allEventSimulation();
            }
            case "Series":
            case "series": {
                seriesSimulation();
            }
            case "Memo":
            case "memo": {
                memoSimulation();
            }
            case "Search":
            case "search": {
                searchSimulation();
            }
            case "Add Event":
            case "add event":
            case "addevent":
            case "AddEvent": {
                addEventSimulation();
            }

            default: {
                System.out.println("Wrong input!!!");
            }
        }}

    }

    public static void helperOfSimulationMessage(){
        System.out.println("Type in the index of the event:");
        System.out.println("if you want to go back to main, type in -1");
    }


    public static void pastEventSimulation(){
        while (true){
        System.out.println("past events: \n");
        ArrayList<Event> pe = Calendar.showPastEvent();
        helperOfSimulationMessage();
        int index = -1;
        try {

            Scanner scan = new Scanner(System.in);
            index = scan.nextInt();
            Event e = pe.get(index-1);
            eventSimulation(e);
        }
        catch (Exception e) {
            if (index == -1){
                return;
            }else {
                System.out.println("invalid index!");
            }
        }}
    }


    public static void onGoingEventSimulation(){
        while (true){
        System.out.println("on going events: \n");
        ArrayList<Event> oe = Calendar.showOngoingEvent();
        helperOfSimulationMessage();
        int index = -1;
        try {
            Scanner scan = new Scanner(System.in);
            index = scan.nextInt();
            Event e = oe.get(index-1);
            eventSimulation(e);
        }
        catch (Exception e) {
            if (index == -1) {
                return;
            } else {
                System.out.println("invalid index!");
            }
        }
        }

    }

    public static void futureEventSimulation(){
        while (true){
        System.out.println("future events: \n");
        ArrayList<Event> fe = Calendar.showFutureEvent();
        helperOfSimulationMessage();
        int index = -1;
        try {
            Scanner scan = new Scanner(System.in);
            index = scan.nextInt();
            Event e = fe.get(index-1);
            eventSimulation(e);
        }
        catch (Exception e) {
            if (index == -1){
                return;
            }else {
                System.out.println("invalid index!");
            }

        }}

    }

    public static void allEventSimulation(){
        while (true){
        System.out.println("all events: \n");
        ArrayList<Event> ae = Calendar.showEvents();
        helperOfSimulationMessage();
        int index = -1;
        try {
            Scanner scan = new Scanner(System.in);
            index = scan.nextInt();
            Event e = ae.get(index-1);
            eventSimulation(e);
        }
        catch (Exception e) {
            if (index == -1) {
                return;
            } else {
                System.out.println("invalid index!");

            }
        }
        }
    }

    public static void seriesSimulation(){
        while (true){
        System.out.println("all series: \n");
        ArrayList<Series> s = Calendar.showSeries();
        helperOfSimulationMessage();
        int index = -1;
        try {
            Scanner scan = new Scanner(System.in);
            index = scan.nextInt();
            Series s0 = s.get(index-1);
            eventInSeriesSimulation(s0);
        }
        catch (Exception e) {
            if (index == -1) {
                return;
            } else {
                System.out.println("invalid index!");
                seriesSimulation();
            }
        }
        }
    }

    public static void memoSimulation(){
        while (true){
        System.out.println("all memos: \n");
        ArrayList<Memo> m = Calendar.showMemo();
        helperOfSimulationMessage();
        int index = -1;
        try {
            Scanner scan = new Scanner(System.in);
            index = scan.nextInt();
            Memo m0 = m.get(index-1);
            eventInMemoSimulation(m0);
        }
        catch (Exception e) {
            if (index == -1) {
                return;
            } else {
                System.out.println("invalid index!");
            }
        }
        }
    }

//    private static String operationEventCategory(){
//        System.out.println("Delete Event[type delete]");
//        System.out.println("Set Alert[type setalert]");
//        System.out.println("Delete Alert[type deletealert]");
//        System.out.println("Turn On Alert[type alerton]");
//        System.out.println("Turn Off Alert[type alertoff]");
//        System.out.println("Add Tag[type addtag]");
//        System.out.println("Delete Tag[type deletetag]");
//        System.out.println("Add Memo[type addmemo]");
//        System.out.println("Delete Memo[type deletememo]");
//        System.out.println("====================================");
//        System.out.println("Type in your operation:");
//        Scanner scanner = new Scanner(System.in);
//        return scanner.next();
//    }
//
//    private static void operationOnEvent(Event event){
//        switch (operationEventCategory()){
//            case "delete":
//        }
//
//    }

    public static void searchSimulation(){
        while (true) {
            System.out.println("====================");
            System.out.println("|Search by tag[type in tag]          |");
            System.out.println("|Search by date[type in date]        |");
            System.out.println("|Search by memo[type in memo]        |");
            System.out.println("|Search by series[type in series]    |");
            System.out.println("|Search by event[type in event name] |");
            System.out.println("search by: ");
            System.out.println("if you want to go back to main, type main");
            Scanner scan = new Scanner(System.in);
            String command = scan.next();
            switch (command) {
                case "tag":
                    Calendar.searchEventByTag();
                case "date":
                    Calendar.searchEventByDate();
                case "memo":
                    Calendar.searchEventByMemo();
                case "event name":
                    Calendar.searchEventByName();
                case "series":
                    Calendar.searchEventBySeriesName();
                case "main":
                    return;
                default:
                    System.out.println("invalid command");
            }
        }
    }

    public static void addEventSimulation() {
        while (true){
        System.out.println("type in the event information");
        Scanner scan = new Scanner(System.in);
        System.out.println("name of the event: ");
        String name = scan.next();
        System.out.println("start time in format: yyyy-MM-dd HH:mm");
        String startTime = scan.next();
        System.out.println("duration in minute (type long): ");
        Long duration = scan.nextLong();
        System.out.println("address: ");
        String address = scan.next();
        try {
            Calendar.createEvent(name, startTime, duration, address);
            return;
        }catch (Exception e){
            System.out.println("invalid input! please try again.");
            addEventSimulation();
        }}


    }

    public static void eventInSeriesSimulation(Series s0){
        while (true){
        System.out.println("events in series");
        s0.showEvents(); // index line by line
        helperOfSimulationMessage();
        int index = -1;
        try {
            Scanner scan = new Scanner(System.in);
            index = scan.nextInt();
            Event e = s0.getEvents().get(index);
            eventSimulation(e);
        }
        catch (Exception e) {
            if (index == -1) {
                return;
            } else {
                System.out.println("invalid index!");
            }
        }
        }
    }

    public static void eventInMemoSimulation(Memo m0){
        while (true){
        System.out.println("events in memos");
        m0.showEvents(); // index line by line
        helperOfSimulationMessage();
        int index = -1;
        try {
            Scanner scan = new Scanner(System.in);
            index = scan.nextInt();
            Event e = m0.getEvents().get(index);
            eventSimulation(e);
        }
        catch (Exception e) {
            if (index == -1) {
                return;
            } else {
                System.out.println("invalid index!");
            }
        }
        }
    }

    public static void eventSimulation(Event event){
        while (true){
        System.out.println("what do you want to do?");
        System.out.println("=====================================================");
        System.out.println("| delete event (type in delete event)               |");
        System.out.println("| view event (type in view event)                   |");
        System.out.println("| get tags (type in get tags)                       |");
        System.out.println("| get memos (type in get memos)                     |");
        System.out.println("| get alerts (type in get alerts)                   |");
        System.out.println("| change name (type in change name)                 |");
        System.out.println("| change start time (type in change start time)     |");
        System.out.println("| change duration (type in change duration)         |");
        System.out.println("| change address (type in change address)           |");
        System.out.println("| set alert (type in set alert)                     |");
        System.out.println("| turn on alert (type in turn on alert)             |");
        System.out.println("| turn off alert (type in turn off alert)           |");
        System.out.println("| add tag (type in add tag)                         |");
        System.out.println("| delete tag (type in delete tag)                   |");
        System.out.println("| add memo (type in add memo)                       |");
        System.out.println("| delete memo (type in delete memo)                 |");
        System.out.println("| back to main (type in main)                       |");
        Scanner scan = new Scanner(System.in);
        String command = scan.next();
        switch (command){
            case "delete event":{
                Calendar.getterCurrentUser().deleteEvent(event.getName());
                System.out.println("successfully delete this event");
                return;
            }
            case "view event":{
                while (true){
                System.out.println(event);
                System.out.println("type in back, if you want to do more with the event");
                System.out.println("type in main, if you want to go back to main");
                String c = scan.next();
                switch (c) {
                    case "main": {
                       return;
                    }
                    case "back": {
                        break;
                    }
                    default:{
                        System.out.println("Wrong input");
                    }
                }}
            }

            case "get tags": {
                while (true){
                System.out.println(event.getTags());
                System.out.println("type in back, if you want to do more with the event");
                System.out.println("type in main, if you want to go back to main");
                String c = scan.next();
                switch (c) {
                    case "main": {
                        return;
                    }
                    case "back": {
                        break;
                    }
                    default:{
                        System.out.println("Wrong input");
                    }
                }}
            }
            /////////////////////////////////////////////////////////////////////////////////############
            case "get memos": {
                System.out.println("memos in this event: ");
                event.showMemos(); // index line by line
                Memo m0;
                while(true) {
                    try {
                        System.out.println("type in the index of the memo: ");
                        int index = scan.nextInt();
                        m0 = event.getMemos().get(index);
                        break;
                    } catch (Exception e) {
                        System.out.println("invalid input!");
                    }
                }
                eventInMemoSimulation(m0);
            }
            case "get alerts":{
                System.out.println("alerts for this event: ");
                event.showAlerts(); // index line by line
                Alert a0;
                while(true) {
                    try {
                        System.out.println("type in the index of the alert: ");
                        int i = scan.nextInt();
                        a0 = event.getAlerts().get(i);
                        break;
                    } catch (Exception e) {
                        System.out.println("invalid input!");
                    }
                }
                alertSimulation(event, a0);
            }
            case "change name":{
                while (true){
                System.out.println("current name: " + event.getName());
                System.out.println("type in the new name: ");
                String newName = scan.next();
                event.setName(newName);
                System.out.println("name set to: " + newName);
                System.out.println("type in back, if you want to do more with the event");
                System.out.println("type in main, if you want to go back to main");
                String c = scan.next();
                switch (c) {
                    case "main": {
                       return;
                    }
                    case "back": {
                        break;
                    }
                    default:{
                        System.out.println("Wrong input");
                    }
                }}
            }
            case "change start time":{
                while (true){
                System.out.println("current start time: " + event.getStartTime().toString().replace("T", " "));
                while (true) {
                    try {
                        System.out.println("type in the new start time in format yyyy-MM-dd HH:mm");
                        String newStartTime = scan.next();
                        event.setStartTime(newStartTime);
                        break;
                    } catch (Exception e) {
                        System.out.println("invalid time format.");
                    }
                }
                System.out.println("start time set to: " + event.getStartTime().toString().replace("T", " "));
                System.out.println("type in back, if you want to do more with the event");
                System.out.println("type in main, if you want to go back to main");
                String c = scan.next();
                switch (c) {
                    case "main": {
                        return;
                    }
                    case "back": {
                        break;
                    }
                    default:{
                        System.out.println("Wrong input");
                    }
                }}
            }
            case "change duration": {
                while (true){
                System.out.println("current duration: " + event.getDuration() + "minutes");
                System.out.println("type in the new duration: ");
                int newDuration = scan.nextInt();
                event.setDuration(newDuration);
                System.out.println("duration set to: " + newDuration);
                System.out.println("type in back, if you want to do more with the event");
                System.out.println("type in main, if you want to go back to main");
                String c = scan.next();
                switch (c) {
                    case "main": {
                       return;
                    }
                    case "back": {
                        break;
                    }
                    default:{
                        System.out.println("Wrong input");
                    }
                    }
                }}

            case "change address": {
                while (true){
                System.out.println("current address: " + event.getAddress());
                System.out.println("type in the new address: ");
                String newAddress = scan.next();
                event.setAddress(newAddress);
                System.out.println("address set to: " + newAddress);
                System.out.println("type in back, if you want to do more with the event");
                System.out.println("type in main, if you want to go back to main");
                String c = scan.next();
                switch (c) {
                    case "main": {
                        return;
                    }
                    case "back": {
                        break;
                    }
                    default:{
                        System.out.println("Wrong input");
                    }
                }}
            }
            case "set alert": {
                while (true){
                System.out.println("all the alerts in the events");
                event.getAlerts();
                while (true) {
                    try {
                        System.out.println("type in the information for the new alert");
                        System.out.println("start time in format yyyy-MM-dd HH:mm");
                        String newStartTime = scan.next();
                        System.out.println("frequency: type in MINUTE or HOUR or DAY or WEEK or MONTH or YEAR or ONETIME");
                        String unit = scan.next();
                        System.out.println("repeat how many time: ");
                        int num = scan.nextInt();
                        event.setAlert(newStartTime, num, unit);
                        break;
                    } catch (Exception e) {
                        System.out.println("invalid input");
                    }
                }
                System.out.println("alert set successfully!");
                System.out.println("type in back, if you want to do more with the event");
                System.out.println("type in main, if you want to go back to main");
                String c = scan.next();
                switch (c) {
                    case "main": {
                        return;
                    }
                    case "back": {
                        break;
                    }
                    default:{
                        System.out.println("Wrong input");
                    }
                }
            }}
            case "turn on alert": {
                while (true){
                event.turnOnAlert();
                System.out.println("alert is turned on");
                System.out.println("type in back, if you want to do more with the event");
                System.out.println("type in main, if you want to go back to main");
                String c = scan.next();
                switch (c) {
                    case "main": {
                        return;
                    }
                    case "back": {
                        break;
                    }
                    default:{
                        System.out.println("Wrong input");
                    }
                }}
            }
            case "turn off alert": {
                while (true){
                event.turnOffAlert();
                System.out.println("alert is turned off");
                System.out.println("type in back, if you want to do more with the event");
                System.out.println("type in main, if you want to go back to main");
                String c = scan.next();
                switch (c) {
                    case "main": {
                        return;
                    }
                    case "back": {
                        break;
                    }
                    default:{
                        System.out.println("Wrong input");
                    }
                }}
            }
            case "add tag": {
                while (true){
                System.out.println("current tags: ");
                event.getTags(); //index line by line
                System.out.println("type in the new tag: ");
                String newTag = scan.next();
                event.addTag(newTag);
                System.out.println("new tag is added: " + newTag);
                System.out.println("type in back, if you want to do more with the event");
                System.out.println("type in main, if you want to go back to main");
                String c = scan.next();
                switch (c) {
                    case "main": {
                        return;
                    }
                    case "back": {
                        break;
                    }
                    default:{
                        System.out.println("Wrong input");
                    }
                }}
            }
            ////############################################################
            case "delete tag": {
                while (true) {
                    System.out.println("current tags: ");
                    System.out.println("type in the tag you want to delete: ");
                    String t = scan.next();
                    if (event.deleteTag(t)){
                        System.out.println("succesfully delete tag!");
                        break;
                    }
                    else{
                        System.out.println("no such tag");
                    }
                }
                System.out.println("type in back, if you want to do more with the event");
                System.out.println("type in main, if you want to go back to main");
                String c = scan.next();
                switch (c) {
                    case "main": {
                        logInSimulation();
                    }
                    case "back": {
                        eventSimulation(event);
                    }
                    default:{
                        eventSimulation(event);
                    }
                }
            }
            case "add memo": {
                System.out.println("type in the information for memo: ");
                System.out.println("type in the name: ");
                String memoName = scan.next();
                System.out.println("type in the content: ");
                String memoContent = scan.next();
                event.addNewMemo(memoName, memoContent);
                System.out.println("memo add successfully!");
                System.out.println("type in back, if you want to do more with the event");
                System.out.println("type in main, if you want to go back to main");
                String c = scan.next();
                switch (c) {
                    case "main": {
                        logInSimulation();
                    }
                    case "back": {
                        eventSimulation(event);
                    }
                    default:{
                        eventSimulation(event);
                    }
                }
            }
            case "delete memo": {
                while (true) {
                    System.out.println("current memo names: ");
                    event.getMemos();
                    System.out.println("type in the memo name you want to delete: ");
                    String name = scan.next();
                    if (event.deleteMemo(name)){
                        System.out.println("successfully delete the memo");
                        break;
                    }else{
                        System.out.println("no such memo");
                    }

                }
                System.out.println("type in back, if you want to do more with the event");
                System.out.println("type in main, if you want to go back to main");
                String c = scan.next();
                switch (c) {
                    case "main": {
                        logInSimulation();
                    }
                    case "back": {
                        eventSimulation(event);
                    }
                    default:{
                        eventSimulation(event);
                    }
                }
            }

            case "main": {
                logInSimulation();
            }


        }}

    }

    public static void alertSimulation(Event event, Alert a){
        System.out.println("what do you want to do with this alert?");
        System.out.println("| delete alert (type in delete)        |");
        System.out.println("| view this alert (type in view)       |");
        System.out.println("| return to main (type in main)        | ");
        System.out.println("| return to this event (type in back) |");
        Scanner scan = new Scanner(System.in);
        String command = scan.next();
        switch (command){
            case "delete":{
                event.deleteAlert(a.getStartTime().toString().replace("T", " "));
            }
            case "view":{
                System.out.println(a);
            }
            case "main": {
                logInSimulation();
            }
            case "back": {
                eventSimulation(event);
            }
        }
        System.out.println("type in back, if you want to do more with the event");
        System.out.println("type in main, if you want to go back to main");
        String c = scan.next();
        switch (c) {
            case "main": {
                logInSimulation();
            }
            case "back": {
                eventSimulation(event);
            }
            default:{
                eventSimulation(event);
            }
        }


    }
}
