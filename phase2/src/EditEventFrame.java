import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditEventFrame extends JFrame implements ActionListener {


    // i want this event info on this edit page or not
    Container container = getContentPane();
    //panel should be added to show the
    //event detail
    //tags detail
    //alert detail
    //memo detail
    JButton deleteButton = new JButton("DELETE");
    JButton tagButton = new JButton("EDIT");
    JButton tagDeleteButton = new JButton("DELETE");
    JButton memoButton = new JButton("EDIT");
    JButton memoDeleteButton = new JButton("DELETE");
    JButton alertButton = new JButton("EDIT");
    JButton alertDeleteButton = new JButton("DELETE");
    JButton editEventButton = new JButton("EDIT");
    JButton turnOnButton = new JButton("ON");
    JButton turnOffButton = new JButton("OFF");
    private Event event;
    JButton backButton = new JButton("BACK");


    EditEventFrame(Event event) {
        this.event = event;
        this.setTitle("Edit Event");
        this.setVisible(true);
        this.setBounds(10, 10, 500, 600);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Event getEvent(){
        return this.event;
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        //the location is not done yet.

    }

    public void addComponentsToContainer() {
        container.add(deleteButton);
        container.add(tagButton);
        container.add(memoButton);
        container.add(alertButton);
        container.add(editEventButton);
        container.add(turnOffButton);
        container.add(turnOffButton);
        container.add(backButton);
        container.add(tagDeleteButton);
        container.add(memoDeleteButton);
        container.add(alertDeleteButton);


    }

    public void addActionEvent() {

    }

    static ArrayList<String> Users = new ArrayList<String>();

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == deleteButton){
            // we store currentCalendar rather than currentUser now --- by Oliver
            CalendarFacade.getCurrentCalendar().deleteEvent(event.getName());
            System.out.println("successfully delete this event");
        }

        if (e.getSource() == tagButton){
            JOptionPane.showMessageDialog(this, event.getTags().toString());
            //better have a pop-up frame enable adding and deleting tags
            //need new tag frame
        }

        if (e.getSource() == memoButton){
            //open new frame
            //need memo frame showing memos in the frame, being able to edit
        }

        if (e.getSource()==alertButton){
            //open new frame
            //need alertFrame
        }

        //modify name, start time, duration , address
        if(e.getSource()==editEventButton){
            ModifyEventFrame mod = new ModifyEventFrame(this.event);

        }

        if(e.getSource()==turnOffButton){

        }

        if(e.getSource()==turnOnButton){

        }

        if(e.getSource() == backButton){
            this.dispose();

        }

        }



}

