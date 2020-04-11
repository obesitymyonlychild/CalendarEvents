import java.awt.*;
import java.util.ArrayList;

public class AllEventFrame {
    public static void main(String[] args) {
        MyFrame eventFrame = new MyFrame("Events",400,400,400,600);
        ArrayList<Event> pe = CalendarFacade.showPastEvent();
        ArrayList<Event> oe = CalendarFacade.showOngoingEvent();
        ArrayList<Event> fe = CalendarFacade.showFutureEvent();
        for (int i=0;i<pe.size();i++){
            oe.add(oe.size()+i,pe.get(i));
        }
        for (int i=0;i<fe.size();i++){
            oe.add(oe.size()+i,fe.get(i));
        }
        if (oe.size()==0){
            eventFrame.add(new TextField("There are no Event in this account"));
        } else{
            int len = oe.size();
            eventFrame.setLayout(new GridLayout(len,1));
            for (int i=0;i<len;i++){
                eventFrame.add(new TextField(oe.get(i).toString()));
            }
            for (int i=0;i<len;i++){
                int a = 600/len;
                Button b = new Button(oe.get(i).getName());
                b.setBounds(300,10+a*i,20,20);
            }
        }
    }}