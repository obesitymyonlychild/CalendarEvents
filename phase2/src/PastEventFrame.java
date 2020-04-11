import java.awt.*;
import java.util.ArrayList;

public class PastEventFrame {
    public static void main(String[] args) {
        MyFrame eventFrame = new MyFrame("Events",400,400,400,600);
        ArrayList<Event> oe = CalendarFacade.showPastEvent();
        if (oe.size()==0){
            eventFrame.add(new TextField("There are no Past Event"));
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
    }
}
