import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class FutureEventFrame extends JFrame implements ActionListener {

    Container container = getContentPane();
    ArrayList<JButton> buttons = new ArrayList<JButton>();
    ArrayList<Event> oe = CalendarFacade.showFutureEvent();
    JLabel NoEvent = new JLabel("There are no Past Event");
    JButton back = new JButton("back");
    ArrayList<JLabel> lable = new ArrayList<JLabel>();


    FutureEventFrame(){
        this.createLabels();
        this.createButton();
        this.setTitle("Event");
        this.setVisible(true);
        this.setBounds(10, 10, 450, 700);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLocationAndSize();
        this.addComponentsToContainer();
    }

    public void createLabels(){
        int len = oe.size();
        for (int i=0;i<len;i++){
            JLabel event = new JLabel(oe.get(i).toString());
            lable.add(i,event);
        }
    }

    public void createButton(){
        int len = oe.size();
        for (int i=0;i<len;i++){
            JButton button = new JButton("edit");
            buttons.add(i,button);
        }
    }

    public void setLocationAndSize(){
        back.setBounds(300,600,100,40);
        if (oe.size()==0){
            NoEvent.setBounds(100,50,200,50);
            container.add(NoEvent);
        }else{
            for (int i=0;i<lable.size();i++){
                lable.get(i).setBounds(10,10+i*(700-80)/oe.size(),300,(700-80)/oe.size());
            }
            for (int i=0;i<buttons.size();i++){
                buttons.get(i).setBounds(350,10+i*(700-80)/oe.size(),30,(700-80)/oe.size());
            }
        }
    }


    public void addComponentsToContainer() {
        container.add(back);
        if (oe.size() != 0){
            for (int i=0;i<lable.size();i++){
                container.add(lable.get(i));
            }
//            for (int i=0;i<buttons.size();i++){
//                container.add(buttons.get(i));
//            }
        }
        else{
            container.add(NoEvent);
        }
    }

    public void addActionEvent() {
        for (int i=0;i<buttons.size();i++){
            buttons.get(i).addActionListener(this);
        }
        back.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back){
            this.dispose();
        }
        for (int i=0;i<buttons.size();i++){
            if (e.getSource() == buttons.get(i)) {
                EditEventFrame a = new EditEventFrame(oe.get(i));
            }

        }}
}